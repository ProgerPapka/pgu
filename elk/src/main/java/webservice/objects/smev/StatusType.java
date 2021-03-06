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
 * <p>Java class for StatusType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="StatusType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="REQUEST"/>
 *     &lt;enumeration value="RESULT"/>
 *     &lt;enumeration value="REJECT"/>
 *     &lt;enumeration value="INVALID"/>
 *     &lt;enumeration value="ACCEPT"/>
 *     &lt;enumeration value="PING"/>
 *     &lt;enumeration value="PROCESS"/>
 *     &lt;enumeration value="NOTIFY"/>
 *     &lt;enumeration value="FAILURE"/>
 *     &lt;enumeration value="CANCEL"/>
 *     &lt;enumeration value="STATE"/>
 *     &lt;enumeration value="PACKET"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "StatusType")
@XmlEnum
public enum StatusType {


    /**
     * ������
     * 
     */
    REQUEST,

    /**
     * ���������
     * 
     */
    RESULT,

    /**
     * �������������� �����
     * 
     */
    REJECT,

    /**
     * ������ ��� ���
     * 
     */
    INVALID,

    /**
     * ���������-������ � ������
     * 
     */
    ACCEPT,

    /**
     * ������ ������/�����������
     * 
     */
    PING,

    /**
     * � ���������
     * 
     */
    PROCESS,

    /**
     * ����������� �� ������
     * 
     */
    NOTIFY,

    /**
     * ����������� ����
     * 
     */
    FAILURE,

    /**
     * ����� ���������
     * 
     */
    CANCEL,

    /**
     * ������� ���������
     * 
     */
    STATE,

    /**
     * �������� ��������� ���������
     * 
     */
    PACKET;

    public String value() {
        return name();
    }

    public static StatusType fromValue(String v) {
        return valueOf(v);
    }

}
