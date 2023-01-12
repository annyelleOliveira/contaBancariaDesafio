package contaBancaria.utils;

import contaBancaria.entities.ConteudoNotificacao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Consumer {

    @SqsListener(value = "movimentacoes_conta", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void processoFila(ConteudoNotificacao mensagem) {
        log.info("Messagem do SQS {}", mensagem);
    }
}
