package webservice.service.obtaining.service;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import org.apache.log4j.Logger;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import webservice.objects.elk.*;
import webservice.objects.smev.*;
import webservice.objects.smev.ObjectFactory;

import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDateTime;

public class ElkSyncServiceTest {

    private Logger logger = Logger.getLogger(ElkSyncServiceTest.class);

    private static ObjectFactory objectFactorySmev;
    private static webservice.objects.elk.ObjectFactory objectFactoryElk;
    private static BaseMessageType createOrders = null;
    private static BaseMessageType updateOrders = null;
    private static BaseMessageType deleteOrders = null;
    private static BaseMessageType createInvitations = null;
    private static BaseMessageType updateInvitations = null;
    private static BaseMessageType createPayments = null;
    private static ElkSyncService syncService;

    @BeforeClass
    public static void initMetaData() {
        objectFactorySmev = new ObjectFactory();
        objectFactoryElk = new webservice.objects.elk.ObjectFactory();
        createOrders = initCreateOrder();
        updateOrders = initUpdateOrder();
        deleteOrders = initDeleteOrder();
        createInvitations = initCreateInvitations();
        updateInvitations = initUpdateInvitations();
        createPayments = initCreatePayments();
        ElkSyncService_Service elkSyncService_service = new ElkSyncService_Service();
        syncService = elkSyncService_service.getElkSyncService();
    }


    @Test
    public void clientTestCreateOrders() {
        BaseMessageType message = createOrders;
        logger.info("Отправляем запрос на создание заявления");
        BaseMessageType response = syncService.createOrders(new HeaderType(), message);
        logger.info("Получили ответ");
        logger.info(response.getMessageData().getAppData().getAny().get(0));
        assertNotNull(response);
    }

    @Test
    public void clientTestUpdateOrders(){
        BaseMessageType message = updateOrders;
        logger.info("Отправляем запрос на обновление данных по заявлению");
        BaseMessageType response = syncService.updateOrders(new HeaderType(), message);
        logger.info("Получили ответ");
        logger.info(response.getMessageData().getAppData().getAny().get(0));
        assertNotNull(response);
    }

    @Test
    public void clientTestDeleteOrders(){
        BaseMessageType message = deleteOrders;
        logger.info("Отправляем запрос на удаление заявления");
        BaseMessageType response = syncService.deleteOrders(new HeaderType(), message);
        logger.info("Получили ответ");
        logger.info(response.getMessageData().getAppData().getAny().get(0));
        assertNotNull(response);
    }

    @Test
    public void clientTestCreateInvitations(){
        BaseMessageType message = createInvitations;
        logger.info("Отправляем запрос на создание записи на прием");
        BaseMessageType response = syncService.createInvitations(new HeaderType(), message);
        logger.info("Получили ответ");
        logger.info(response.getMessageData().getAppData().getAny().get(0));
        assertNotNull(response);
    }

    @Test
    public void clientTestUpdateInvitations(){
        BaseMessageType message = updateInvitations;
        logger.info("Отправляем запрос на обновление данных по записи на прием");
        BaseMessageType response = syncService.updateInvitations(new HeaderType(), message);
        logger.info("Получили ответ");
        logger.info(response.getMessageData().getAppData().getAny().get(0));
        assertNotNull(response);
    }

    @Test
    public void clientTestCreatePayments(){
        BaseMessageType message = createPayments;
        logger.info("Отправляем запрос на создание плятежа");
        BaseMessageType response = syncService.createPayments(new HeaderType(), message);
        logger.info("Получили ответ");
        logger.info(response.getMessageData().getAppData().getAny().get(0));
        assertNotNull(response);
    }

    @Test
    public void testLog4J() {
        logger.info("This's log4j test!");
    }

    //методы генерации запросов на сервис
    private static BaseMessageType initCreatePayments() {
        CreatePayments createPayments = objectFactoryElk.createCreatePayments();
        Payments payments = objectFactoryElk.createPayments();
        Payment payment = objectFactoryElk.createPayment();
        payment.setElkPaymentNumber("1");
        payment.setPaymentDate(new XMLGregorianCalendarImpl());
        payment.setPaymentName("Client payment");
        payment.setPaymentSumma(522.5);
        payment.setUserId("2");
        payment.setPaymentUrl("http://payment");
        payments.getPayment().add(payment);
        createPayments.setPayments(payments);
        AppDataType appDataType = objectFactorySmev.createAppDataType();
        appDataType.getAny().add(createPayments);
        return initBaseMessageType(appDataType);
    }

