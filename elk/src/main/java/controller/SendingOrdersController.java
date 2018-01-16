package controller;

import data.EsiaUser;
import exception.ElkServiceException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webservice.client.IPGU02.ServiceSendingDataToELK;
import webservice.objects.elk.Order;
import webservice.objects.elk.UpdateOrder;


import java.util.Collections;
import java.util.List;

@Service
public class SendingOrdersController {

    private static Logger logger = Logger.getLogger(SendingOrdersController.class);

    @Autowired
    private ServiceSendingDataToELK serviceSendingDataToELK;
    @Autowired
    private EsiaUser user;

    /**
     * Передача заявлений в ЕЛК
     */
    public void sendDataAboutOrders() {
        List<Order> orderList = Collections.emptyList(); //TODO получить заявки(вместе со всеми статусами) по пользователю с базы и сконвертировать
        try {
            serviceSendingDataToELK.sendOrders(orderList);
        } catch (ElkServiceException e) {
            logger.error(e);
        }
    }

    /**
     * Передача статусов по заявлениям в ЕЛК
     */
    public void sendStatusesAboutOrders() {
        List<UpdateOrder> orders = Collections.emptyList(); //TODO статусы по заявлениям взять с базы и сконвертировать
        try {
            serviceSendingDataToELK.updateOrders(orders);
        } catch (ElkServiceException e) {
            logger.error(e);
        }
    }

    /**
     * Передача файлов по заявлению
     */
    public void sendFilesByOrder() {
        List<String> files = Collections.emptyList(); //TODO получить файлы по заявлению
        String statusExtId = "";
        String elkNumber = "";
        try {
            serviceSendingDataToELK.sendFilesByOrders(files, elkNumber, statusExtId);
        } catch (ElkServiceException e) {
            logger.error(e);
        }
    }

    /**
     * Удаление заявлений в ЕЛК
     */
    public void deleteDataAboutOrders() {
        List<String> ordersNumber = Collections.emptyList();
        try {
            serviceSendingDataToELK.deleteOrders(ordersNumber);
        } catch (ElkServiceException e) {
            logger.error(e);
        }
    }


}
