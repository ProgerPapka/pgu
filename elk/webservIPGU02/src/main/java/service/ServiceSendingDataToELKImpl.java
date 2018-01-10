package service;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import org.springframework.beans.factory.annotation.Autowired;
import wsdl.*;
import wsdl.gosuslugi.lk.elk.types.*;
import wsdl.gosuslugi.lk.elk.types.Error;

import javax.xml.datatype.XMLGregorianCalendar;
import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

public class ServiceSendingDataToELKImpl implements ServiceSendingDataToELK {

    @Autowired
    private CreateOrderService service;

    @Override
    public void sendOrders(List<Order> orders) {
        BaseMessageType baseMessageType = initBaseMessageToSendOrders(orders);
        BaseMessageType response = service.createOrders(new HeaderType(), baseMessageType);
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
     * @param ordersNumber
     */
    @Override
    public void deleteOrders(List<String> ordersNumber) {
        BaseMessageType baseMessageType = initBaseMessageToDeleteOrders(ordersNumber);
        BaseMessageType response = service.deleteOrders(new HeaderType(), baseMessageType);
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
        service.updateOrders(new HeaderType(), baseMessageType);//TODO дописать проверку правильного ответа
    }

    @Override
    public void sendFilesByOrders(List<File> files) {
        service.uploadFiles(new HeaderType(), new BaseMessageType());//TODO дописать головную часть xml и его тело
    }

    private BaseMessageType initBaseMessageToDeleteOrders(List<String> ordersNumbers) {
        DeleteOrdersRequest deleteOrdersRequest = new DeleteOrdersRequest();
        for(String orderNum : ordersNumbers){
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
        //
        MessageDataType messageData = new MessageDataType();
        messageData.setAppData(appDataType);
        baseMessage.setMessageData(messageData);
        return baseMessage;
    }

}
