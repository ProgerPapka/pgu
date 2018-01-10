package data;

import org.springframework.stereotype.Component;

@Component
public class Vehicle {

    private String name;
    private String numberPlate;
    private RegCertificate regCertificate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public RegCertificate getRegCertificate() {
        return regCertificate;
    }

    public void setRegCertificate(RegCertificate regCertificate) {
        this.regCertificate = regCertificate;
    }

    public static class RegCertificate{
        private String series;
        private String number;

        public RegCertificate() {
        }

        public RegCertificate(String series, String number) {
            this.series = series;
            this.number = number;
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
    }
}
