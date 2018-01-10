package data;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Document {

    private String type;
    private String vrfStu;
    private String series;
    private String number;
    private LocalDateTime issueDate;
    private String issueId;
    private String issuedBy;
    private LocalDateTime expiryDate;
    private String lastName; //для заграничного паспорта
    private String firstName; //для заграничного паспорта

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

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDateTime getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDateTime issueDate) {
        this.issueDate = issueDate;
    }

    public String getIssueId() {
        return issueId;
    }

    public void setIssueId(String issueId) {
        this.issueId = issueId;
    }

    public String getIssuedBy() {
        return issuedBy;
    }

    public void setIssuedBy(String issuedBy) {
        this.issuedBy = issuedBy;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
