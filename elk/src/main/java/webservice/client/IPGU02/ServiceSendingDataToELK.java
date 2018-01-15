package webservice.client.IPGU02;

import webservice.objects.elk.Order;
import webservice.objects.elk.UpdateOrder;

import java.util.List;

public interface ServiceSendingDataToELK {

    void sendOrders(List<Order> orders);

    void deleteOrders(List<String> ordersNumber);

    void updateOrders(List<UpdateOrder> orders);

    void sendFilesByOrders(List<String> nameFiles, String elkNumber, String statusExtId);

}
