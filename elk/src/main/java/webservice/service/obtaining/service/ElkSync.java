package webservice.service.obtaining.service;

import com.sun.org.apache.xml.internal.security.c14n.Canonicalizer;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.w3c.dom.Document;
import webservice.objects.elk.*;
import webservice.objects.smev.*;
import webservice.objects.smev.ObjectFactory;

import javax.jws.WebService;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebService(
        endpointInterface = "webservice.service.obtaining.service.ElkSyncService"
)
@XmlSeeAlso({ObjectFactory.class, webservice.objects.include.ObjectFactory.class, webservice.objects.elk.ObjectFactory.class})
public class ElkSync implements ElkSyncService {

    private static Logger logger = Logger.getLogger(ElkSync.class);

    @Autowired
    @Qualifier("objFactElk")
    private webservice.objects.elk.ObjectFactory objectFactoryElk = new webservice.objects.elk.ObjectFactory();
    @Autowired
    @Qualifier("objFactSmev")
    private ObjectFactory objectFactorySmev = new ObjectFactory();

    @Override
    public BaseMessageType createOrders(HeaderType var1, BaseMessageType var2) {
        logger.info("Запрос на запись заявления пришел!!!");
        getMessage(var2.getMessage());
        //messageData
        CreateOrdersRequest createOrdersRequest = (CreateOrdersRequest)
                var2.getMessageData().getAppData().getAny().get(0);
        Orders orders = createOrdersRequest.getOrders();
        OrderResponse orderResponse = objectFactoryElk.createOrderResponse();
        List<String> elkNumbers = new ArrayList<>();
        for (Order order : orders.getOrder()) {
            String userId = order.getUserId();
            String eServiceCode = order.getEServiceCode();
            String serviceTargetCode = order.getServiceTargetCode();
            String userSelectedRegion = order.getUserSelectedRegion();
            String elkNumber = order.getElkOrderNumber();
            elkNumbers.add(elkNumber);
            XMLGregorianCalendar calendar = order.getRequestDate();
            String orderUrl = order.getOrderUrl();
            if (getStatusHistoryList(order.getStatusHistoryList())) {
                orderResponse = createOkResponse(elkNumbers);
            }
        }
        //response
        AppDataType appDataType = objectFactorySmev.createAppDataType();
        appDataType.getAny().add(orderResponse);
        return createResponse(var2.getMessage(), appDataType);
    }

    @Override
    public BaseMessageType updateOrders(HeaderType var1, BaseMessageType var2) {
        getMessage(var2.getMessage());
        //messageData
        UpdateOrdersRequest updateOrdersRequest = (UpdateOrdersRequest)
                var2.getMessageData().getAppData().getAny().get(0);
        UpdateOrders updateOrders = updateOrdersRequest.getOrders();
        OrderResponse orderResponse = objectFactoryElk.createOrderResponse();
        List<String> elkNumbers = new ArrayList<>();
        //get data
        for (UpdateOrder order : updateOrders.getOrder()) {
            String elkNumber = order.getElkOrderNumber();
            elkNumbers.add(elkNumber);
            if (getStatusHistoryList(order.getStatusHistoryList())) {
                orderResponse = createOkResponse(elkNumbers);
            }
        }
        //response
        AppDataType appDataType = objectFactorySmev.createAppDataType();
        appDataType.getAny().add(orderResponse);
        return createResponse(var2.getMessage(), appDataType);
    }

    @Override
    public BaseMessageType deleteOrders(HeaderType var1, BaseMessageType var2) {
        getMessage(var2.getMessage());
        DeleteOrdersRequest deleteOrdersRequest = (DeleteOrdersRequest)
                var2.getMessageData().getAppData().getAny().get(0);
        List<String> elkNumbers = new ArrayList<>();
        for (String elkNumber : deleteOrdersRequest.getElkOrderNumber()) {
            deleteOrder(elkNumber);
        }
        OrderResponse orderResponse = createOkResponse(elkNumbers);
        AppDataType appDataType = objectFactorySmev.createAppDataType();
        appDataType.getAny().add(orderResponse);
        return createResponse(var2.getMessage(), appDataType);
    }

    @Override
    public BaseMessageType createInvitations(HeaderType var1, BaseMessageType var2) {
        getMessage(var2.getMessage());
        CreateInvitations invitations = (CreateInvitations)
                var2.getMessageData().getAppData().getAny().get(0);
        List<String> elkInvitationNumbers = new ArrayList<>();
        getInvitations(invitations.getInvitations(), elkInvitationNumbers);
        InvitationResponse invitationResponse = createOkInvitationResponse(elkInvitationNumbers);
        AppDataType appDataType = objectFactorySmev.createAppDataType();
        appDataType.getAny().add(invitationResponse);
        return createResponse(var2.getMessage(), appDataType);
    }

