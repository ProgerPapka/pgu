package webservice.client.IPGU02;

import com.sun.xml.internal.ws.fault.ServerSOAPFaultException;
import exception.ElkServiceException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import webservice.objects.GeneratorMessageType;
import webservice.objects.GeneratorSmevHeader;
import webservice.objects.elk.*;
import webservice.objects.elk.Error;
import webservice.objects.archive.FileDescription;
import webservice.objects.archive.FileDescriptions;
import webservice.objects.smev.*;
import webservice.objects.archive.ArchiveDescription;
import webservice.objects.smev.ObjectFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ServiceSendingDataToELKImpl implements ServiceSendingDataToELK {

    private static Logger logger = Logger.getLogger(ServiceSendingDataToELKImpl.class);

    @Autowired
    private CreateOrderService service;
    @Autowired
    private ArchiveUtil archiveUtil;
    @Autowired
    @Qualifier("objFactSmev")
    private ObjectFactory objectFactorySmev;
    @Autowired
    @Qualifier("objFactElk")
    private webservice.objects.elk.ObjectFactory objectFactoryElk;
    @Qualifier("objFactArch")
    private webservice.objects.archive.ObjectFactory objectFactoryArchive;
    @Autowired
    private GeneratorMessageType generatorMessageType;
    @Autowired
    private GeneratorSmevHeader generatorSmevHeader;

    @Override
    public void sendOrders(List<Order> orders) throws ElkServiceException {
        try {
            BaseMessageType baseMessageType = initBaseMessageToSendOrders(orders);
            HeaderType headerType = generatorSmevHeader.createHeader("");
            //TODO переписать headerType - ничего об этом объекте нигде не сказано
            BaseMessageType response = service.createOrders(headerType, baseMessageType);
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
        } catch (ServerSOAPFaultException e) {
            logger.error("Ошибка!", e);
            throw new ElkServiceException(e);
        }
    }

    /**
     * Номера соответствуют номера в ЕЛК
     *
     * @param ordersNumber
     */
    @Override
    public void deleteOrders(List<String> ordersNumber) throws ElkServiceException {
        try {
            BaseMessageType baseMessageType = initBaseMessageToDeleteOrders(ordersNumber);
            HeaderType headerType = generatorSmevHeader.createHeader("");
            BaseMessageType response = service.deleteOrders(headerType, baseMessageType);
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
        } catch (ServerSOAPFaultException e) {
            logger.error("Ошибка!", e);
            throw new ElkServiceException(e);

        }
    }

    @Override
    public void updateOrders(List<UpdateOrder> orders) throws ElkServiceException {
        try {
            BaseMessageType baseMessageType = initBaseMessageToUpdateOrders(orders);
            HeaderType headerType = generatorSmevHeader.createHeader("");
            BaseMessageType response = service.updateOrders(headerType, baseMessageType);
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
        } catch (ServerSOAPFaultException e) {
            logger.error("Ошибка!", e);
            throw new ElkServiceException(e);
        }
    }

    @Override
    public void sendFilesByOrders(List<String> files, String elkNumber, String statusExtId) throws ElkServiceException {
        try {
            BaseMessageType baseMessageType = initBaseMessageToUploadFiles(files, elkNumber, statusExtId);
            HeaderType headerType = generatorSmevHeader.createHeader("");
            BaseMessageType response = service.uploadFiles(headerType, baseMessageType);
            //TODO переписать правильно проверку ответа
            Error error = (Error) response.getMessageData().getAppData().getAny().get(0);
            if (error.getCode() == 0) {
                return; //все заявки успешно отправлены
            }
        } catch (ServerSOAPFaultException e) {
            logger.error("Ошибка!", e);
            throw new ElkServiceException(e);
        }
    }

    public void setService(CreateOrderService service) {
        this.service = service;
    }

    public void setArchiveUtil(ArchiveUtil archiveUtil) {
        this.archiveUtil = archiveUtil;
    }

    public void setObjectFactorySmev(ObjectFactory objectFactorySmev) {
        this.objectFactorySmev = objectFactorySmev;
    }

    public void setObjectFactoryElk(webservice.objects.elk.ObjectFactory objectFactoryElk) {
        this.objectFactoryElk = objectFactoryElk;
    }

    public void setObjectFactoryArchive(webservice.objects.archive.ObjectFactory objectFactoryArchive) {
        this.objectFactoryArchive = objectFactoryArchive;
    }

    public void setGeneratorMessageType(GeneratorMessageType generatorMessageType) {
        this.generatorMessageType = generatorMessageType;
    }

    public void setGeneratorSmevHeader(GeneratorSmevHeader generatorSmevHeader) {
        this.generatorSmevHeader = generatorSmevHeader;
    }

    private BaseMessageType initBaseMessageToUploadFiles(List<String> nameFiles,
                                                         String elkNumber, String statusExtId) {
        UploadFiles uploadFiles = objectFactoryElk.createUploadFiles();
        uploadFiles.setElkOrderNumber(elkNumber);
        uploadFiles.setStatusExtId(statusExtId);
        AppDataType appDataType = objectFactorySmev.createAppDataType();
        appDataType.getAny().add(uploadFiles);
        AppDocumentType documentType = objectFactorySmev.createAppDocumentType();
        documentType.setRequestCode("1");
        documentType.setBinaryData(
                archiveUtil.generateArchive(
                        nameFiles,
                        initXmlForArchive(nameFiles, "application/pdf", "archive-description.xml")
                )
        );
        return initBaseMessageType(appDataType, documentType);
    }

    private File initXmlForArchive(List<String> nameFile, String typeFiles, String filePath) {
        ArchiveDescription archiveDescription = objectFactoryArchive.createArchiveDescription();
        FileDescriptions fileDescriptions = objectFactoryArchive.createFileDescriptions();
        for (String file : nameFile) {
            FileDescription description = objectFactoryArchive.createFileDescription();
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
                logger.error("Error creation xml file", e);
            }
        }
        try {
            JAXBContext context = JAXBContext.newInstance(ArchiveDescription.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.marshal(archiveDescription, file);
        } catch (JAXBException e) {
            logger.error("JAXB error at marshal", e);
        }

        return file;
    }

    private BaseMessageType initBaseMessageToDeleteOrders(List<String> ordersNumbers) {
        DeleteOrdersRequest deleteOrdersRequest = objectFactoryElk.createDeleteOrdersRequest();
        for (String orderNum : ordersNumbers) {
            deleteOrdersRequest.getElkOrderNumber().add(orderNum);
        }
        AppDataType appDataType = objectFactorySmev.createAppDataType();
        appDataType.getAny().add(deleteOrdersRequest);
        return initBaseMessageType(appDataType);
    }

    private BaseMessageType initBaseMessageToUpdateOrders(List<UpdateOrder> orders) {
        UpdateOrdersRequest ordersRequest = objectFactoryElk.createUpdateOrdersRequest();
        UpdateOrders updateOrders = objectFactoryElk.createUpdateOrders();
        for (UpdateOrder order : orders) {
            updateOrders.getOrder().add(order);
        }
        ordersRequest.setOrders(updateOrders);
        AppDataType appDataType = objectFactorySmev.createAppDataType();
        appDataType.getAny().add(ordersRequest);
        return initBaseMessageType(appDataType);
    }

    private BaseMessageType initBaseMessageToSendOrders(List<Order> orders) {
        CreateOrdersRequest ordersRequest = objectFactoryElk.createCreateOrdersRequest();
        Orders createOrders = objectFactoryElk.createCreateOrders();
        for (Order order : orders) {
            createOrders.getOrder().add(order);
        }
        ordersRequest.setOrders(createOrders);
        AppDataType appDataType = objectFactorySmev.createAppDataType();
        appDataType.getAny().add(ordersRequest);
        return initBaseMessageType(appDataType);
    }

    private BaseMessageType initBaseMessageType(AppDataType appDataType) {
        return initBaseMessageType(appDataType, null);
    }



    private BaseMessageType initBaseMessageType(AppDataType appDataType,
                                                AppDocumentType documentType) {
        BaseMessageType baseMessage = objectFactorySmev.createBaseMessageType();
        //message type
        MessageType messageType = generatorMessageType.createMessageTypeToSubscribe();
        baseMessage.setMessage(messageType);
        //message data
        MessageDataType messageData = objectFactorySmev.createMessageDataType();
        messageData.setAppData(appDataType);
        messageData.setAppDocument(documentType);
        baseMessage.setMessageData(messageData);
        return baseMessage;
    }

}
