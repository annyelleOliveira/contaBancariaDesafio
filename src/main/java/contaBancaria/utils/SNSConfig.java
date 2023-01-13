package contaBancaria.utils;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class SNSConfig {


        @Value("us-east-1")
        private String region;

        @Value("AKIAQTNGFBQXBW5IUN45")
        private String accessKey;

        @Value("Kx3/AZw9n68YpRxKRiJExiydeu0mlxqowEt/1MkM")
        private String secretKey;


        @Bean
        @Primary
        public AmazonSNS amazonSNSAsync() {
            return AmazonSNSClientBuilder.standard()
                    .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
                    .withRegion(Regions.US_EAST_1)
                    .build();
        }
}
