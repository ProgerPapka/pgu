package beans;

import configuration.Token;
import controller.SubscribeController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import service.ServiceSendingDataToELK;
import service.ServiceSendingDataToELKImpl;
import service.SubscriberService;
import service.SubscriberServiceImpl;

@Configuration
public class Config {

    @Bean
    SubscriberService initFirstService(){
        return new SubscriberServiceImpl();
    }

    @Bean
    ServiceSendingDataToELK initSecondService(){
        return new ServiceSendingDataToELKImpl();
    }

    @Bean(name = "elk")
    @Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
    Token initToken(){
        return new Token();
    }

    @Bean
    SubscribeController subscribeController(){
        return new SubscribeController();
    }


}
