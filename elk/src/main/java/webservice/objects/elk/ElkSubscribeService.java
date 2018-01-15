//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package webservice.objects.elk;


import webservice.objects.smev.BaseMessageType;
import webservice.objects.smev.HeaderType;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.xml.bind.annotation.XmlSeeAlso;

@WebService(
        name = "ElkSubscribeService",
        targetNamespace = "http://epgu.gosuslugi.ru/lk/subscribe/"
)
@SOAPBinding(
        parameterStyle = ParameterStyle.BARE
)
@XmlSeeAlso({ObjectFactory.class, webservice.objects.include.ObjectFactory.class, webservice.objects.elk.elksubscribetype.ObjectFactory.class, webservice.objects.smev.ObjectFactory.class})
public interface ElkSubscribeService {
    @WebMethod(
            action = "http://epgu.gosuslugi.ru/lk/ElkSubscribeService/process"
    )
    @WebResult(
            name = "BaseMessage",
            targetNamespace = "http://smev.gosuslugi.ru/rev120315",
            partName = "processResponse"
    )
    BaseMessageType process(@WebParam(name = "Header", targetNamespace = "http://smev.gosuslugi.ru/rev120315", header = true, partName = "smevHeader") HeaderType var1, @WebParam(name = "BaseMessage", targetNamespace = "http://smev.gosuslugi.ru/rev120315", partName = "processRequest") BaseMessageType var2);
}
