package webservice.service.obtaining.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import wsdl.include.ObjectFactory;

@Configuration
public class Beans {

    @Bean(name = "include")
    public ObjectFactory initObjFact(){
        return new ObjectFactory();
    }

    @Bean(name = "elk")
    public wsdl.lk.elk.types.ObjectFactory initObjFactElk(){
        return new wsdl.lk.elk.types.ObjectFactory();
    }

    @Bean(name = "smev")
    public wsdl.smev.ObjectFactory initObjFactSmev(){
        return new wsdl.smev.ObjectFactory();
    }

    @Bean(name = "ws")
    public WS initWS(){
        return new WS();
    }

}
