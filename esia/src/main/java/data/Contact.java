package data;

import org.springframework.stereotype.Component;

@Component
public class Contact {

    private String type;
    private String vrfStu;
    private String value;

    public Contact() {
    }

    public Contact(String type, String vrfStu, String value) {
        this.type = type;
        this.vrfStu = vrfStu;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVrfStu() {
        return vrfStu;
    }

    public void setVrfStu(String vrfStu) {
        this.vrfStu = vrfStu;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


}
