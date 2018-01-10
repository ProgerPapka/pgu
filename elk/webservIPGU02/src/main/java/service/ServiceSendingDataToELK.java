package service;

import data.Order;

import java.io.File;
import java.util.List;

public interface ServiceSendingDataToELK {

    void sendOrders(String userId, List<Order> orders);

    void deleteOrders(List<String> ordersNumber);

    void updateOrders(String userId, List<Order> orders);

    void sendFilesByOrders(List<File> files);

}
