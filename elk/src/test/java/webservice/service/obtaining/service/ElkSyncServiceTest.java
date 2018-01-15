package webservice.service.obtaining.service;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import webservice.objects.elk.CreateOrdersRequest;
import webservice.objects.elk.Order;
import webservice.objects.elk.Orders;
import webservice.objects.smev.*;

import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class ElkSyncServiceTest {

    private Logger logger = Logger.getLogger(ElkSyncServiceTest.class);

    private ObjectFactory objectFactorySmev;
    private webservice.objects.elk.ObjectFactory objectFactoryElk;
    private BaseMessageType baseMessageType = null;

    @Before
    public void initMetaData() {
        objectFactorySmev = new ObjectFactory();
        objectFactoryElk = new webservice.objects.elk.ObjectFactory();
        CreateOrdersRequest ordersRequest = objectFactoryElk.createCreateOrdersRequest();
        Orders createOrders = objectFactoryElk.createCreateOrders();
        Order order = new Order();
        order.setUserId("123");
        order.setEServiceCode("456");
        order.setOrderNumber("111");
        createOrders.getOrder().add(order);
        ordersRequest.setOrders(createOrders);
        AppDataType appDataType = objectFactorySmev.createAppDataType();
        appDataType.getAny().add(ordersRequest);
        baseMessageType = initBaseMessageType(appDataType, null);
    }


    @Test
    public void mainTest() {
        try {
            URL url = new URL("http://localhost:1986/elk?wsdl");
            QName qname = new QName("http://service.obtaining.service.webservice/", "ElkSyncServiceImplService");
            Service service = Service.create(url, qname);
            ElkSyncService syncService = service.getPort(ElkSyncService.class);
            logger.info("Делаем запрос!");
            BaseMessageType b = baseMessageType;
            BaseMessageType baseMessageType = syncService.createOrders(
                    new HeaderType(),
                    b
            );
            logger.info("Мы получили ответ!");
        } catch (MalformedURLException e) {
            logger.error(e);
        }
    }

    @Test
    public void testLog4J() {
        logger.info("This's log4j test!");
    }

    private BaseMessageType initBaseMessageType(AppDataType appDataType,
                                                AppDocumentType documentType) {
        BaseMessageType baseMessage = objectFactorySmev.createBaseMessageType();
        //message type
        String senderCode = "IPGU01001"; //эти данные взяты с примера запроса
        String senderName = "ЕПГУ";
        String recipientCode = "IPGU01001";
        String recipientName = "ЕПГУ";
        String originatorCode = "IPGU01001";
        String originatorName = "ЕПГУ";
        String serviceName = "ElkSubscribeServiceV25";
        OrgExternalType sender = objectFactorySmev.createOrgExternalType();
        OrgExternalType recipient = objectFactorySmev.createOrgExternalType();
        OrgExternalType originator = objectFactorySmev.createOrgExternalType();
        LocalDateTime dateTime = LocalDateTime.now();
        XMLGregorianCalendar calendar = XMLGregorianCalendarImpl.createDateTime(
                dateTime.getYear(),
                dateTime.getMonthValue(),
                dateTime.getDayOfMonth(),
                dateTime.getHour(),
                dateTime.getMinute(),
                dateTime.getSecond()
        );
        MessageType messageType = objectFactorySmev.createMessageType();
        sender.setCode(senderCode);
        sender.setName(senderName);
        messageType.setSender(sender);
        recipient.setName(recipientName);
        recipient.setCode(recipientCode);
        messageType.setRecipient(recipient);
        originator.setCode(originatorCode);
        originator.setName(originatorName);
        messageType.setOriginator(originator);
        messageType.setServiceName(serviceName);
        messageType.setTypeCode(TypeCodeType.GSRV);
        messageType.setStatus(StatusType.REQUEST);
        messageType.setDate(calendar);
        messageType.setExchangeType("1");
        baseMessage.setMessage(messageType);
        //message data
        MessageDataType messageData = objectFactorySmev.createMessageDataType();
        messageData.setAppData(appDataType);
        messageData.setAppDocument(documentType);
        baseMessage.setMessageData(messageData);
        return baseMessage;
    }

}