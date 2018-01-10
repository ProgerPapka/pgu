package web;

import controller.SubscribeController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDateTime;

@Controller
public class ElkController {

    @Autowired
    private SubscribeController subscribeController;

    /**
     * Endpoint нажатия кнопки Подписка к ЕЛК для получения данных
     */
    @RequestMapping(value = "/subscribe", method = RequestMethod.GET)
    public void subscribeToElk() {
        subscribeController.subscribe(LocalDateTime.of(2016, 1, 1, 0, 0, 0));
    }

    /**
     * Endpoint нажатия кнопки Отписка от ЕЛК для получения данных
     */
    @RequestMapping(value = "/unsubscribe", method = RequestMethod.GET)
    public void unsubscribeToElk() {
        subscribeController.unsubscribe();
    }

}
