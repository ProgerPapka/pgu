package controller;

import data.Order;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class NotificationController {

    /**
     * Notification about sent orders
     * @return
     */
    public List<Order> getSentOrders() {
        return Collections.emptyList(); //TODO получить с базы все отправленые в елк заявления
    }

    public boolean sendOrdersToELK(List<String> idOrders){
        //TODO отправить заявления которых нет в ЕЛК(предварительно получить с базы)
        return true;
    }

    /**
     * Delete orders, which sent to ELK
     * @return
     */
    public boolean deleteSentOrders(List<String> idOrders){
        //TODO удалить заяления которые были отправлены в ЕЛК
        return true;
    }
}
