//
// This file was archive by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.01.11 at 05:38:20 PM SAMT 
//


package webservice.objects.smev;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TypeCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TypeCodeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="GSRV"/>
 *     &lt;enumeration value="GFNC"/>
 *     &lt;enumeration value="OTHR"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TypeCodeType")
@XmlEnum
public enum TypeCodeType {


    /**
     * �������������� � ������ �������� ���������������
     *                         �����
     *                     
     * 
     */
    GSRV,

    /**
     * �������������� � ������ ����������
     *                     
     * 
     */
    GFNC,

    /**
     * �������������� � ������ ����������
     *                     
     * 
     */
    OTHR;

    public String value() {
        return name();
    }

    public static TypeCodeType fromValue(String v) {
        return valueOf(v);
    }

}
