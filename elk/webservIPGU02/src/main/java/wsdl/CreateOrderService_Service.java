//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package wsdl;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;

@WebServiceClient(
        name = "CreateOrderService",
        targetNamespace = "http://gosuslugi.ru/epguapi/ws/v25/",
        wsdlLocation = "file:/C:/Users/ziatdinov.e/Documents/IntelliJProjects/rpgu/elk/webservIPGU02/src/main/resources/wsdl/ws1.wsdl"
)
public class CreateOrderService_Service extends Service {
    private static final URL CREATEORDERSERVICE_WSDL_LOCATION;
    private static final WebServiceException CREATEORDERSERVICE_EXCEPTION;
    private static final QName CREATEORDERSERVICE_QNAME = new QName("http://gosuslugi.ru/epguapi/ws/v25/", "CreateOrderService");

    public CreateOrderService_Service() {
        super(__getWsdlLocation(), CREATEORDERSERVICE_QNAME);
    }

    public CreateOrderService_Service(WebServiceFeature... var1) {
        super(__getWsdlLocation(), CREATEORDERSERVICE_QNAME, var1);
    }

    public CreateOrderService_Service(URL var1) {
        super(var1, CREATEORDERSERVICE_QNAME);
    }

    public CreateOrderService_Service(URL var1, WebServiceFeature... var2) {
        super(var1, CREATEORDERSERVICE_QNAME, var2);
    }

    public CreateOrderService_Service(URL var1, QName var2) {
        super(var1, var2);
    }

    public CreateOrderService_Service(URL var1, QName var2, WebServiceFeature... var3) {
        super(var1, var2, var3);
    }

    @WebEndpoint(
            name = "CreateOrderService"
    )
    public CreateOrderService getCreateOrderService() {
        return (CreateOrderService)super.getPort(new QName("http://gosuslugi.ru/epguapi/ws/v25/", "CreateOrderService"), CreateOrderService.class);
    }

    @WebEndpoint(
            name = "CreateOrderService"
    )
    public CreateOrderService getCreateOrderService(WebServiceFeature... var1) {
        return (CreateOrderService)super.getPort(new QName("http://gosuslugi.ru/epguapi/ws/v25/", "CreateOrderService"), CreateOrderService.class, var1);
    }

    private static URL __getWsdlLocation() {
        if (CREATEORDERSERVICE_EXCEPTION != null) {
            throw CREATEORDERSERVICE_EXCEPTION;
        } else {
            return CREATEORDERSERVICE_WSDL_LOCATION;
        }
    }

    static {
        URL var0 = null;
        WebServiceException var1 = null;

        try {
            var0 = new URL("file:/C:/Users/ziatdinov.e/Documents/IntelliJProjects/rpgu/elk/webservIPGU02/src/main/resources/wsdl/ws1.wsdl");
        } catch (MalformedURLException var3) {
            var1 = new WebServiceException(var3);
        }

        CREATEORDERSERVICE_WSDL_LOCATION = var0;
        CREATEORDERSERVICE_EXCEPTION = var1;
    }
}
