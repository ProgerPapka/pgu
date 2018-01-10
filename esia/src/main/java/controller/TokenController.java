package controller;

import configuration.*;
import configuration.EsiaConfig;
import configuration.RpguConfigToConnect;
import exception.JsonException;
import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthJSONAccessTokenResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class TokenController {

    @Autowired
    private RpguConfigToConnect rpguConfig;
    @Autowired
    private CertificateUtil certificateUtil;
    @Autowired
    private GeneratorTimestamp timestampUtil;
    @Autowired
    private EsiaConfig esiaConfig;
    @Autowired
    private Mapper userMapper;

    /**
     * Get new token to get data ()
     * @param code
     * @param state
     * @param scope
     * @param byRefresh
     * @throws OAuthSystemException
     * @throws OAuthProblemException
     * @throws JsonException
     */
    public void getNewToken(String code, String state, String scope, Token token, boolean byRefresh) throws OAuthSystemException, OAuthProblemException, JsonException {
        if (state.equals(rpguConfig.getState())) {
            String timestamp = timestampUtil.generateTimestamp();
            String clientSecret = certificateUtil.getUrlSafeSign(
                    scope + timestamp +
                            rpguConfig.getClientID() + rpguConfig.getState()
            );
            GrantType grantType;
            if(byRefresh){
                grantType = GrantType.REFRESH_TOKEN;
            } else {
                grantType = GrantType.AUTHORIZATION_CODE;
            }
            OAuthClientRequest requestToEsia = OAuthClientRequest
                    .tokenLocation(esiaConfig.getEsiaServer() + esiaConfig.getEsiaTokenPoint())
                    .setClientId(rpguConfig.getClientID())
                    .setRedirectURI(rpguConfig.getRedirectUrl())
                    .setScope(scope)
                    .setCode(code)
                    .setGrantType(grantType)
                    .setClientSecret(clientSecret)
                    .setParameter("state", rpguConfig.getState())
                    .setParameter("token_type", rpguConfig.getTokenType())
                    .setParameter("timestamp", timestamp)
                    .setParameter("access_type", rpguConfig.getAccessType())
                    .buildBodyMessage();
            OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
            OAuthJSONAccessTokenResponse oauthResponse = oAuthClient.accessToken(requestToEsia, OAuth.HttpMethod.POST, OAuthJSONAccessTokenResponse.class);
            //TODO check(test) token
            //TODO put token to cache(Memcached)
            userMapper.mapTokenDataFromMap(oauthResponse, token); //данные в токене
        }
    }

}
