//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package webservice.service.obtaining.service;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;

// targetNamespace = "http://gosuslugi.ru/epguapi/ws/v25/"  так указан в wsdl
@WebServiceClient(
        name = "ElkSyncService",
        targetNamespace = "http://service.obtaining.service.webservice/",
        wsdlLocation = "http://localhost:1986/elk?wsdl"
)
public class ElkSyncService_Service extends Service {
    private static final URL ELKSYNCSERVICE_WSDL_LOCATION;
    private static final WebServiceException ELKSYNCSERVICE_EXCEPTION;
    private static final QName ELKSYNCSERVICE_QNAME = new QName("http://service.obtaining.service.webservice/", "ElkSyncService");

    public ElkSyncService_Service() {
        super(__getWsdlLocation(), ELKSYNCSERVICE_QNAME);
    }

    public ElkSyncService_Service(WebServiceFeature... var1) {
        super(__getWsdlLocation(), ELKSYNCSERVICE_QNAME, var1);
    }

    public ElkSyncService_Service(URL var1) {
        super(var1, ELKSYNCSERVICE_QNAME);
    }

    public ElkSyncService_Service(URL var1, WebServiceFeature... var2) {
        super(var1, ELKSYNCSERVICE_QNAME, var2);
    }

    public ElkSyncService_Service(URL var1, QName var2) {
        super(var1, var2);
    }

    public ElkSyncService_Service(URL var1, QName var2, WebServiceFeature... var3) {
        super(var1, var2, var3);
    }

    @WebEndpoint(
            name = "ElkSyncService"
    )
    public ElkSyncService getElkSyncService() {
        return (ElkSyncService) super.getPort(ElkSyncService.class);
    }

    @WebEndpoint(
            name = "ElkSyncService"
    )
    public ElkSyncService getElkSyncService(WebServiceFeature... var1) {
        return (ElkSyncService)super.getPort(new QName("http://service.obtaining.service.webservice/", "ElkSyncService"), ElkSyncService.class, var1);
    }

    private static URL __getWsdlLocation() {
        if (ELKSYNCSERVICE_EXCEPTION != null) {
            throw ELKSYNCSERVICE_EXCEPTION;
        } else {
            return ELKSYNCSERVICE_WSDL_LOCATION;
        }
    }

    static {
        URL var0 = null;
        WebServiceException var1 = null;
        try {
            var0 = new URL("http://localhost:1986/elk?wsdl");
        } catch (MalformedURLException var3) {
            var1 = new WebServiceException(var3);
        }

        ELKSYNCSERVICE_WSDL_LOCATION = var0;
        ELKSYNCSERVICE_EXCEPTION = var1;
    }
}
