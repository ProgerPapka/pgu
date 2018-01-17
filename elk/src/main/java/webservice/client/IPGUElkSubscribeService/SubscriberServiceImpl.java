package webservice.client.IPGUElkSubscribeService;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import com.sun.xml.internal.ws.fault.ServerSOAPFaultException;
import exception.ElkServiceException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDateTime;

import webservice.objects.elk.ElkSubscribeService;
import webservice.objects.elk.elksubscribetype.SubscribeRequest;
import webservice.objects.smev.*;
import webservice.objects.elk.Error;
import webservice.objects.elk.elksubscribetype.UnsubscribeRequest;
import webservice.objects.time.TimeStampUtil;


public class SubscriberServiceImpl implements SubscriberService {

    private static Logger logger = Logger.getLogger(SubscriberServiceImpl.class);

    @Autowired
    private ElkSubscribeService service;
    @Autowired
    private ObjectFactory objectFactorySmev;
    @Autowired
    private webservice.objects.elk.elksubscribetype.ObjectFactory objectFactoryElk;

    @Override
    public boolean subscribeToGetData(String token, LocalDateTime timestamp) throws ElkServiceException {
        try {
            HeaderType header = objectFactorySmev.createHeaderType(); //никакой информации об этом объекте нет
            header.setActor("pgu");
            header.setMessageClass(MessageClassType.REQUEST);
            header.setMessageId("1");
            header.setNodeId("12");
            PacketIdsType packetIdsType = objectFactorySmev.createPacketIdsType();
            PacketIdType packetIdType = objectFactorySmev.createPacketIdType();
            packetIdType.setMessageId("1");
            packetIdType.setSubRequestNumber("13");
            packetIdsType.getId().add(packetIdType);
            header.setPacketIds(packetIdsType);
            header.setTimeStamp(TimeStampUtil.getXMLGregorianCalendar(LocalDateTime.now()));
            BaseMessageType baseMessage = initBaseMessageTypeToSubscrb(token, timestamp);
            BaseMessageType response = service.process(header, baseMessage);
            AppDataType dataResponse = response.getMessageData().getAppData();
            Error error = (Error) dataResponse.getAny().get(0);
            return error.getCode() == 0;
        } catch (ServerSOAPFaultException e) {
            logger.error("Ошибка!", e);
            throw new ElkServiceException(e);
        }

    }

    @Override
    public boolean unsubscribeToGetData(String token) throws ElkServiceException {
        try {
            HeaderType header = objectFactorySmev.createHeaderType();
            header.setActor("pgu");
            header.setMessageClass(MessageClassType.REQUEST);
            header.setMessageId("1");
            header.setNodeId("12");
            PacketIdsType packetIdsType = objectFactorySmev.createPacketIdsType();
            PacketIdType packetIdType = objectFactorySmev.createPacketIdType();
            packetIdType.setMessageId("1");
            packetIdType.setSubRequestNumber("13");
            packetIdsType.getId().add(packetIdType);
            header.setPacketIds(packetIdsType);
            header.setTimeStamp(new XMLGregorianCalendarImpl());
            BaseMessageType baseMessage = initBaseMessageTypeToUnsubcrb(token);
            BaseMessageType response = service.process(header, baseMessage);
            Error error = (Error) response.getMessageData().getAppData().getAny().get(0);
            return error.getCode() == 0;
        } catch (ServerSOAPFaultException e) {
            logger.error("Ошибка!", e);
            throw new ElkServiceException(e);
        }

    }

    public void setService(ElkSubscribeService service) {
        this.service = service;
    }

    public void setObjectFactorySmev(ObjectFactory objectFactorySmev) {
        this.objectFactorySmev = objectFactorySmev;
    }

    public void setObjectFactoryElk(webservice.objects.elk.elksubscribetype.ObjectFactory
                                            objectFactoryElk) {
        this.objectFactoryElk = objectFactoryElk;
    }

    private BaseMessageType initBaseMessageTypeToUnsubcrb(String token) {
        BaseMessageType baseMessage = initBaseMessage();
        //message data
        AppDataType dataType = objectFactorySmev.createAppDataType();
        UnsubscribeRequest unsubscribeRequest = objectFactoryElk.createUnsubscribeRequest();
        unsubscribeRequest.setToken(token);
        dataType.getAny().add(unsubscribeRequest);
        MessageDataType messageDataType = objectFactorySmev.createMessageDataType();
        messageDataType.setAppData(dataType);
        baseMessage.setMessageData(messageDataType);
        return baseMessage;
    }

    private BaseMessageType initBaseMessageTypeToSubscrb(String token, LocalDateTime timestamp) {
        BaseMessageType baseMessage = initBaseMessage();
        //message data
        AppDataType dataType = objectFactorySmev.createAppDataType();
        SubscribeRequest subscribeRequest = objectFactoryElk.createSubscribeRequest();
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
        MessageDataType messageDataType = objectFactorySmev.createMessageDataType();
        messageDataType.setAppData(dataType);
        baseMessage.setMessageData(messageDataType);
        return baseMessage;
    }

    private BaseMessageType initBaseMessage() {
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
        return baseMessage;
    }

}
