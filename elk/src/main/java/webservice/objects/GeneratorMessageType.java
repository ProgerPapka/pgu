package webservice.objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import webservice.objects.smev.*;
import webservice.objects.time.TimeStampUtil;

import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDateTime;

public class GeneratorMessageType {

    @Autowired
    @Qualifier("objFactSmev")
    private ObjectFactory objectFactorySmev;

    public MessageType createMessageTypeToSubscribe() {

        String senderCode = "RPGU"; //Данные о системе-инициаторе взаимодействия (Потребителе)
        String senderName = "РПГУ";
        String recipientCode = "IPGU01001"; //Данные о системе-получателе сообщения (Поставщике)
        String recipientName = "ЕПГУ";
        String originatorCode = "IPGU01001"; //Данные о системе,инициировавшей цепочку из нескольких запросов-ответов, объединенных единым процессом в рамках
        String originatorName = "ЕПГУ";
        String serviceName = "ElkSubscribeServiceV25";
        OrgExternalType sender = objectFactorySmev.createOrgExternalType();
        OrgExternalType recipient = objectFactorySmev.createOrgExternalType();
        OrgExternalType originator = objectFactorySmev.createOrgExternalType();
        sender.setCode(senderCode);
        sender.setName(senderName);
        recipient.setName(recipientName);
        recipient.setCode(recipientCode);
        originator.setCode(originatorCode);
        originator.setName(originatorName);

        XMLGregorianCalendar calendar = TimeStampUtil.getXMLGregorianCalendar(LocalDateTime.now());

        MessageType messageType = objectFactorySmev.createMessageType();
        messageType.setSender(sender);
        messageType.setRecipient(recipient);
        messageType.setOriginator(originator); //не обязательный
        messageType.setTypeCode(TypeCodeType.GSRV); // GSRV - в рамках оказания государственных услуг; GFNC - Взаимодействие в рамках исполнениягосударственных функций
        messageType.setStatus(StatusType.REQUEST);
        messageType.setDate(calendar);
        messageType.setServiceCode(""); // Код государственной услуги, в рамках оказания которой осуществляется информационный обмен
        messageType.setCaseNumber(""); // Номер дела в информационной системе-отправителе
        messageType.setServiceName(serviceName); //имя сервиса
        messageType.setExchangeType("1"); // 1 - Взаимодействие с порталами государственных услуг; 2 - Межвед; 0 - Неопределенная категория
        return messageType;
    }

}
