package webservice.service.obtaining.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import webservice.objects.include.ObjectFactory;


@Configuration
public class Beans {

    @Bean(name = "include")
    public ObjectFactory initObjFact(){
        return new ObjectFactory();
    }

    @Bean(name = "elk")
    public webservice.objects.elk.ObjectFactory initObjFactElk(){
        return new webservice.objects.elk.ObjectFactory();
    }

    @Bean(name = "smev")
    public webservice.objects.smev.ObjectFactory initObjFactSmev(){
        return new webservice.objects.smev.ObjectFactory();
    }

    @Bean(name = "ws")
    public WS initWS(){
        return new WS();
    }

}