    private static BaseMessageType initUpdateInvitations() {
        UpdateInvitations updateInvitations = objectFactoryElk.createUpdateInvitations();
        Invitations invitations = objectFactoryElk.createInvitations();
        Invitation invitation = objectFactoryElk.createInvitation();
        invitation.setElkInvitationNumber("1");
        invitation.setInvitationAddress("2");
        invitation.setInvitationCreateDate(new XMLGregorianCalendarImpl());
        invitation.setInvitationEndDate(new XMLGregorianCalendarImpl());
        invitation.setInvitationStartDate(new XMLGregorianCalendarImpl());
        invitation.setInvitationOrgName("3");
        invitation.setInvitationStatus("Ok");
        invitation.setInvitationUrl("http://invitation");
        invitation.setUserId("222");
        invitations.getInvitation().add(invitation);
        updateInvitations.setInvitations(invitations);
        AppDataType appDataType = objectFactorySmev.createAppDataType();
        appDataType.getAny().add(updateInvitations);
        return initBaseMessageType(appDataType);
    }

    private static BaseMessageType initCreateInvitations() {
        CreateInvitations createInvitations = objectFactoryElk.createCreateInvitations();
        Invitations invitations = objectFactoryElk.createInvitations();
        Invitation invitation = objectFactoryElk.createInvitation();
        invitation.setElkInvitationNumber("1");
        invitation.setInvitationAddress("2");
        invitation.setInvitationCreateDate(new XMLGregorianCalendarImpl());
        invitation.setInvitationEndDate(new XMLGregorianCalendarImpl());
        invitation.setInvitationStartDate(new XMLGregorianCalendarImpl());
        invitation.setInvitationOrgName("3");
        invitation.setInvitationStatus("Ok");
        invitation.setInvitationUrl("http://invitation");
        invitation.setUserId("222");
        invitations.getInvitation().add(invitation);
        createInvitations.setInvitations(invitations);
        AppDataType appDataType = objectFactorySmev.createAppDataType();
        appDataType.getAny().add(createInvitations);
        return initBaseMessageType(appDataType);
    }

    private static BaseMessageType initDeleteOrder() {
        DeleteOrdersRequest deleteOrdersRequest = objectFactoryElk.createDeleteOrdersRequest();
        deleteOrdersRequest.getElkOrderNumber().add("2");
        AppDataType appDataType = objectFactorySmev.createAppDataType();
        appDataType.getAny().add(deleteOrdersRequest);
        return initBaseMessageType(appDataType);
    }

    private static BaseMessageType initUpdateOrder() {
        UpdateOrdersRequest ordersRequest = objectFactoryElk.createUpdateOrdersRequest();
        UpdateOrders updateOrders = objectFactoryElk.createUpdateOrders();
        UpdateOrder order = objectFactoryElk.createUpdateOrder();
        order.setElkOrderNumber("12");
        order.getStatusHistoryList().add(initStatusHistoryList());
        updateOrders.getOrder().add(order);
        ordersRequest.setOrders(updateOrders);
        AppDataType appDataType = objectFactorySmev.createAppDataType();
        appDataType.getAny().add(ordersRequest);
        return initBaseMessageType(appDataType);
    }

    private static BaseMessageType initCreateOrder() {
        CreateOrdersRequest ordersRequest = objectFactoryElk.createCreateOrdersRequest();
        Orders createOrders = objectFactoryElk.createCreateOrders();
        Order order = objectFactoryElk.createCreateOrder();
        order.setUserId("123");
        order.setEServiceCode("456");
        order.setOrderNumber("111");
        createOrders.getOrder().add(order);
        ordersRequest.setOrders(createOrders);
        AppDataType appDataType = objectFactorySmev.createAppDataType();
        appDataType.getAny().add(ordersRequest);
        return initBaseMessageType(appDataType);
    }

    private static StatusHistoryList initStatusHistoryList() {
        //statuses
        StatusHistoryList statusHistoryList = objectFactoryElk.createStatusHistoryList();
        StatusHistory statusHistory = objectFactoryElk.createStatusHistory();
        statusHistory.setStatus("Принято");
        statusHistory.setStatusComment("Только получили");
        statusHistory.setStatusDate(new XMLGregorianCalendarImpl());
        statusHistory.setStatusExtId("1");
        statusHistory.setStatusName("Status");
        return statusHistoryList;
    }

    private static BaseMessageType initBaseMessageType(AppDataType appDataType) {
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
        messageData.setAppDocument(null);
        baseMessage.setMessageData(messageData);
        return baseMessage;
    }

}