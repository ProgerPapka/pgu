package configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Config to connection to esia
 */
@PropertySource("classpath:esia.properties")
public class EsiaConfig {

    @Value("${esia.server}")
    private String esiaServer;
    @Value("${esia.auth}")
    private String esiaCodePoint;
    @Value("${esia.token}")
    private String esiaTokenPoint;
    @Value("${esia.info.user}")
    private String esiaUserInfoPoint;
    @Value("${esia.info.org}")
    private String esiaOrgInfoPoint;

    /**
     * Return address esia server(url)
     * @return
     */
    public String getEsiaServer() {
        return esiaServer;
    }

    /**
     * Return authentication point in esia server
     * @return
     */
    public String getEsiaCodePoint() {
        return esiaCodePoint;
    }

    /**
     * Return resource point in esia server
     * @return
     */
    public String getEsiaTokenPoint() {
        return esiaTokenPoint;
    }

    /**
     * Return info point about subjects in esia server
     * @return
     */
    public String getEsiaUserInfoPoint() {
        return esiaUserInfoPoint;
    }

}
