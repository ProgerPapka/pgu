package controller;

import data.EsiaUser;
import data.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.ServiceSendingDataToELK;

import java.io.File;
import java.util.Collections;
import java.util.List;

@Service
public class ControllerSendingOrders {

    @Autowired
    private ServiceSendingDataToELK serviceSendingDataToELK;
    @Autowired
    private EsiaUser user;

    /**
     * Передача заявлений в ЕЛК
     */
    public void sendDataAboutOrders(){
        List<Order> orderList = Collections.emptyList(); //TODO получить заявки(вместе со всеми статусами) по пользователю с базы
        serviceSendingDataToELK.sendOrders(user.getId(),orderList);
    }

    /**
     * Передача статусов по заявлениям в ЕЛК
     */
    public void sendStatusesAboutOrders(){
        List<Order> orders = Collections.emptyList(); //TODO статусы по заявлениям взять с базы
        serviceSendingDataToELK.updateOrders(user.getId(),orders);
    }

    /**
     * Передача файлов по заявлению
     */
    public void sendFilesByOrder(){
        List<File> files = Collections.emptyList(); //TODO получить файлы по заявлению
        serviceSendingDataToELK.sendFilesByOrders(files);
    }

    /**
     * Удаление заявлений в ЕЛК
     */
    public void deleteDataAboutOrders(){
        List<String> ordersNumber = Collections.emptyList();
        serviceSendingDataToELK.deleteOrders(ordersNumber);
    }


}
