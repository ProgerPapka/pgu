package webservice.client.IPGU02;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import webservice.client.IPGUElkSubscribeService.SubscriberServiceTest;
import webservice.objects.elk.*;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ServiceSendingDataToELKTest {

    private static Logger logger = Logger.getLogger(SubscriberServiceTest.class);

    private static ServiceSendingDataToELK sendingDataToELK;

    @BeforeClass
    public static void init() {
        try {
            //тестовая среда
            URL url = new URL("http://smev-mvf.test.gosuslugi.ru:7777/gateway/services/SID0004763/wsdl");
            QName qname = new QName("http://gosuslugi.ru/epguapi/ws/v25/", "CreateOrderService");
            Service service = Service.create(url, qname);
            CreateOrderService createOrderService = service.getPort(CreateOrderService.class);
            ServiceSendingDataToELKImpl serviceSendingDataToELK = new ServiceSendingDataToELKImpl();
            serviceSendingDataToELK.setService(createOrderService);
            serviceSendingDataToELK.setArchiveUtil(new ArchiveUtil());
            serviceSendingDataToELK.setObjectFactoryArchive(new webservice.objects.archive.ObjectFactory());
            serviceSendingDataToELK.setObjectFactoryElk(new ObjectFactory());
            serviceSendingDataToELK.setObjectFactorySmev(new webservice.objects.smev.ObjectFactory());
            sendingDataToELK = serviceSendingDataToELK;
        } catch (MalformedURLException e) {
            logger.error(e);
        }
    }

    @Test
    public void sendOrders() {
        List<Order> list = new ArrayList<>();
        Order order = new Order();
        order.setUserId("22");
        order.setOrderNumber("1");
        order.setEServiceCode("1");
        order.setRequestDate(new XMLGregorianCalendarImpl());
        list.add(order);
        sendingDataToELK.sendOrders(list);
    }

    @Test
    public void deleteOrders() {
        List<String> list = Arrays.asList("12","2313","2131");
        sendingDataToELK.deleteOrders(list);
    }

    @Test
    public void updateOrders() {
        List<UpdateOrder> orders = new ArrayList<>();
        UpdateOrder updateOrder = new UpdateOrder();
        updateOrder.setElkOrderNumber("12");
        StatusHistoryList statusHistoryList = new StatusHistoryList();
        StatusHistory statusHistory = new StatusHistory();
        statusHistory.setStatus("Ok");
        statusHistory.setStatusName("statusOk");
        statusHistory.setStatusExtId("0");
        statusHistory.setStatusDate(new XMLGregorianCalendarImpl());
        statusHistoryList.getStatusHistory().add(statusHistory);
        updateOrder.getStatusHistoryList().add(statusHistoryList);
        orders.add(updateOrder);
        sendingDataToELK.updateOrders(orders);
    }

    @Test
    public void sendFilesByOrders() {
        List<String> fileNames = new ArrayList<>();
        sendingDataToELK.sendFilesByOrders(fileNames,"12","0");
    }
}