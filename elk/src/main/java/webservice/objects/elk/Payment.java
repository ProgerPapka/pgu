//
// This file was archive by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.01.09 at 06:15:32 PM SAMT 
//


package webservice.objects.elk;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for Payment complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Payment">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="userId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="elkPaymentNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="paymentDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="paymentName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="paymentSumma" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="paymentUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Payment", propOrder = {
    "userId",
    "elkPaymentNumber",
    "paymentDate",
    "paymentName",
    "paymentSumma",
    "paymentUrl"
})
public class Payment {

    @XmlElement(required = true)
    protected String userId;
    @XmlElement(required = true)
    protected String elkPaymentNumber;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar paymentDate;
    @XmlElement(required = true)
    protected String paymentName;
    protected double paymentSumma;
    protected String paymentUrl;

    /**
     * Gets the value of the userId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the value of the userId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserId(String value) {
        this.userId = value;
    }

    /**
     * Gets the value of the elkPaymentNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getElkPaymentNumber() {
        return elkPaymentNumber;
    }

    /**
     * Sets the value of the elkPaymentNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setElkPaymentNumber(String value) {
        this.elkPaymentNumber = value;
    }

    /**
     * Gets the value of the paymentDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPaymentDate() {
        return paymentDate;
    }

    /**
     * Sets the value of the paymentDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPaymentDate(XMLGregorianCalendar value) {
        this.paymentDate = value;
    }

    /**
     * Gets the value of the paymentName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentName() {
        return paymentName;
    }

    /**
     * Sets the value of the paymentName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentName(String value) {
        this.paymentName = value;
    }

    /**
     * Gets the value of the paymentSumma property.
     * 
     */
    public double getPaymentSumma() {
        return paymentSumma;
    }

    /**
     * Sets the value of the paymentSumma property.
     * 
     */
    public void setPaymentSumma(double value) {
        this.paymentSumma = value;
    }

    /**
     * Gets the value of the paymentUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentUrl() {
        return paymentUrl;
    }

    /**
     * Sets the value of the paymentUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentUrl(String value) {
        this.paymentUrl = value;
    }

}
