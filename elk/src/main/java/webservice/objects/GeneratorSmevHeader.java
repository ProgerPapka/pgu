package webservice.objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import webservice.objects.smev.*;
import webservice.objects.time.TimeStampUtil;

import java.time.LocalDateTime;

public class GeneratorSmevHeader {

    @Autowired
    @Qualifier("objFactSmev")
    private ObjectFactory objectFactorySmev;

    public HeaderType createHeader(String messageId){
        HeaderType headerType = objectFactorySmev.createHeaderType();

        headerType.setMessageClass(MessageClassType.REQUEST);
        headerType.setMessageId(messageId); // Представляет собой уникальный идентификатор цепочки электронных сообщений (запроса и ответа) в рамках узла СМЭВ.
        headerType.setNodeId("semvID"); //Уникальный идентификатор узла СМЭВ.
        headerType.setTimeStamp(TimeStampUtil.getXMLGregorianCalendar(LocalDateTime.now()));

        PacketIdsType packetIdsType = objectFactorySmev.createPacketIdsType();
        PacketIdType packetIdType = objectFactorySmev.createPacketIdType();
        packetIdType.setSubRequestNumber("");
        packetIdsType.getId().add(packetIdType);
        headerType.setPacketIds(packetIdsType); // об этом элементе нигде ничего не сказано
        headerType.setActor(""); // об этом элементе нигде ничего не сказано

        return headerType;
    }

}
