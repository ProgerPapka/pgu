package wsdl.gosuslugi.lk.elk.types;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "order", propOrder = {
        "elkOrderNumber",
        "status",
        "message"
})
public class DeleteResult {

    @XmlElement(required = true)
    protected long elkOrderNumber;
    @XmlElement(required = true)
    protected String status;
    protected String message;

    public long getElkOrderNumber() {
        return elkOrderNumber;
    }

    public void setElkOrderNumber(long elkOrderNumber) {
        this.elkOrderNumber = elkOrderNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
