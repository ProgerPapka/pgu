//
// This file was archive by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.01.09 at 06:15:32 PM SAMT 
//


package webservice.objects.elk;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for CreatePayments complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CreatePayments">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="payments" type="{http://gosuslugi.ru/lk/elk/types/}Payments"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CreatePayments", propOrder = {
    "payments"
})
@XmlRootElement
public class CreatePayments {

    @XmlElement(required = true)
    protected Payments payments;

    /**
     * Gets the value of the payments property.
     *
     * @return
     *     possible object is
     *     {@link Payments }
     *
     */
    public Payments getPayments() {
        return payments;
    }

    /**
     * Sets the value of the payments property.
     *
     * @param value
     *     allowed object is
     *     {@link Payments }
     *     
     */
    public void setPayments(Payments value) {
        this.payments = value;
    }

}
