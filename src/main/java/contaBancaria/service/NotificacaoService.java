package contaBancaria.service;

import contaBancaria.entities.ConteudoNotificacao;
import contaBancaria.entities.Movimento;
import contaBancaria.repository.MovimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificacaoService {

    @Autowired
    MovimentoRepository movimentoRepository;

    public ConteudoNotificacao conteudoNotificacao() {
        Movimento request = movimentoRepository.findFirstByOrderByIdDesc();
        ConteudoNotificacao conteudoNotificacao = new ConteudoNotificacao((String.valueOf(request.getIdConta().getAgencia().getNumeroAgencia())),
                (String.valueOf(request.getIdConta().getNumeroConta())),
                (String.valueOf(request.getIdConta().getDigitoConta())),
                (String.valueOf(request.getValorMovimentacao())));
//        conteudoNotificacao.setNumero_agencia(String.valueOf(request.getIdConta().getAgencia().getNumeroAgencia()));
//        conteudoNotificacao.setNumero_conta(String.valueOf(request.getIdConta().getNumeroConta()));
//        conteudoNotificacao.setDigito_conta(String.valueOf(request.getIdConta().getDigitoConta()));
//        conteudoNotificacao.setValor_movimento(String.valueOf(request.getValorMovimentacao()));
        return conteudoNotificacao;
    }

}
