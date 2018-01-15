package service;

import wsdl.smev.BaseMessageType;
import wsdl.smev.HeaderType;
import wsdl.smev.ObjectFactory;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

@WebService(
        name = "ElkSyncService",
        targetNamespace = "http://gosuslugi.ru/epguapi/ws/v25/"
)
@SOAPBinding(
        parameterStyle = SOAPBinding.ParameterStyle.BARE
)
@XmlSeeAlso({ObjectFactory.class, wsdl.include.ObjectFactory.class, wsdl.lk.elk.types.ObjectFactory.class})
public interface ElkSyncService {

    @WebMethod(
            action = "createOrders"
    )
    @WebResult(
            name = "BaseMessage",
            targetNamespace = "http://smev.gosuslugi.ru/rev120315",
            partName = "createOrdersResponse"
    )
    BaseMessageType createOrders(@WebParam(name = "Header", targetNamespace = "http://smev.gosuslugi.ru/rev120315", header = true, partName = "smevHeader") HeaderType var1,
                                 @WebParam(name = "BaseMessage", targetNamespace = "http://smev.gosuslugi.ru/rev120315", partName = "createOrdersRequest") BaseMessageType var2);

    @WebMethod(
            action = "updateOrders"
    )
    @WebResult(
            name = "BaseMessage",
            targetNamespace = "http://smev.gosuslugi.ru/rev120315",
            partName = "updateOrdersResponse"
    )
    BaseMessageType updateOrders(@WebParam(name = "Header", targetNamespace = "http://smev.gosuslugi.ru/rev120315", header = true, partName = "smevHeader") HeaderType var1,
                                 @WebParam(name = "BaseMessage", targetNamespace = "http://smev.gosuslugi.ru/rev120315", partName = "updateOrdersRequest") BaseMessageType var2);

    @WebMethod(
            action = "deleteOrders"
    )
    @WebResult(
            name = "BaseMessage",
            targetNamespace = "http://smev.gosuslugi.ru/rev120315",
            partName = "deleteOrdersResponse"
    )
    BaseMessageType deleteOrders(@WebParam(name = "Header", targetNamespace = "http://smev.gosuslugi.ru/rev120315", header = true, partName = "smevHeader") HeaderType var1,
                                 @WebParam(name = "BaseMessage", targetNamespace = "http://smev.gosuslugi.ru/rev120315", partName = "deleteOrdersRequest") BaseMessageType var2);

    @WebMethod(
            action = "createInvitations"
    )
    @WebResult(
            name = "BaseMessage",
            targetNamespace = "http://smev.gosuslugi.ru/rev120315",
            partName = "createInvitationsResponse"
    )
    BaseMessageType createInvitations(@WebParam(name = "Header", targetNamespace = "http://smev.gosuslugi.ru/rev120315", header = true, partName = "smevHeader") HeaderType var1,
                                      @WebParam(name = "BaseMessage", targetNamespace = "http://smev.gosuslugi.ru/rev120315", partName = "createInvitationsRequest") BaseMessageType var2);


    @WebMethod(
            action = "updateInvitations"
    )
    @WebResult(
            name = "BaseMessage",
            targetNamespace = "http://smev.gosuslugi.ru/rev120315",
            partName = "updateInvitationsResponse"
    )
    BaseMessageType updateInvitations(@WebParam(name = "Header", targetNamespace = "http://smev.gosuslugi.ru/rev120315", header = true, partName = "smevHeader") HeaderType var1,
                                      @WebParam(name = "BaseMessage", targetNamespace = "http://smev.gosuslugi.ru/rev120315", partName = "updateInvitationsRequest") BaseMessageType var2);


    @WebMethod(
            action = "createPayments"
    )
    @WebResult(
            name = "BaseMessage",
            targetNamespace = "http://smev.gosuslugi.ru/rev120315",
            partName = "createPaymentsResponse"
    )
    BaseMessageType createPayments(@WebParam(name = "Header", targetNamespace = "http://smev.gosuslugi.ru/rev120315", header = true, partName = "smevHeader") HeaderType var1,
                                   @WebParam(name = "BaseMessage", targetNamespace = "http://smev.gosuslugi.ru/rev120315", partName = "createPaymentsRequest") BaseMessageType var2);


}
