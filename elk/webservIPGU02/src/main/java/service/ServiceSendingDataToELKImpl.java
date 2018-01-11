package service;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import wsdl.*;
import wsdl.generated.ArchiveDescription;
import wsdl.generated.FileDescription;
import wsdl.generated.FileDescriptions;
import wsdl.gosuslugi.lk.elk.types.*;
import wsdl.gosuslugi.lk.elk.types.Error;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class ServiceSendingDataToELKImpl implements ServiceSendingDataToELK {

    private static Logger logger = Logger.getLogger(ServiceSendingDataToELKImpl.class);

    @Autowired
    private CreateOrderService service;
    @Autowired
    private ArchiveUtil archiveUtil;

    @Override
    public void sendOrders(List<Order> orders) {
        BaseMessageType baseMessageType = initBaseMessageToSendOrders(orders);
        BaseMessageType response = service.createOrders(new HeaderType(), baseMessageType);
        //TODO переписать правильно проверку ответа. xsd нет!
        Error error = (Error) response.getMessageData().getAppData().getAny().get(0);
        if (error.getCode() == 0) {
            return; //все заявки успешно отправлены
        }
        for (OrderResult result : error.getOrdersResult().getOrderResults()) {
            if (!result.getStatus().equals("0")) {
                return; //тут ошибка в отдельной заявке
            }
        }
    }

    /**
     * Номера соответствуют номера в ЕЛК
     *
     * @param ordersNumber
     */
    @Override
    public void deleteOrders(List<String> ordersNumber) {
        BaseMessageType baseMessageType = initBaseMessageToDeleteOrders(ordersNumber);
        BaseMessageType response = service.deleteOrders(new HeaderType(), baseMessageType);
        //TODO переписать правильно проверку ответа. xsd нет!
        ErrorDelete error = (ErrorDelete) response.getMessageData().getAppData().getAny().get(0);
        if (error.getCode() == 0) {
            return; //все заявки успешно отправлены
        }
        for (DeleteResult result : error.getDeleteResults().getOrderResults()) {
            if (!result.getStatus().equals("0")) {
                return; //тут ошибка в отдельной заявке
            }
        }
    }

    @Override
    public void updateOrders(List<UpdateOrder> orders) {
        BaseMessageType baseMessageType = initBaseMessageToUpdateOrders(orders);
        BaseMessageType response = service.updateOrders(new HeaderType(), baseMessageType);
        //TODO переписать правильно проверку ответа. xsd нет!
        Error error = (Error) response.getMessageData().getAppData().getAny().get(0);
        if (error.getCode() == 0) {
            return; //все заявки успешно отправлены
        }
        for (OrderResult result : error.getOrdersResult().getOrderResults()) {
            if (!result.getStatus().equals("0")) {
                return; //тут ошибка в отдельной заявке
            }
        }
    }

    @Override
    public void sendFilesByOrders(List<String> files, String elkNumber, String statusExtId) {
        BaseMessageType baseMessageType = initBaseMessageToUploadFiles(files, elkNumber, statusExtId);
        BaseMessageType response = service.uploadFiles(new HeaderType(), baseMessageType);
        //TODO переписать правильно проверку ответа
        Error error = (Error) response.getMessageData().getAppData().getAny().get(0);
        if (error.getCode() == 0) {
            return; //все заявки успешно отправлены
        }
    }

    private BaseMessageType initBaseMessageToUploadFiles(List<String> nameFiles,
                                                         String elkNumber, String statusExtId) {
        UploadFiles uploadFiles = new UploadFiles();
        uploadFiles.setElkOrderNumber(elkNumber);
        uploadFiles.setStatusExtId(statusExtId);
        AppDataType appDataType = new AppDataType();
        appDataType.getAny().add(uploadFiles);
        AppDocumentType documentType = new AppDocumentType();
        documentType.setBinaryData(
                archiveUtil.generateArchive(
                        nameFiles,
                        initXmlForArchive(nameFiles,"application/pdf", "archive-description.xml")
                )
        );
        return initBaseMessageType(appDataType, documentType);
    }

    private File initXmlForArchive(List<String> nameFile, String typeFiles, String filePath) {
        ArchiveDescription archiveDescription = new ArchiveDescription();
        FileDescriptions fileDescriptions = new FileDescriptions();
        for (String file : nameFile) {
            FileDescription description = new FileDescription();
            description.setName(file);
            description.setContentType(typeFiles);
            fileDescriptions.getFileDescription().add(description);
        }
        archiveDescription.setFileDescriptions(fileDescriptions);
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                logger.error("Error creation xml file",e);
            }
        }
        try {
            JAXBContext context = JAXBContext.newInstance(ArchiveDescription.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.marshal(archiveDescription, file);
        } catch (JAXBException e) {
            logger.error("JAXB error at marshal",e);
        }

        return file;
    }

    private BaseMessageType initBaseMessageToDeleteOrders(List<String> ordersNumbers) {
        DeleteOrdersRequest deleteOrdersRequest = new DeleteOrdersRequest();
        for (String orderNum : ordersNumbers) {
            deleteOrdersRequest.getElkOrderNumber().add(orderNum);
        }
        AppDataType appDataType = new AppDataType();
        appDataType.getAny().add(deleteOrdersRequest);
        return initBaseMessageType(appDataType);
    }

    private BaseMessageType initBaseMessageToUpdateOrders(List<UpdateOrder> orders) {
        UpdateOrdersRequest ordersRequest = new UpdateOrdersRequest();
        UpdateOrders updateOrders = new UpdateOrders();
        for (UpdateOrder order : orders) {
            updateOrders.getOrder().add(order);
        }
        ordersRequest.setOrders(updateOrders);
        AppDataType appDataType = new AppDataType();
        appDataType.getAny().add(ordersRequest);
        return initBaseMessageType(appDataType);
    }

    private BaseMessageType initBaseMessageToSendOrders(List<Order> orders) {
        CreateOrdersRequest ordersRequest = new CreateOrdersRequest();
        Orders createOrders = new Orders();
        for (Order order : orders) {
            createOrders.getOrder().add(order);
        }
        ordersRequest.setOrders(createOrders);
        AppDataType appDataType = new AppDataType();
        appDataType.getAny().add(ordersRequest);
        return initBaseMessageType(appDataType);
    }

    private BaseMessageType initBaseMessageType(AppDataType appDataType) {
        return initBaseMessageType(appDataType, null);
    }

    private BaseMessageType initBaseMessageType(AppDataType appDataType,
                                                AppDocumentType documentType) {
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
        //message data
        MessageDataType messageData = new MessageDataType();
        messageData.setAppData(appDataType);
        messageData.setAppDocument(documentType);
        baseMessage.setMessageData(messageData);
        return baseMessage;
    }

}
