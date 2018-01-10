package wsdl.gosuslugi.lk.elk.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "code",
        "message",
        "deleteResults"
})
public class ErrorDelete {

    @XmlElement(required = true)
    protected long code;
    @XmlElement(required = true)
    protected String message;
    @XmlElement(required = true)
    protected DeleteResults deleteResults;

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DeleteResults getDeleteResults() {
        return deleteResults;
    }

    public void setDeleteResults(DeleteResults deleteResults) {
        this.deleteResults = deleteResults;
    }
}
