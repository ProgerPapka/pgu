package service;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import org.springframework.beans.factory.annotation.Autowired;
import wsdl.gosuslugi.epgu.lk.common.types.Error;
import wsdl.gosuslugi.epgu.lk.subscribe.types.elksubscribeservicetypes.SubscribeRequest;
import wsdl.gosuslugi.epgu.lk.subscribe.types.elksubscribeservicetypes.UnsubscribeRequest;
import wsdl.gosuslugi.smev.rev120315.*;
import wsdl.gosuslugi.epgu.lk.subscribe.ElkSubscribeService;

import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDateTime;

public class SubscriberServiceImpl implements SubscriberService {

    @Autowired
    private ElkSubscribeService service;

    @Override
    public boolean subscribeToGetData(String token, LocalDateTime timestamp) {
        HeaderType header = new HeaderType(); //никакой информации об этом объекте нет
        BaseMessageType baseMessage = initBaseMessageTypeToSubscrb(token, timestamp);
        BaseMessageType response = service.process(header, baseMessage);
        AppDataType dataResponse = response.getMessageData().getAppData();
        Error error = (Error) dataResponse.getAny().get(0);
        return error.getErrorCode() == 0;
    }

    @Override
    public boolean unsubscribeToGetData(String token) {
        HeaderType header = new HeaderType();
        BaseMessageType baseMessage = initBaseMessageTypeToUnsubcrb(token);
        BaseMessageType response = service.process(header, baseMessage);
        Error error = (Error) response.getMessageData().getAppData().getAny().get(0);
        return error.getErrorCode() == 0;
    }

    private BaseMessageType initBaseMessageTypeToUnsubcrb(String token) {
        BaseMessageType baseMessage = initBaseMessage();
        //message data
        AppDataType dataType = new AppDataType();
        UnsubscribeRequest unsubscribeRequest = new UnsubscribeRequest();
        unsubscribeRequest.setToken(token);
        dataType.getAny().add(unsubscribeRequest);
        MessageDataType messageDataType = new MessageDataType();
        messageDataType.setAppData(dataType);
        baseMessage.setMessageData(messageDataType);
        return baseMessage;
    }

    private BaseMessageType initBaseMessageTypeToSubscrb(String token, LocalDateTime timestamp) {
        BaseMessageType baseMessage = initBaseMessage();
        //message data
        AppDataType dataType = new AppDataType();
        SubscribeRequest subscribeRequest = new SubscribeRequest();
        subscribeRequest.setToken(token);
        subscribeRequest.setSinceTime(
                XMLGregorianCalendarImpl.createDateTime(
                        timestamp.getYear(),
                        timestamp.getMonthValue(),
                        timestamp.getDayOfMonth(),
                        timestamp.getHour(),
                        timestamp.getMinute(),
                        timestamp.getSecond()
                ));
        dataType.getAny().add(subscribeRequest);
        MessageDataType messageDataType = new MessageDataType();
        messageDataType.setAppData(dataType);
        baseMessage.setMessageData(messageDataType);
        return baseMessage;
    }

    private BaseMessageType initBaseMessage() {
        BaseMessageType baseMessage = new BaseMessageType();
        //message type
        String senderCode = "IPGU01001"; //эти данные взяты с примера запроса
        String senderName = "ЕПГУ";
        String recipientCode = "IPGU01001";
        String recipientName = "ЕПГУ";
        String originatorCode = "IPGU01001";
        String originatorName = "ЕПГУ";
        String serviceName = "ElkSubscribeServiceV25";
        OrgExternalType sender = new OrgExternalType();
        OrgExternalType recipient = new OrgExternalType();
        OrgExternalType originator = new OrgExternalType();
        LocalDateTime dateTime = LocalDateTime.now();
        XMLGregorianCalendar calendar = XMLGregorianCalendarImpl.createDateTime(
                dateTime.getYear(),
                dateTime.getMonthValue(),
                dateTime.getDayOfMonth(),
                dateTime.getHour(),
                dateTime.getMinute(),
                dateTime.getSecond()
        );
        MessageType messageType = new MessageType();
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
        return baseMessage;
    }

}
