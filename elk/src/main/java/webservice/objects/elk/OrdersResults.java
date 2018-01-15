package webservice.objects.elk;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "orders", propOrder = {
        "orderResults"
})
public class OrdersResults {

    protected List<OrderResult> orderResults;

    public List<OrderResult> getOrderResults() {
        return orderResults;
    }

    public void setOrderResults(List<OrderResult> orderResults) {
        this.orderResults = orderResults;
    }
}
