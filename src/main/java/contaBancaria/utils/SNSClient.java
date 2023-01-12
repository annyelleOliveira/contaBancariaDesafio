package contaBancaria.utils;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.amazonaws.services.sns.model.SubscribeResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;


public class SNSClient {

    @Autowired
    AmazonSNS snsClient = AmazonSNSClientBuilder.defaultClient();
    String topicArn = snsClient.createTopic("notifica-utilizacao-limite").getTopicArn();
    SubscribeResult subscribeRequest = snsClient.subscribe(new SubscribeRequest(topicArn, "email", "aannyoliveira@gmail.com"));
}
