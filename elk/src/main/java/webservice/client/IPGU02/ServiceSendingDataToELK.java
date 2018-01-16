package webservice.client.IPGU02;

import exception.ElkServiceException;
import webservice.objects.elk.Order;
import webservice.objects.elk.UpdateOrder;

import java.util.List;

public interface ServiceSendingDataToELK {

    void sendOrders(List<Order> orders) throws ElkServiceException;

    void deleteOrders(List<String> ordersNumber) throws ElkServiceException;

    void updateOrders(List<UpdateOrder> orders) throws ElkServiceException;

    void sendFilesByOrders(List<String> nameFiles, String elkNumber, String statusExtId) throws ElkServiceException;

}
