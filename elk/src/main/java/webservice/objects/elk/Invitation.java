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
 * <p>Java class for Invitation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Invitation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="userId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="elkInvitationNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="invitationCreateDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="invitationStartDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="invitationEndDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="invitationOrgName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="invitationAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="invitationStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="invitationUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Invitation", propOrder = {
    "userId",
    "elkInvitationNumber",
    "invitationCreateDate",
    "invitationStartDate",
    "invitationEndDate",
    "invitationOrgName",
    "invitationAddress",
    "invitationStatus",
    "invitationUrl"
})
public class Invitation {

    @XmlElement(required = true)
    protected String userId;
    @XmlElement(required = true)
    protected String elkInvitationNumber;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar invitationCreateDate;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar invitationStartDate;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar invitationEndDate;
    @XmlElement(required = true)
    protected String invitationOrgName;
    protected String invitationAddress;
    @XmlElement(required = true)
    protected String invitationStatus;
    protected String invitationUrl;

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
     * Gets the value of the elkInvitationNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getElkInvitationNumber() {
        return elkInvitationNumber;
    }

    /**
     * Sets the value of the elkInvitationNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setElkInvitationNumber(String value) {
        this.elkInvitationNumber = value;
    }

    /**
     * Gets the value of the invitationCreateDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getInvitationCreateDate() {
        return invitationCreateDate;
    }

    /**
     * Sets the value of the invitationCreateDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setInvitationCreateDate(XMLGregorianCalendar value) {
        this.invitationCreateDate = value;
    }

    /**
     * Gets the value of the invitationStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getInvitationStartDate() {
        return invitationStartDate;
    }

    /**
     * Sets the value of the invitationStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setInvitationStartDate(XMLGregorianCalendar value) {
        this.invitationStartDate = value;
    }

    /**
     * Gets the value of the invitationEndDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getInvitationEndDate() {
        return invitationEndDate;
    }

    /**
     * Sets the value of the invitationEndDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setInvitationEndDate(XMLGregorianCalendar value) {
        this.invitationEndDate = value;
    }

    /**
     * Gets the value of the invitationOrgName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInvitationOrgName() {
        return invitationOrgName;
    }

    /**
     * Sets the value of the invitationOrgName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInvitationOrgName(String value) {
        this.invitationOrgName = value;
    }

    /**
     * Gets the value of the invitationAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInvitationAddress() {
        return invitationAddress;
    }

    /**
     * Sets the value of the invitationAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInvitationAddress(String value) {
        this.invitationAddress = value;
    }

    /**
     * Gets the value of the invitationStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInvitationStatus() {
        return invitationStatus;
    }

    /**
     * Sets the value of the invitationStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInvitationStatus(String value) {
        this.invitationStatus = value;
    }

    /**
     * Gets the value of the invitationUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInvitationUrl() {
        return invitationUrl;
    }

    /**
     * Sets the value of the invitationUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInvitationUrl(String value) {
        this.invitationUrl = value;
    }

}
