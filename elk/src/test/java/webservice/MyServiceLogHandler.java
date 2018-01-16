package webservice;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

public class MyServiceLogHandler implements SOAPHandler<SOAPMessageContext> {

    private static Logger logger = Logger.getLogger(MyServiceLogHandler.class);

    @Override
    public Set<QName> getHeaders() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void close(MessageContext arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean handleFault(SOAPMessageContext arg0) {
        SOAPMessage message= arg0.getMessage();
        try {
            message.writeTo(System.out);
        } catch (SOAPException e) {
            // TODO Auto-generated catch block
           logger.error(e);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            logger.error("",e);
        }
        return false;
    }

    @Override
    public boolean handleMessage(SOAPMessageContext arg0) {
        SOAPMessage message= arg0.getMessage();
        boolean isOutboundMessage=  (Boolean)arg0.get (MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        if(isOutboundMessage){
            System.out.println("OUTBOUND MESSAGE\n");

        }else{
            System.out.println("INBOUND MESSAGE\n");
        }
        try {
            message.writeTo(System.out);
        } catch (SOAPException e) {
            logger.error(e);
        } catch (IOException e) {
            logger.error("",e);
        }
        return false;
    }
}