package webservice.client.IPGUElkSubscribeService;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import webservice.client.IPGU02.ServiceSendingDataToELK;
import webservice.client.IPGU02.ServiceSendingDataToELKImpl;
import webservice.objects.elk.ElkSubscribeService;
import webservice.objects.elk.elksubscribetype.ObjectFactory;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
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
            QName qname = new QName("http://epgu.gosuslugi.ru/lk/subscribe/", "QueryOrderService");
            Service service = Service.create(url, qname);
            ElkSubscribeService syncService = service.getPort(ElkSubscribeService.class);
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
        assertTrue(subscriberService.subscribeToGetData("token", LocalDateTime.now()));
    }

    @Test
    public void unsubscribeToGetData() {
        assertTrue(subscriberService.unsubscribeToGetData("token"));
    }
}