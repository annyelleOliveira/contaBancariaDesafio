package contaBancaria.utils;

import contaBancaria.service.NotificacaoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Publisher {

    @Autowired
    private QueueMessagingTemplate queueMessagingTemplate;
    @Value("https://sqs.us-east-1.amazonaws.com/041687518254/movimentacoes_conta")
    private String endpoint;

    @Autowired
    NotificacaoService notificacaoService;

    @Scheduled(fixedRate = 1000)
    public void scheduleFixedRateTask() {
        log.info("Enviando mensagem para SQS");
        queueMessagingTemplate.convertAndSend(endpoint, notificacaoService.conteudoNotificacao());
    }

}
