package webservice.service.obtaining.test;

import service.ElkSyncServiceImpl;

import javax.xml.ws.Endpoint;

public class WS {

    public void init(){
        Endpoint.publish("http://localhost:1986/wsdl", new ElkSyncServiceImpl());
    }

}
