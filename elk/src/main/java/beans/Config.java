package beans;

import configuration.Token;
import controller.SubscribeController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import webservice.client.IPGU02.ArchiveUtil;
import webservice.client.IPGU02.ServiceSendingDataToELK;
import webservice.client.IPGU02.ServiceSendingDataToELKImpl;
import webservice.client.IPGUElkSubscribeService.SubscriberService;
import webservice.client.IPGUElkSubscribeService.SubscriberServiceImpl;
import webservice.objects.elk.CreateOrderService;
import webservice.objects.elk.CreateOrderService_Service;
import webservice.objects.smev.ObjectFactory;


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

    @Bean
    CreateOrderService initConnectToService(){
        CreateOrderService_Service createOrderService_service = new CreateOrderService_Service();
        return createOrderService_service.getCreateOrderService();
    }

    @Bean
    ArchiveUtil initArchiveUtil(){
        return new ArchiveUtil();
    }

    @Bean
    ObjectFactory initObjectFactorySmev(){
        return new ObjectFactory();
    }

    @Bean
    webservice.objects.elk.ObjectFactory initObjectFactoryElk(){
        return new webservice.objects.elk.ObjectFactory();
    }

    @Bean
    webservice.objects.archive.ObjectFactory initObjectFactoryArchive(){
        return new webservice.objects.archive.ObjectFactory();
    }

}
