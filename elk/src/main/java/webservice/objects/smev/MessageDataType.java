//
// This file was archive by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.01.11 at 05:38:20 PM SAMT 
//


package webservice.objects.smev;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MessageDataType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MessageDataType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://smev.gosuslugi.ru/rev120315}AppData" minOccurs="0"/>
 *         &lt;element ref="{http://smev.gosuslugi.ru/rev120315}AppDocument" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MessageDataType", propOrder = {
    "appData",
    "appDocument"
})
public class MessageDataType {

    @XmlElement(name = "AppData")
    protected AppDataType appData;
    @XmlElement(name = "AppDocument")
    protected AppDocumentType appDocument;

    /**
     * Gets the value of the appData property.
     *
     * @return
     *     possible object is
     *     {@link AppDataType }
     *
     */
    public AppDataType getAppData() {
        return appData;
    }

    /**
     * Sets the value of the appData property.
     *
     * @param value
     *     allowed object is
     *     {@link AppDataType }
     *
     */
    public void setAppData(AppDataType value) {
        this.appData = value;
    }

    /**
     * Gets the value of the appDocument property.
     *
     * @return
     *     possible object is
     *     {@link AppDocumentType }
     *
     */
    public AppDocumentType getAppDocument() {
        return appDocument;
    }

    /**
     * Sets the value of the appDocument property.
     *
     * @param value
     *     allowed object is
     *     {@link AppDocumentType }
     *     
     */
    public void setAppDocument(AppDocumentType value) {
        this.appDocument = value;
    }

}
