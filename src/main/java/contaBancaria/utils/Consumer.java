package contaBancaria.utils;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import contaBancaria.entities.ConteudoNotificacao;
import contaBancaria.service.MovimentoService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Consumer {

    private static final Logger LOG = LoggerFactory.getLogger(MovimentoService.class);

    @Autowired
    private AmazonSQSAsync sqs;

    @SqsListener(value = "movimentacoes_conta", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void processoFila(ConteudoNotificacao mensagem) {
        LOG.info("Messagem do SQS {}", mensagem);
    }
}
