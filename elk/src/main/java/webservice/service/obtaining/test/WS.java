package webservice.service.obtaining.test;


import webservice.service.obtaining.service.ElkSync;

import javax.xml.ws.Endpoint;

public class WS {

    public void init(){
        Endpoint.publish("http://localhost:1986/elk", new ElkSync());
    }

}
