package webservice.objects.elk;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UploadFiles", propOrder = {
        "elkOrderNumber",
        "statusExtId"
})
public class UploadFiles {

    protected String elkOrderNumber;
    protected String statusExtId;

    public String getElkOrderNumber() {
        return elkOrderNumber;
    }

    public void setElkOrderNumber(String elkOrderNumber) {
        this.elkOrderNumber = elkOrderNumber;
    }

    public String getStatusExtId() {
        return statusExtId;
    }

    public void setStatusExtId(String statusExtId) {
        this.statusExtId = statusExtId;
    }
}