    @Override
    public BaseMessageType updateInvitations(HeaderType var1, BaseMessageType var2) {
        getMessage(var2.getMessage());
        UpdateInvitations invitations = (UpdateInvitations)
                var2.getMessageData().getAppData().getAny().get(0);
        List<String> elkInvitationNumbers = new ArrayList<>();
        getInvitations(invitations.getInvitations(), elkInvitationNumbers);
        InvitationResponse invitationResponse = createOkInvitationResponse(elkInvitationNumbers);
        AppDataType appDataType = objectFactorySmev.createAppDataType();
        appDataType.getAny().add(invitationResponse);
        return createResponse(var2.getMessage(), appDataType);
    }

    @Override
    public BaseMessageType createPayments(HeaderType var1, BaseMessageType var2) {
        getMessage(var2.getMessage());
        CreatePayments createPayments = (CreatePayments)
                var2.getMessageData().getAppData().getAny().get(0);
        List<String> elkPaymentNumbers = new ArrayList<>();
        for (Payment payment : createPayments.getPayments().getPayment()) {
            String userId = payment.getUserId();
            String elkPaymentNumber = payment.getElkPaymentNumber();
            elkPaymentNumbers.add(elkPaymentNumber);
            XMLGregorianCalendar paymentDate = payment.getPaymentDate();
            String paymentName = payment.getPaymentName();
            double paymentSumma = payment.getPaymentSumma();
            String paymentUrl = payment.getPaymentUrl();
        }
        PaymentResponse paymentResponse = createOkPaymentResponse(elkPaymentNumbers);
        AppDataType appDataType = objectFactorySmev.createAppDataType();
        appDataType.getAny().add(paymentResponse);
        return createResponse(var2.getMessage(), appDataType);
    }

    private void deleteOrder(String elkNumber) {
        //TODO удаление заявления в рпгу
    }

    private void getInvitations(Invitations invitations, List<String> elkInvitationNumbers) {
        for (Invitation invitation : invitations.getInvitation()) {
            String userId = invitation.getUserId();
            String elkInvitationNumber = invitation.getElkInvitationNumber();
            String invitationCreateNumber = invitation.getElkInvitationNumber();
            XMLGregorianCalendar invitationCreateDate = invitation
                    .getInvitationCreateDate();
            XMLGregorianCalendar invitationStartDate = invitation
                    .getInvitationStartDate();
            XMLGregorianCalendar invitationEndDate = invitation
                    .getInvitationEndDate();
            String invitationOrgName = invitation.getInvitationOrgName();
            String invitationAddress = invitation.getInvitationAddress();
            String invitationStatus = invitation.getInvitationStatus();
            String invitationUrl = invitation.getInvitationUrl();
            elkInvitationNumbers.add(elkInvitationNumber);
        }
    }

    private OrderResponse createOkResponse(List<String> elkNumbers) {
        OrderResponse orderResponse = objectFactoryElk.createOrderResponse();
        orderResponse.setCode("0");
        orderResponse.setMessage("OK");
        OrderResponseItems orderResponseItems = objectFactoryElk.createOrderResponseItems();
        for (String elkNumber : elkNumbers) {
            OrderResponseItem orderResponseItem = objectFactoryElk.createOrderResponseItem();
            orderResponseItem.setElkOrderNumber(elkNumber);
            orderResponseItem.setMessage("OK");
            orderResponseItem.setStatus("0");
            orderResponseItems.getOrder().add(orderResponseItem);
        }
        orderResponse.getOrders().add(orderResponseItems);
        return orderResponse;
    }

    private PaymentResponse createOkPaymentResponse(List<String> elkPaymentNumbers) {
        PaymentResponse paymentResponse = objectFactoryElk.createPaymentResponse();
        paymentResponse.setCode("0");
        paymentResponse.setMessage("OK");
        PaymentResponseItems paymentResponseItems = objectFactoryElk.createPaymentResponseItems();
        for (String elkPaymentNumber : elkPaymentNumbers) {
            PaymentResponseItem paymentResponseItem = objectFactoryElk.createPaymentResponseItem();
            paymentResponseItem.setElkPaymentNumber(elkPaymentNumber);
            paymentResponseItem.setMessage("OK");
            paymentResponseItem.setStatus("0");
            paymentResponseItems.getPayment().add(paymentResponseItem);
        }
        paymentResponse.getPayments().add(paymentResponseItems);
        return paymentResponse;
    }

    private InvitationResponse createOkInvitationResponse(List<String> elkNumbers) {
        InvitationResponse invitationResponse = objectFactoryElk.createInvitationResponse();
        invitationResponse.setCode("0");
        invitationResponse.setMessage("OK");
        InvitationResponseItems invitationResponseItems = objectFactoryElk.createInvitationResponseItems();
        for (String elkNumber : elkNumbers) {
            InvitationResponseItem invitationResponseItem = objectFactoryElk.createInvitationResponseItem();
            invitationResponseItem.setElkInvitationNumber(elkNumber);
            invitationResponseItem.setMessage("OK");
            invitationResponseItem.setStatus("0");
            invitationResponseItems.getInvitation().add(invitationResponseItem);
        }
        invitationResponse.getInvitations().add(invitationResponseItems);
        return invitationResponse;
    }

