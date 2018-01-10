package configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Configuration about rpgu
 */
@PropertySource("classpath:rpgu.properties")
public class RpguConfigToConnect {

    @Value("${rpgu.client_id}")
    private String clientID;
    @Value("${rpgu.scope}")
    private String scope;
    @Value("${rpgu.access_type}")
    private String accessType;
    @Value("${rpgu.redirect_url}")
    private String redirectUrl;
    @Value("${rpgu.token_type}")
    private String tokenType;

    private String state;

    public String getState() {
        if(state == null){
            state = UUID.randomUUID().toString();
        }
        return state;
    }

    public String getClientID() {
        return clientID;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
}
