//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package wsdl.gosuslugi.epgu.lk.subscribe;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;

@WebServiceClient(
        name = "ElkSubscribeService",
        targetNamespace = "http://epgu.gosuslugi.ru/lk/subscribe/",
        wsdlLocation = "file:/C:/Users/ziatdinov.e/Documents/IntelliJProjects/rpgu/obtaining/webservIPGUElkSubscribeService/src/main/resources/wsdl/ws2.wsdl"
)
public class ElkSubscribeService_Service extends Service {
    private static final URL ELKSUBSCRIBESERVICE_WSDL_LOCATION;
    private static final WebServiceException ELKSUBSCRIBESERVICE_EXCEPTION;
    private static final QName ELKSUBSCRIBESERVICE_QNAME = new QName("http://epgu.gosuslugi.ru/lk/subscribe/", "ElkSubscribeService");

    public ElkSubscribeService_Service() {
        super(__getWsdlLocation(), ELKSUBSCRIBESERVICE_QNAME);
    }

    public ElkSubscribeService_Service(WebServiceFeature... var1) {
        super(__getWsdlLocation(), ELKSUBSCRIBESERVICE_QNAME, var1);
    }

    public ElkSubscribeService_Service(URL var1) {
        super(var1, ELKSUBSCRIBESERVICE_QNAME);
    }

    public ElkSubscribeService_Service(URL var1, WebServiceFeature... var2) {
        super(var1, ELKSUBSCRIBESERVICE_QNAME, var2);
    }

    public ElkSubscribeService_Service(URL var1, QName var2) {
        super(var1, var2);
    }

    public ElkSubscribeService_Service(URL var1, QName var2, WebServiceFeature... var3) {
        super(var1, var2, var3);
    }

    @WebEndpoint(
            name = "ElkSubscribeService"
    )
    public ElkSubscribeService getElkSubscribeService() {
        return (ElkSubscribeService)super.getPort(new QName("http://epgu.gosuslugi.ru/lk/subscribe/", "ElkSubscribeService"), ElkSubscribeService.class);
    }

    @WebEndpoint(
            name = "ElkSubscribeService"
    )
    public ElkSubscribeService getElkSubscribeService(WebServiceFeature... var1) {
        return (ElkSubscribeService)super.getPort(new QName("http://epgu.gosuslugi.ru/lk/subscribe/", "ElkSubscribeService"), ElkSubscribeService.class, var1);
    }

    private static URL __getWsdlLocation() {
        if (ELKSUBSCRIBESERVICE_EXCEPTION != null) {
            throw ELKSUBSCRIBESERVICE_EXCEPTION;
        } else {
            return ELKSUBSCRIBESERVICE_WSDL_LOCATION;
        }
    }

    static {
        URL var0 = null;
        WebServiceException var1 = null;

        try {
            var0 = new URL("file:/C:/Users/ziatdinov.e/Documents/IntelliJProjects/rpgu/obtaining/webservIPGUElkSubscribeService/src/main/resources/wsdl/ws2.wsdl");
        } catch (MalformedURLException var3) {
            var1 = new WebServiceException(var3);
        }

        ELKSUBSCRIBESERVICE_WSDL_LOCATION = var0;
        ELKSUBSCRIBESERVICE_EXCEPTION = var1;
    }
}
