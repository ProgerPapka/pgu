//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package wsdl.gosuslugi.epgu.lk.subscribe;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.xml.bind.annotation.XmlSeeAlso;
import wsdl.gosuslugi.smev.rev120315.BaseMessageType;
import wsdl.gosuslugi.smev.rev120315.HeaderType;
import wsdl.include.ObjectFactory;

@WebService(
        name = "ElkSubscribeService",
        targetNamespace = "http://epgu.gosuslugi.ru/lk/subscribe/"
)
@SOAPBinding(
        parameterStyle = ParameterStyle.BARE
)
@XmlSeeAlso({ObjectFactory.class, wsdl.gosuslugi.epgu.lk.common.types.ObjectFactory.class, wsdl.gosuslugi.epgu.lk.subscribe.types.elksubscribeservicetypes.ObjectFactory.class, wsdl.gosuslugi.smev.rev120315.ObjectFactory.class})
public interface ElkSubscribeService {
    @WebMethod(
            action = "http://epgu.gosuslugi.ru/lk/ElkSubscribeService/process"
    )
    @WebResult(
            name = "BaseMessage",
            targetNamespace = "http://smev.gosuslugi.ru/rev120315",
            partName = "processResponse"
    )
    BaseMessageType process(@WebParam(name = "Header",targetNamespace = "http://smev.gosuslugi.ru/rev120315",header = true,partName = "smevHeader") HeaderType var1, @WebParam(name = "BaseMessage",targetNamespace = "http://smev.gosuslugi.ru/rev120315",partName = "processRequest") BaseMessageType var2);
}
