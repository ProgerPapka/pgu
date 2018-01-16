package beans;

import configuration.Token;
import controller.SubscribeController;
import controller.TokenController;
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
import webservice.objects.elk.ElkSubscribeService;
import webservice.objects.elk.ElkSubscribeService_Service;
import webservice.objects.smev.ObjectFactory;


@Configuration
public class Config {

    @Bean
    ElkSubscribeService initElkSubscribeService() {
        ElkSubscribeService_Service elkSubscribeService_service = new ElkSubscribeService_Service();
        return elkSubscribeService_service.getElkSubscribeService();
    }

    @Bean
    public TokenController initTokenController() {
        return new TokenController();
    }

    @Bean
    public SubscriberService initFirstService() {
        return new SubscriberServiceImpl();
    }

    @Bean
    public ServiceSendingDataToELK initSecondService() {
        return new ServiceSendingDataToELKImpl();
    }

    @Bean(name = "elk")
    @Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public Token initToken() {
        return new Token();
    }

    @Bean
    public SubscribeController subscribeController() {
        return new SubscribeController();
    }

    @Bean
    public CreateOrderService initConnectToService() {
        CreateOrderService_Service createOrderService_service = new CreateOrderService_Service();
        return createOrderService_service.getCreateOrderService();
    }

    @Bean
    public ArchiveUtil initArchiveUtil() {
        return new ArchiveUtil();
    }

    @Bean(name = "objFactSmev")
    public ObjectFactory initObjectFactorySmev() {
        return new ObjectFactory();
    }

    @Bean(name = "objFactElk")
    public webservice.objects.elk.ObjectFactory initObjectFactoryElk() {
        return new webservice.objects.elk.ObjectFactory();
    }

    @Bean(name = "objFactArch")
    public webservice.objects.archive.ObjectFactory initObjectFactoryArchive() {
        return new webservice.objects.archive.ObjectFactory();
    }

}
