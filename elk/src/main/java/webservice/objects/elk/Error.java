//
// This file was archive by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.01.10 at 10:53:24 AM SAMT 
//


package webservice.objects.elk;


import javax.xml.bind.annotation.*;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="code" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "code",
        "message",
        "ordersResult"
})
@XmlRootElement(name = "error")
public class Error {

    @XmlElement(required = true)
    protected long code;
    @XmlElement(required = true)
    protected String message;
    @XmlElement(required = true)
    protected OrdersResults ordersResult;

    /**
     * Gets the value of the code property.
     *
     */
    public long getCode() {
        return code;
    }

    /**
     * Sets the value of the code property.
     *
     */
    public void setCode(long value) {
        this.code = value;
    }

    /**
     * Gets the value of the message property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the value of the message property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setMessage(String value) {
        this.message = value;
    }

    public OrdersResults getOrdersResult() {
        return ordersResult;
    }

    public void setOrdersResult(OrdersResults ordersResult) {
        this.ordersResult = ordersResult;
    }
}
