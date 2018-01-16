package webservice.client.IPGUElkSubscribeService;

import exception.ElkServiceException;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import webservice.MyServiceLogHandler;
import webservice.client.IPGU02.ServiceSendingDataToELK;
import webservice.client.IPGU02.ServiceSendingDataToELKImpl;
import webservice.objects.elk.ElkSubscribeService;
import webservice.objects.elk.elksubscribetype.ObjectFactory;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;
import javax.xml.ws.handler.Handler;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class SubscriberServiceTest {

    private static Logger logger = Logger.getLogger(SubscriberServiceTest.class);

    private static SubscriberService subscriberService;

    @BeforeClass
    public static void init() {
        try {
            //тестовая среда
            URL url = new URL("http://smev-mvf.test.gosuslugi.ru:7777/gateway/services/SID0004155?wsdl");
            QName qname = new QName("http://epgu.gosuslugi.ru/lk/subscribe/", "ElkSubscribeService");
            Service service = Service.create(url, qname);
            ElkSubscribeService syncService = service.getPort(ElkSubscribeService.class);

            BindingProvider bindingProvider = (BindingProvider) syncService;
            java.util.List<Handler> handlers = bindingProvider.getBinding().getHandlerChain();
            handlers.add(new MyServiceLogHandler());
            bindingProvider.getBinding().setHandlerChain(handlers);

            SubscriberServiceImpl subscriberServ = new SubscriberServiceImpl();
            subscriberServ.setService(syncService);
            subscriberServ.setObjectFactoryElk(new ObjectFactory());
            subscriberServ.setObjectFactorySmev(new webservice.objects.smev.ObjectFactory());
            subscriberService = subscriberServ;
        } catch (MalformedURLException e) {
            logger.error(e);
        }
    }


    @Test
    public void subscribeToGetData() {
        try {
            assertTrue(subscriberService.subscribeToGetData("eyJhbGciOiJSUzI1NiIsInNidCI6ImFjY2VzcyIsInR5cCI6IkpXVCIsInZlciI6MX0.eyJleHAiOjE0NDY3NDM1MDMsInNjb3BlIjoiaHR0cDpcL1wvZXNpYS5nb3N1c2x1Z2kucnVcL3Vzcl9pbmY_b2lkPTEwMDAzMjE3NjMmbW9kZT13IiwiaXNzIjoiaHR0cDpcL1wvZXNpYS5nb3N1c2x1Z2kucnVcLyIsIm5iZiI6MTQ0NjczOTkwMywidXJuOmVzaWE6c2lkIjoiZDZiYzdmM2MtZTg3Zi00OGQxLTlkNjAtZmMzYjc5YTUwZGQ3IiwidXJuOmVzaWE6c2JqX2lkIjoxMDAwMzIxNzYzLCJjbGllbnRfaWQiOiJQR1UiLCJpYXQiOjE0NDY3Mzk5MDN9.N9v-F2HWxtblEaEH4-ap7P7Jmtp7-0nUXOE32oxT0rwj8-FITR4CDGdYrwlOB1HyhTN85SRsoMtMaZgUAhQyiRUb-oyM0uzBW_so_Bt6teL1EjSGC28RnqVZpwLjKHtpE1IvWM32gN7hb1ClaZQKF4gxYxVEw2awFjNlNK2qOy4Hnt-BzlJmANt0SzwrD6HD5TktVYy0exDeJne9nNupYhOAI5qQbFeUPNTo7TpC3Avbi7BOA4BkdfFeF_DJETJW-8ciz1f61QH6iUH1gFQEBVi12FwzTZtyacLgcag9DhfXObN1W8uuqBKgg227D6EGIMbzfk8s2fKsLC6pCddLgQ",
                    LocalDateTime.now()));
        } catch (ElkServiceException e) {
            logger.error(e);
        }
    }

    @Test
    public void unsubscribeToGetData() {
        try {
            assertTrue(subscriberService.unsubscribeToGetData("eyJhbGciOiJSUzI1NiIsInNidCI6ImFjY2VzcyIsInR5cCI6IkpXVCIsInZlciI6MX0.eyJleHAiOjE0NDY3NDM1MDMsInNjb3BlIjoiaHR0cDpcL1wvZXNpYS5nb3N1c2x1Z2kucnVcL3Vzcl9pbmY_b2lkPTEwMDAzMjE3NjMmbW9kZT13IiwiaXNzIjoiaHR0cDpcL1wvZXNpYS5nb3N1c2x1Z2kucnVcLyIsIm5iZiI6MTQ0NjczOTkwMywidXJuOmVzaWE6c2lkIjoiZDZiYzdmM2MtZTg3Zi00OGQxLTlkNjAtZmMzYjc5YTUwZGQ3IiwidXJuOmVzaWE6c2JqX2lkIjoxMDAwMzIxNzYzLCJjbGllbnRfaWQiOiJQR1UiLCJpYXQiOjE0NDY3Mzk5MDN9.N9v-F2HWxtblEaEH4-ap7P7Jmtp7-0nUXOE32oxT0rwj8-FITR4CDGdYrwlOB1HyhTN85SRsoMtMaZgUAhQyiRUb-oyM0uzBW_so_Bt6teL1EjSGC28RnqVZpwLjKHtpE1IvWM32gN7hb1ClaZQKF4gxYxVEw2awFjNlNK2qOy4Hnt-BzlJmANt0SzwrD6HD5TktVYy0exDeJne9nNupYhOAI5qQbFeUPNTo7TpC3Avbi7BOA4BkdfFeF_DJETJW-8ciz1f61QH6iUH1gFQEBVi12FwzTZtyacLgcag9DhfXObN1W8uuqBKgg227D6EGIMbzfk8s2fKsLC6pCddLgQ"));
        } catch (ElkServiceException e) {
            logger.error(e);
        }
    }
}