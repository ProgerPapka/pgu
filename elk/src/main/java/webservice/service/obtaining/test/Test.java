package webservice.service.obtaining.test;

import beans.Config;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Beans.class);
        WS ws = (WS) context.getBean("ws");
        ws.init();
    }

}
