package contaBancaria.service;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.SubscribeRequest;
import contaBancaria.entities.Agencia;
import contaBancaria.entities.Conta;
import contaBancaria.entities.Movimento;
import contaBancaria.entities.Usuario;
import contaBancaria.entities.dto.ContaDTO;
import contaBancaria.entities.dto.MovimentoDTO;
import contaBancaria.exception.AwsException;
import contaBancaria.exception.ConsultaContaException;
import contaBancaria.exception.ParametroInvalidoException;
import contaBancaria.exception.UsuarioNotFoundException;
import contaBancaria.repository.ContaRepository;
import contaBancaria.repository.MovimentoRepository;
import contaBancaria.utils.NotificacaoRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ContaService {

    private static final Logger LOG = LoggerFactory.getLogger(ContaService.class);
    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private MovimentoRepository movimentoRepository;

    @Autowired
    UsuarioService usuarioService;
    @Autowired
    AgenciaService agenciaService;

    @Autowired
    private AmazonSNS snsClient;

    @Autowired
    private MovimentoService movimentoService;


    public Conta criar(ContaDTO request) throws Exception {

        Conta newConta = new Conta();
        validaCamposConta(request);
        newConta.setSaldo(BigDecimal.valueOf(0));
        newConta.setDataCriacaoConta(LocalDateTime.now());
        newConta.setAgencia(buscaAgencia(request.getLocalidade()).get());
        newConta.setTitular(usuarioService.buscaCpf(request.getCpf()).get());
        newConta.setNumeroConta(numeroContaEDigito(1000));
        newConta.setDigitoConta(numeroContaEDigito(1));
        newConta.setIdConta(gerarIdConta(newConta));

        return contaRepository.save(newConta);
    }

    private String gerarIdConta(Conta conta) {
        int numeroAgencia = conta.getAgencia().getNumeroAgencia();
        int numeroConta = conta.getNumeroConta() + conta.getDigitoConta();
        int dhCriacao = converterLocalDateTimeEmInt(conta.getDataCriacaoConta());

        return Stream.of(numeroAgencia, numeroConta, dhCriacao).map(Objects::toString).collect(Collectors.joining());
    }

    private int converterLocalDateTimeEmInt(LocalDateTime dataCriacaoConta) {
        List<Integer> values = Arrays.asList(dataCriacaoConta.getMonthValue(), dataCriacaoConta.getDayOfMonth(), dataCriacaoConta.getYear());
        return Integer.valueOf(values.stream().map(i -> i.toString()).collect(Collectors.joining()));
    }

    private int numeroContaEDigito(int value) {
        Random random = new Random();
        return random.nextInt(value);
    }

    private Optional<Agencia> buscaAgencia(String localAgencia) {
        return agenciaService.buscarNumeroAgencia(localAgencia);
    }


    public Optional<Conta> consultarSaldo(String idConta) {
        return contaRepository.findByIdConta(idConta);
    }

    public Optional<Conta> buscarPorNumeroConta(int numeroConta) {
        return contaRepository.findByNumeroConta(numeroConta);
    }

    public List<Conta> listarContas() {
        return contaRepository.findAll();
    }

    public Optional<Conta> verificarConta(Long id) {
        return contaRepository.findById(id);
    }

    public BigDecimal consultarSaldo(int numeroConta) {
        Optional<Conta> conta = contaRepository.findByNumeroConta(numeroConta);
        return conta.get().getSaldo();

    }

    public void atualizaSaldo(MovimentoDTO request) {
        Optional<Conta> conta = contaRepository.findByNumeroConta(request.getNumeroConta());
        conta.get().setSaldo(calcularSaldo(request));
    }

    public BigDecimal calcularSaldo(MovimentoDTO request) {
        BigDecimal saldoAtual = consultarSaldo(request.getNumeroConta());
        if (!Objects.nonNull(saldoAtual)) {
            saldoAtual = BigDecimal.valueOf(0);
        }
        if (request.getTipoMovimento().equals("saque")) {
            saldoAtual = calcularDebito(saldoAtual, request.getValorMovimentacao());
        } else {
            saldoAtual = calcularCredito(saldoAtual, request.getValorMovimentacao());
        }

        return saldoAtual;
    }

    private BigDecimal calcularCredito(BigDecimal saldoAtual, BigDecimal valorDeposito) {
        return saldoAtual.add(valorDeposito);
    }

    private BigDecimal calcularDebito(BigDecimal saldoAtual, BigDecimal valorDeposito) {
        return saldoAtual.subtract(valorDeposito);
    }

    public List<Movimento> extratoDetalhadoConta(String idConta) throws ConsultaContaException {
        Optional<Conta> conta = buscarPorIdConta(idConta);
        if (Objects.isNull(conta)) {
            throw new ConsultaContaException("Conta " + idConta + " não encontrada. Favor, verificar.");
        }
        return movimentoService.extratoDetalhadoMovimentacoes(conta.get());
    }

    public Optional<Conta> buscarPorIdConta(String idConta) {
        return contaRepository.findByIdConta(idConta);
    }

    private void validaCamposConta(ContaDTO contaDTO) throws Exception {
        LOG.info("Validando os campos para a criação da conta.");
        validaCpf(contaDTO.getCpf());
        validadeAgencia(contaDTO.getLocalidade());
    }

    private void validaCpf(String cpf) throws Exception {
        if (Objects.isNull(cpf)) {
            throw new ParametroInvalidoException("Cpf obrigatório. Favor, informar.");
        }
        Optional<Usuario> consultaUsuario = usuarioService.buscaCpf(cpf);
        if (consultaUsuario.isEmpty() || !consultaUsuario.isPresent()) {
            throw new UsuarioNotFoundException("Cpf informado não encontrado em nosso banco de dados. Favor, verificar");
        }
    }

    private void validadeAgencia(String localAgencia) throws Exception {
        if (Objects.isNull(localAgencia)) {
            throw new ParametroInvalidoException("Localidade(Estado) obrigatória. Favor, informar.");
        }
        Optional<Agencia> consultaAgencia = agenciaService.buscarNumeroAgencia(localAgencia);
        if (consultaAgencia.isEmpty() || !consultaAgencia.isPresent()) {
            throw new UsuarioNotFoundException("Agencia não encontrada para a localidade informada. Favor, verificar");
        }
    }

//    public void envioDeSNScomBaseSaldoConta(NotificacaoRequest request) throws ConsultaContaException, AwsException {
//        Optional<Conta> dadosConta = contaRepository.findByIdConta(idConta);
//        if (dadosConta.isPresent() && dadosConta.get().getSaldo().compareTo(BigDecimal.ZERO) < 0) {
//            notificacaoSNS(request);
//        }
//    }

    public void notificacaoSNS(NotificacaoRequest request) throws AwsException {
        if (snsClient.createTopic(request.getTopic()) == null) {
            throw new AwsException("Tópico informado inválido.");
        }

        for (String destinatario : request.getDestinatarios()) {
            snsClient.subscribe(new SubscribeRequest(request.getTopic(), "email", destinatario));
        }
    }
}
