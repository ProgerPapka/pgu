//
// This file was archive by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.01.09 at 06:15:32 PM SAMT 
//


package webservice.objects.elk;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for UpdateOrdersRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UpdateOrdersRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="orders" type="{http://gosuslugi.ru/lk/elk/types/}UpdateOrders"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UpdateOrdersRequest", propOrder = {
    "orders"
})
@XmlRootElement
public class UpdateOrdersRequest {

    @XmlElement(required = true)
    protected UpdateOrders orders;

    /**
     * Gets the value of the orders property.
     *
     * @return
     *     possible object is
     *     {@link UpdateOrders }
     *
     */
    public UpdateOrders getOrders() {
        return orders;
    }

    /**
     * Sets the value of the orders property.
     *
     * @param value
     *     allowed object is
     *     {@link UpdateOrders }
     *     
     */
    public void setOrders(UpdateOrders value) {
        this.orders = value;
    }

}
