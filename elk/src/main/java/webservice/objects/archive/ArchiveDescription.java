//
// This file was archive by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.01.11 at 10:23:27 AM SAMT 
//


package webservice.objects.archive;

import javax.xml.bind.annotation.*;


/**
 * 
 *                     �������� ������ ������
 *                 
 * 
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fileDescriptions" type="{}FileDescriptions"/>
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
    "fileDescriptions"
})
@XmlRootElement(name = "archiveDescription")
public class ArchiveDescription {

    @XmlElement(required = true)
    protected FileDescriptions fileDescriptions;

    /**
     * Gets the value of the fileDescriptions property.
     * 
     * @return
     *     possible object is
     *     {@link FileDescriptions }
     *     
     */
    public FileDescriptions getFileDescriptions() {
        return fileDescriptions;
    }

    /**
     * Sets the value of the fileDescriptions property.
     * 
     * @param value
     *     allowed object is
     *     {@link FileDescriptions }
     *     
     */
    public void setFileDescriptions(FileDescriptions value) {
        this.fileDescriptions = value;
    }

}