    private boolean getStatusHistoryList(List<StatusHistoryList> lists) {
        //statuses
        for (StatusHistoryList statusHistoryList : lists) {
            for (StatusHistory statusHistory : statusHistoryList.getStatusHistory()) {
                String status = statusHistory.getStatus();
                String statusExtId = statusHistory.getStatusExtId();
                XMLGregorianCalendar calendar = statusHistory.getStatusDate();
                String statusComment = statusHistory.getStatusComment();
                String statusName = statusHistory.getStatusName();
                for (Files files : statusHistory.getFiles()) {
                    for (File file : files.getFile()) {
                        file.getFileName();
                        file.getFileSize();
                        file.getFileUrl();
                        file.getMimeType();
                    }
                }
            }
        }
        return true;
    }

    private void getMessage(MessageType messageType) {
        //message
        logger.info("Получаем мета данные о сообщении");
        String senderCode = messageType.getSender().getCode();
        String senderName = messageType.getSender().getName();
        String recipientCode = messageType.getRecipient().getCode();
        String recipientName = messageType.getRecipient().getName();
        String originatorCode = messageType.getOriginator().getCode();
        String originatorName = messageType.getOriginator().getName();
        String serviceName = messageType.getServiceName();
        String typeCode = messageType.getTypeCode().value();
        StatusType statusType = messageType.getStatus();
        XMLGregorianCalendar calendar = messageType.getDate();
        String exchangeType = messageType.getExchangeType();
        logger.info("Получили мета данные о сообщении!");
    }

    private BaseMessageType createResponse(MessageType messageType, AppDataType appDataType) {
        BaseMessageType var3 = objectFactorySmev.createBaseMessageType();
        var3.setMessage(messageType);
        MessageDataType messageDataType = objectFactorySmev.createMessageDataType();
        messageDataType.setAppData(appDataType);
        var3.setMessageData(messageDataType);
        String cur;
        try {
            cur = this.marshall(var3, BaseMessageType.class, "http://smev.gosuslugi.ru/rev120315", "BaseMessage");
        } catch (SOAPException | JAXBException | ParserConfigurationException e) {
            logger.error(e);
        } catch (IOException e) {
            logger.error("IOException", e);
        }
        return var3;
    }

    private SOAPMessage toSOAPMessage(Document doc) throws Exception {
        Canonicalizer c14n =
                Canonicalizer.getInstance(Canonicalizer.ALGO_ID_C14N_WITH_COMMENTS);
        byte[] canonicalMessage = c14n.canonicalizeSubtree(doc);
        ByteArrayInputStream in = new ByteArrayInputStream(canonicalMessage);
        MessageFactory factory = MessageFactory.newInstance();
        return factory.createMessage(null, in);
    }

    public SOAPMessage marshallToSoap(Document document) throws SOAPException {
        SOAPMessage soapMessage;

        if (document.getElementsByTagNameNS("http://schemas.xmlsoap.org/soap/envelope/", "Envelope").getLength() != 0) {
            try {
                soapMessage = toSOAPMessage(document);
            } catch (Exception e) {
                return null;
            }
        } else {
            soapMessage = MessageFactory.newInstance().createMessage();
            soapMessage.getSOAPBody().addDocument(document);
        }
        return soapMessage;
    }

    private JAXBElement getJaxbElement(Class<?> clazz, String namespace, String mainTag, Object object) {
        return new JAXBElement(new QName(namespace, mainTag), clazz, null, object);
    }

    public Document getXMLDocument(Object objectMessage, Class<?> clazz, String namespace, String mainTag) throws JAXBException, ParserConfigurationException {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = builder.newDocument();

        Marshaller marshaller = JAXBContext.newInstance(ObjectFactory.class, webservice.objects.include.ObjectFactory.class, webservice.objects.elk.ObjectFactory.class)
                .createMarshaller();

        marshaller.marshal(getJaxbElement(clazz, namespace, mainTag, objectMessage), document);

        return document;
    }

    public String marshall(Document document) throws SOAPException, IOException {
        SOAPMessage soapMessage = marshallToSoap(document);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        soapMessage.writeTo(outputStream);

        return outputStream.toString("UTF-8");
    }

    public String marshall(Object objectMessage, Class<?> clazz, String namespace, String mainTag) throws SOAPException, JAXBException, ParserConfigurationException, IOException {
        return marshall(getXMLDocument(objectMessage, clazz, namespace, mainTag));
    }

}
