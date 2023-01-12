package contaBancaria.controller;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.SubscribeRequest;
import contaBancaria.utils.NotificacaoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificacaoController {

    @Autowired
    private AmazonSNS snsClient;


    @PostMapping("/notificacao")
    public ResponseEntity<String> sendNotification(@RequestBody NotificacaoRequest request) {
        if (snsClient.createTopic(request.getTopic()) == null) {
            return ResponseEntity.badRequest().body("Invalid topic.");
        }

        for (String recipient : request.getDestinatarios()) {
            snsClient.subscribe(new SubscribeRequest(request.getTopic(), "email", recipient));
        }

        return ResponseEntity.ok("Notificacao enviada com sucesso.");
    }
}
