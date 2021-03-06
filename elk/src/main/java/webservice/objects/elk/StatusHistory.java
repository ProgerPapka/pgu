//
// This file was archive by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.01.09 at 06:15:32 PM SAMT 
//


package webservice.objects.elk;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for StatusHistory complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StatusHistory">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="statusExtId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statusName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="statusDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="statusComment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="files" type="{http://gosuslugi.ru/lk/elk/types/}Files" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StatusHistory", propOrder = {
    "status",
    "statusExtId",
    "statusName",
    "statusDate",
    "statusComment",
    "files"
})
public class StatusHistory {

    @XmlElement(required = true)
    protected String status;
    protected String statusExtId;
    @XmlElement(required = true)
    protected String statusName;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar statusDate;
    protected String statusComment;
    protected List<Files> files;

    /**
     * Gets the value of the status property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the statusExtId property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getStatusExtId() {
        return statusExtId;
    }

    /**
     * Sets the value of the statusExtId property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setStatusExtId(String value) {
        this.statusExtId = value;
    }

    /**
     * Gets the value of the statusName property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getStatusName() {
        return statusName;
    }

    /**
     * Sets the value of the statusName property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setStatusName(String value) {
        this.statusName = value;
    }

    /**
     * Gets the value of the statusDate property.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getStatusDate() {
        return statusDate;
    }

    /**
     * Sets the value of the statusDate property.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setStatusDate(XMLGregorianCalendar value) {
        this.statusDate = value;
    }

    /**
     * Gets the value of the statusComment property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getStatusComment() {
        return statusComment;
    }

    /**
     * Sets the value of the statusComment property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setStatusComment(String value) {
        this.statusComment = value;
    }

    /**
     * Gets the value of the files property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the files property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFiles().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Files }
     *
     *
     */
    public List<Files> getFiles() {
        if (files == null) {
            files = new ArrayList<Files>();
        }
        return this.files;
    }

}
