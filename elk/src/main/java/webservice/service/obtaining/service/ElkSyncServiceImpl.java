package webservice.service.obtaining.service;

import org.springframework.beans.factory.annotation.Autowired;
import webservice.objects.elk.*;
import webservice.objects.smev.*;
import webservice.objects.smev.ObjectFactory;

import javax.jws.WebService;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Collections;
import java.util.List;

@WebService(
        endpointInterface = "webservice.service.obtaining.service.ElkSyncService"
)
public class ElkSyncServiceImpl implements ElkSyncService {

    @Autowired
    private webservice.objects.elk.ObjectFactory objectFactoryElk;
    @Autowired
    private ObjectFactory objectFactorySmev;

    @Override
    public BaseMessageType createOrders(HeaderType var1, BaseMessageType var2) {
        getMessage(var2.getMessage());
        //messageData
        CreateOrdersRequest createOrdersRequest = (CreateOrdersRequest)
                var2.getMessageData().getAppData().getAny().get(0);
        Orders orders = createOrdersRequest.getOrders();
        OrderResponse orderResponse = objectFactoryElk.createOrderResponse();
        List<String> elkNumbers = Collections.emptyList();
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
        List<String> elkNumbers = Collections.emptyList();
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
        List<String> elkNumbers = Collections.emptyList();
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
        List<String> elkInvitationNumbers = Collections.emptyList();
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
        List<String> elkInvitationNumbers = Collections.emptyList();
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
        List<String> elkPaymentNumbers = Collections.emptyList();
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
        AppDataType appDataType =objectFactorySmev.createAppDataType();
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
    }

    private BaseMessageType createResponse(MessageType messageType, AppDataType appDataType) {
        BaseMessageType var3 = objectFactorySmev.createBaseMessageType();
        var3.setMessage(messageType);
        MessageDataType messageDataType = objectFactorySmev.createMessageDataType();
        messageDataType.setAppData(appDataType);
        var3.setMessageData(messageDataType);
        return var3;
    }
}
