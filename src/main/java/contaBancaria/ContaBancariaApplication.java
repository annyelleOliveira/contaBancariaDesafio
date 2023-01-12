package contaBancaria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.autoconfigure.context.*;
import org.springframework.cloud.aws.autoconfigure.mail.MailSenderAutoConfiguration;
import org.springframework.cloud.aws.messaging.config.annotation.EnableSns;
import org.springframework.cloud.aws.messaging.config.annotation.EnableSqs;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableSqs
@EnableSns
@EnableScheduling
@SpringBootApplication
@EnableAutoConfiguration(exclude = {
        ContextCredentialsAutoConfiguration.class,
        ContextInstanceDataAutoConfiguration.class,
        ContextRegionProviderAutoConfiguration.class,
        ContextResourceLoaderAutoConfiguration.class,
        ContextStackAutoConfiguration.class,
        MailSenderAutoConfiguration.class})
public class ContaBancariaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContaBancariaApplication.class, args);
    }
}
