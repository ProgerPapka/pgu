package service;

import data.Order;
import org.springframework.beans.factory.annotation.Autowired;
import wsdl.BaseMessageType;
import wsdl.CreateOrderService;
import wsdl.CreateOrderService_Service;
import wsdl.HeaderType;

import java.io.File;
import java.util.List;

public class ServiceSendingDataToELKImpl implements ServiceSendingDataToELK {

    @Autowired
    private CreateOrderService service;

    @Override
    public void sendOrders(String userId, List<Order> orders) {
        service.createOrders(new HeaderType(), new BaseMessageType());//TODO дописать головную часть xml и его тело
    }

    @Override
    public void deleteOrders(List<String> ordersNumber) {
        service.deleteOrders(new HeaderType(), new BaseMessageType());//TODO дописать головную часть xml и его тело
    }

    @Override
    public void updateOrders(String userId, List<Order> orders) {
        service.updateOrders(new HeaderType(), new BaseMessageType());//TODO дописать головную часть xml и его тело
    }

    @Override
    public void sendFilesByOrders(List<File> files) {
        service.uploadFiles(new HeaderType(), new BaseMessageType());//TODO дописать головную часть xml и его тело
    }
}
