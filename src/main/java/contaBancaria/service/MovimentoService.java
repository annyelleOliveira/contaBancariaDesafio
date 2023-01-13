package contaBancaria.service;

import contaBancaria.entities.Conta;
import contaBancaria.entities.Movimento;
import contaBancaria.entities.Usuario;
import contaBancaria.entities.dto.MovimentoDTO;
import contaBancaria.exception.ConsultaContaException;
import contaBancaria.exception.ParametroInvalidoException;
import contaBancaria.exception.UsuarioNotFoundException;
import contaBancaria.repository.MovimentoRepository;
import contaBancaria.utils.Publisher;
import contaBancaria.utils.SNSConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MovimentoService {

    private static final Logger LOG = LoggerFactory.getLogger(MovimentoService.class);

    @Autowired
    private MovimentoRepository movimentoRepository;

    @Autowired
    private ContaService contaService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private NotificacaoService notificacaoService;

    @Autowired
    private SNSConfig snsConfig;

    @Autowired
    private Publisher publisher;

    public Movimento create(MovimentoDTO request) throws Exception {
        Movimento movimento = new Movimento();
        validaCamposMovimento(request);
        movimento.setSaldoAnterior(contaService.consultarSaldo(request.getNumeroConta()));
        movimento.setValorMovimentacao(request.getValorMovimentacao());
        movimento.setDh_movimento(LocalDateTime.now());
        movimento.setDs_movimento(request.getDs_movimento());
        movimento.setIdConta(contaService.buscarPorNumeroConta(request.getNumeroConta()).get());
        movimento.setTitular(usuarioService.buscaCpf(request.getCpf()).get());
        movimento.setTipoMovimento(request.getTipoMovimento());
        contaService.atualizaSaldo(request);
        movimento.setSaldoAtual(contaService.consultarSaldo(request.getNumeroConta()));

        Movimento movimentosalvo = movimentoRepository.save(movimento);
        notificacaoService.conteudoNotificacao();
        verificaSaldoAtual(contaService.consultarSaldo(request.getNumeroConta()));
        return movimentosalvo;

    }

    private void validaCamposMovimento(MovimentoDTO request) throws Exception {
        LOG.info("Validando os campos para a criação do Usuário.");
        validaCpf(request.getCpf());
        validaNumeroConta(request.getNumeroConta());
        validaTipoMovimento(request.getTipoMovimento());
        validaValorMovimentacao(request.getValorMovimentacao());
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

    private void validaNumeroConta(int numeroConta) throws Exception {
        if (Objects.isNull(numeroConta)) {
            throw new ParametroInvalidoException("Número da conta não informado. Favor, informar.");
        }
        Optional<Conta> buscarConta = contaService.buscarPorNumeroConta(numeroConta);
        if (buscarConta.isEmpty() || !buscarConta.isPresent()) {
            throw new ConsultaContaException("Conta com o número: " + numeroConta + " não encontrada. Favor, verificar");
        }
    }

    private void validaValorMovimentacao(BigDecimal valorMovimentacao) throws ParametroInvalidoException {
        if (Objects.isNull(valorMovimentacao)) {
            throw new ParametroInvalidoException("Valor não informado. Favor, informar.");
        }
    }

    private void validaTipoMovimento(String tipoMovimento) throws ParametroInvalidoException {
        if (Objects.isNull(tipoMovimento)) {
            throw new ParametroInvalidoException("Tipo do movimento obrigatório. Favor, informar.");
        }
    }

    public List<Movimento> listaMovimento() {
        return movimentoRepository.findAll();
    }

    public List<Movimento> extratoDetalhadoMovimentacoes(Conta idConta) {
        return movimentoRepository.findByIdConta(idConta);
    }

    private void verificaSaldoAtual(BigDecimal saldoAtual) {
        if (saldoAtual.compareTo(BigDecimal.ZERO) < 0) {
            snsConfig.amazonSNSAsync();
        }
    }
}
