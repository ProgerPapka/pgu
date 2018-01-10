package service;

import wsdl.gosuslugi.lk.elk.types.Order;
import wsdl.gosuslugi.lk.elk.types.UpdateOrder;

import java.io.File;
import java.util.List;

public interface ServiceSendingDataToELK {

    void sendOrders(List<Order> orders);

    void deleteOrders(List<String> ordersNumber);

    void updateOrders(List<UpdateOrder> orders);

    void sendFilesByOrders(List<File> files);

}
