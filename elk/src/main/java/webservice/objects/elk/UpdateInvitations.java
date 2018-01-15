//
// This file was archive by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.01.09 at 06:15:32 PM SAMT 
//


package webservice.objects.elk;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UpdateInvitations complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UpdateInvitations">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="invitations" type="{http://gosuslugi.ru/lk/elk/types/}Invitations"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UpdateInvitations", propOrder = {
    "invitations"
})
public class UpdateInvitations {

    @XmlElement(required = true)
    protected Invitations invitations;

    /**
     * Gets the value of the invitations property.
     * 
     * @return
     *     possible object is
     *     {@link Invitations }
     *     
     */
    public Invitations getInvitations() {
        return invitations;
    }

    /**
     * Sets the value of the invitations property.
     * 
     * @param value
     *     allowed object is
     *     {@link Invitations }
     *     
     */
    public void setInvitations(Invitations value) {
        this.invitations = value;
    }

}
