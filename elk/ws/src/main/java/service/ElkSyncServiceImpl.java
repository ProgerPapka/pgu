package service;

import wsdl.smev.BaseMessageType;
import wsdl.smev.HeaderType;
import wsdl.smev.StatusType;

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
        return new BaseMessageType();
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
