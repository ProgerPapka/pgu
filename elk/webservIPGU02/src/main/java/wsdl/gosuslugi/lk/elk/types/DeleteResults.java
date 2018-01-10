package wsdl.gosuslugi.lk.elk.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "orders", propOrder = {
        "deleteResults"
})
public class DeleteResults {
    protected List<DeleteResult> deleteResults;

    public List<DeleteResult> getOrderResults() {
        return deleteResults;
    }

    public void setOrderResults(List<DeleteResult> orderResults) {
        this.deleteResults = orderResults;
    }

}
