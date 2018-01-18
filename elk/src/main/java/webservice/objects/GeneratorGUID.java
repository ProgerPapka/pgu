package webservice.objects;

import java.util.UUID;

public class GeneratorGUID {

    public String generateGUID(){
        return UUID.randomUUID().toString();
    }

}
