package contaBancaria.utils;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;


public class SNSClient {

    @Value("AKIAQTNGFBQXBW5IUN45")
    private String accessKey;

    @Value("Kx3/AZw9n68YpRxKRiJExiydeu0mlxqowEt/1MkM")
    private String secretKey;
    @Autowired
    AmazonSNS amazonSNS = AmazonSNSClientBuilder.defaultClient();
    String topicArn = amazonSNS.createTopic("notifica-utilizacao-limite").getTopicArn();

}
