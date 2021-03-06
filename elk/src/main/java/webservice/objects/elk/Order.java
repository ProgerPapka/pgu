//
// This file was archive by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.01.10 at 04:42:14 PM SAMT 
//


package webservice.objects.elk;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for Order complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Order">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="userId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="eServiceCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="serviceTargetCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="userSelectedRegion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="elkOrderNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="requestDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="orderUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statusHistoryList" type="{http://gosuslugi.ru/lk/elk/types/}StatusHistoryList" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "order", propOrder = {
    "userId",
    "eServiceCode",
    "serviceTargetCode",
    "userSelectedRegion",
    "orderNumber",
    "requestDate",
    "orderUrl",
    "statusHistoryList"
})
public class Order {

    @XmlElement(required = true)
    protected String userId;
    protected String eServiceCode;
    @XmlElement(required = true)
    protected String serviceTargetCode;
    @XmlElement(required = true)
    protected String userSelectedRegion;
    @XmlElement(required = true)
    protected String orderNumber;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar requestDate;
    protected String orderUrl;
    protected List<StatusHistoryList> statusHistoryList;

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
     * Gets the value of the eServiceCode property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getEServiceCode() {
        return eServiceCode;
    }

    /**
     * Sets the value of the eServiceCode property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setEServiceCode(String value) {
        this.eServiceCode = value;
    }

    /**
     * Gets the value of the serviceTargetCode property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getServiceTargetCode() {
        return serviceTargetCode;
    }

    /**
     * Sets the value of the serviceTargetCode property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setServiceTargetCode(String value) {
        this.serviceTargetCode = value;
    }

    /**
     * Gets the value of the userSelectedRegion property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getUserSelectedRegion() {
        return userSelectedRegion;
    }

    /**
     * Sets the value of the userSelectedRegion property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setUserSelectedRegion(String value) {
        this.userSelectedRegion = value;
    }

    /**
     * Gets the value of the elkOrderNumber property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getElkOrderNumber() {
        return orderNumber;
    }

    /**
     * Sets the value of the elkOrderNumber property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setOrderNumber(String value) {
        this.orderNumber = value;
    }

    /**
     * Gets the value of the requestDate property.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getRequestDate() {
        return requestDate;
    }

    /**
     * Sets the value of the requestDate property.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setRequestDate(XMLGregorianCalendar value) {
        this.requestDate = value;
    }

    /**
     * Gets the value of the orderUrl property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getOrderUrl() {
        return orderUrl;
    }

    /**
     * Sets the value of the orderUrl property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setOrderUrl(String value) {
        this.orderUrl = value;
    }

    /**
     * Gets the value of the statusHistoryList property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the statusHistoryList property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStatusHistoryList().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StatusHistoryList }
     *
     *
     */
    public List<StatusHistoryList> getStatusHistoryList() {
        if (statusHistoryList == null) {
            statusHistoryList = new ArrayList<StatusHistoryList>();
        }
        return this.statusHistoryList;
    }

}
