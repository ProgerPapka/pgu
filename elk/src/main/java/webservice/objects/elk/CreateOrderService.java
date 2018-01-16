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
        name = "CreateOrderService",
        targetNamespace = "http://gosuslugi.ru/epguapi/ws/v25/"
)
@SOAPBinding(
        parameterStyle = ParameterStyle.BARE
)
@XmlSeeAlso({ObjectFactory.class, webservice.objects.smev.ObjectFactory.class, webservice.objects.include.ObjectFactory.class})
public interface CreateOrderService {
    @WebMethod(
            action = "createOrder"
    )
    @WebResult(
            name = "BaseMessage",
            targetNamespace = "http://smev.gosuslugi.ru/rev120315",
            partName = "createOrderResponse"
    )
    BaseMessageType createOrder(@WebParam(name = "Header", targetNamespace = "http://smev.gosuslugi.ru/rev120315", header = true, partName = "smevHeader") HeaderType var1, @WebParam(name = "BaseMessage", targetNamespace = "http://smev.gosuslugi.ru/rev120315", partName = "createOrderRequest") BaseMessageType var2);

    @WebMethod(
            action = "createOrders"
    )
    @WebResult(
            name = "BaseMessage",
            targetNamespace = "http://smev.gosuslugi.ru/rev120315",
            partName = "createOrdersResponse"
    )
    BaseMessageType createOrders(@WebParam(name = "Header", targetNamespace = "http://smev.gosuslugi.ru/rev120315", header = true, partName = "smevHeader") HeaderType var1, @WebParam(name = "BaseMessage", targetNamespace = "http://smev.gosuslugi.ru/rev120315", partName = "createOrdersRequest") BaseMessageType var2);

    @WebMethod(
            action = "updateOrders"
    )
    @WebResult(
            name = "BaseMessage",
            targetNamespace = "http://smev.gosuslugi.ru/rev120315",
            partName = "updateOrdersResponse"
    )
    BaseMessageType updateOrders(@WebParam(name = "Header", targetNamespace = "http://smev.gosuslugi.ru/rev120315", header = true, partName = "smevHeader") HeaderType var1, @WebParam(name = "BaseMessage", targetNamespace = "http://smev.gosuslugi.ru/rev120315", partName = "updateOrdersRequest") BaseMessageType var2);

    @WebMethod(
            action = "deleteOrders"
    )
    @WebResult(
            name = "BaseMessage",
            targetNamespace = "http://smev.gosuslugi.ru/rev120315",
            partName = "deleteOrdersResponse"
    )
    BaseMessageType deleteOrders(@WebParam(name = "Header", targetNamespace = "http://smev.gosuslugi.ru/rev120315", header = true, partName = "smevHeader") HeaderType var1, @WebParam(name = "BaseMessage", targetNamespace = "http://smev.gosuslugi.ru/rev120315", partName = "deleteOrdersRequest") BaseMessageType var2);

    @WebMethod(
            action = "uploadFiles"
    )
    @WebResult(
            name = "BaseMessage",
            targetNamespace = "http://smev.gosuslugi.ru/rev120315",
            partName = "uploadFilesResponse"
    )
    BaseMessageType uploadFiles(@WebParam(name = "Header", targetNamespace = "http://smev.gosuslugi.ru/rev120315", header = true, partName = "smevHeader") HeaderType var1, @WebParam(name = "BaseMessage", targetNamespace = "http://smev.gosuslugi.ru/rev120315", partName = "uploadFilesRequest") BaseMessageType var2);
}
