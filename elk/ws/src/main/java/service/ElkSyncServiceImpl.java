package service;

import wsdl.lk.elk.types.CreateOrder;
import wsdl.lk.elk.types.CreateOrders;
import wsdl.lk.elk.types.CreateOrdersRequest;
import wsdl.lk.elk.types.OrderResponse;
import wsdl.smev.*;

import javax.xml.datatype.XMLGregorianCalendar;

public class ElkSyncServiceImpl implements ElkSyncService {

    @Override
    public BaseMessageType createOrders(HeaderType var1, BaseMessageType var2) {
        //message
        String senderCode = var2.getMessage().getSender().getCode();
        String senderName = var2.getMessage().getSender().getName();
        String recipientCode = var2.getMessage().getRecipient().getCode();
        String recipientName = var2.getMessage().getRecipient().getName();
        String originatorCode = var2.getMessage().getOriginator().getCode();
        String originatorName = var2.getMessage().getOriginator().getName();
        String serviceName = var2.getMessage().getServiceName();
        String typeCode = var2.getMessage().getTypeCode().value();
        StatusType statusType = var2.getMessage().getStatus();
        XMLGregorianCalendar calendar = var2.getMessage().getDate();
        String exchangeType = var2.getMessage().getExchangeType();
        //messageData
        CreateOrdersRequest createOrdersRequest = (CreateOrdersRequest)
                var2.getMessageData().getAppData().getAny().get(0);
        CreateOrders orders = createOrdersRequest.getOrders();
        OrderResponse orderResponse = new OrderResponse();
        for (CreateOrder order : orders.getOrder()){
            //TODO получение и запись в РПГУ всех заявлений
        }
        //response
        BaseMessageType var3 = new BaseMessageType();
        var3.setMessage(var2.getMessage());
        AppDataType appDataType = new AppDataType();
        appDataType.getAny().add(orderResponse);
        MessageDataType messageDataType = new MessageDataType();
        messageDataType.setAppData(appDataType);
        var3.setMessageData(messageDataType);
        return var3;
    }

    @Override
    public BaseMessageType updateOrders(HeaderType var1, BaseMessageType var2) {
        return new BaseMessageType();
    }

    @Override
    public BaseMessageType deleteOrders(HeaderType var1, BaseMessageType var2) {
        return null;
    }

    @Override
    public BaseMessageType createInvitations(HeaderType var1, BaseMessageType var2) {
        return null;
    }

    @Override
    public BaseMessageType updateInvitations(HeaderType var1, BaseMessageType var2) {
        return null;
    }

    @Override
    public BaseMessageType createPayments(HeaderType var1, BaseMessageType var2) {
        return null;
    }
}
