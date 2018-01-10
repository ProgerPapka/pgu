package data;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Organization {

    private String shortName;
    private String fullName;
    private String type;
    private long ogrn;
    private long inn;
    private long leg;
    private long kpp;
    private List<Contact> contacts;
    private List<Address> addresses;
    private List<Vehicle> vehicles;
    private List<Employ> employs;
    private List<Group> groups;


    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setOgrn(long ogrn) {
        this.ogrn = ogrn;
    }

    public void setInn(long inn) {
        this.inn = inn;
    }

    public void setLeg(long leg) {
        this.leg = leg;
    }

    public void setKpp(long kpp) {
        this.kpp = kpp;
    }

    public String getShortName() {
        return shortName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getType() {
        return type;
    }

    public long getOgrn() {
        return ogrn;
    }

    public long getInn() {
        return inn;
    }

    public long getLeg() {
        return leg;
    }

    public long getKpp() {
        return kpp;
    }

    public static class Employ{

        private String position;
        private boolean chief;
        private List<Group> groups;

        public Employ(String position, boolean chief, List<Group> groups) {
            this.position = position;
            this.chief = chief;
            this.groups = groups;
        }

        public Employ() {

        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public boolean isChief() {
            return chief;
        }

        public void setChief(boolean chief) {
            this.chief = chief;
        }

        public List<Group> getGroups() {
            return groups;
        }

        public void setGroups(List<Group> groups) {
            this.groups = groups;
        }
    }

    public static class Group{

        private String name;
        private String description;
        private boolean system;

        public Group() {
        }

        public Group(String name, String description, boolean system) {
            this.name = name;
            this.description = description;
            this.system = system;
        }

        public String getName() {

            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public boolean isSystem() {
            return system;
        }

        public void setSystem(boolean system) {
            this.system = system;
        }
    }

}
