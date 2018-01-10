package configuration;

/**
 * Scope of received data
 */
public class Scope {
    /**
     * Data about user
     */
    public static final String OPENID = "openid";
    /**
     * Data about user
     */
    public static final String USRBRF = "http://esia.gosuslugi.ru/usr_brf";
    /**
     * All data about user
     */
    public static final String USRINF = "http://esia.gosuslugi.ru/usr_inf";
    /**
     * Data about organization
     */
    public static final String ORGINF = "http://esia.gosuslugi.ru/org_inf";
    /**
     * All data about organization
     */
    public static final String ORGFUL = "http://esia.gosuslugi.ru/org_ful";
    /**
     * Scope documents in the request
     */
    public static final String DOCS = "/docs";
    /**
     * Scope addresses in the request
     */
    public static final String ADDRS = "/addrs";
    /**
     * Scope organization in the request
     */
    public static final String ORGS = "/orgs";
    /**
     * Scope vehicles in the request
     */
    public static final String VHLS = "/vhls";
    /**
     * Scope children in the request
     */
    public static final String KIDS = "/kids";
    /**
     * Scope contacts in the request
     */
    public static final String CTTS = "/ctts";

    public static final String ELK = "http://lk.gosuslugi.ru/elk?oid=";

    public static String getUserData() {
        return OPENID;
    }

    public static String getUserData(String ...additionalData) {
        StringBuilder a = new StringBuilder();
        for(String q : additionalData){
            a.append(" ").append(q);
        }
        return USRBRF + " " + a.toString();
    }

    public static String getUserFullData() {
        return USRINF;
    }

    public static String getOrganizationData() {
        return ORGINF;
    }

    public static String getOrganizationData(String ...additionalData) {
        StringBuilder a = new StringBuilder();
        for(String q : additionalData){
            a.append(" ").append(q);
        }
        return ORGINF + " " + a.toString();
    }

    public static String getOrganizationFullData() {
        return ORGFUL;
    }

}
