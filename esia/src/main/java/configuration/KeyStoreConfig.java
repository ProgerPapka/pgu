package configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Configuration of keystore
 */
@PropertySource("classpath:keystore.properties")
public class KeyStoreConfig {

    @Value("${keystore.path}")
    private String pathToKeysStore;
    @Value("${keystore.password}")
    private String keyStorePassword;
    @Value("${keystore.aliasKey}")
    private String keyAlias;
    @Value("${keystore.signatureAlg}")
    private String signatureAlg;

    /**
     * Return path to keystore ***.jks
     * @return
     */
    public String getPathToKeysStore() {
        return pathToKeysStore;
    }

    /**
     * Return password from keystore
     * @return
     */
    public String getKeyStorePassword() {
        return keyStorePassword;
    }

    /**
     * Return alias from certificate
     * @return
     */
    public String getKeyAlias() {
        return keyAlias;
    }

    /**
     * Return algorithm of process
     * @return
     */
    public String getSignatureAlg() {
        return signatureAlg;
    }

}
