package controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import configuration.EsiaConfig;
import configuration.RpguConfigToConnect;
import configuration.Scope;
import configuration.Token;
import exception.JsonException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Requests to esia
 */
@Service
public class RequestToEsiaController {

    @Autowired
    private EsiaConfig esiaConfig;
    @Autowired
    @Qualifier("rpgu")
    private Token token;
    @Autowired
    private InspectorExpires inspectorExpires;
    @Autowired
    private TokenController tokenController;
    @Autowired
    private RpguConfigToConnect rpguConfig;
    @Autowired
    private Mapper userMapper;

    /**
     * Get user info from esia.
     *
     * @param userName
     * @param isFull   If true, then get all data about user
     * @return
     * @throws IOException
     * @throws OAuthSystemException
     * @throws OAuthProblemException
     * @throws JsonException
     */
    public void getUserInfo(String userName, boolean isFull) throws IOException, OAuthSystemException, OAuthProblemException, JsonException {
        if (!inspectorExpires.inspectExpires()) {
            tokenController.getNewToken(token.getRefreshToken(), rpguConfig.getState(), Scope.USRINF, token, true);
        }
        HttpClient client = HttpClientBuilder.create().build(); //строим запрос http
        HttpGet get = new HttpGet(esiaConfig.getEsiaServer() + esiaConfig.getEsiaUserInfoPoint() + userName);
        String result = getResponse(client, get);
        Map<String, Object> userInfo = new ObjectMapper().readValue(result,
                new TypeReference<Map<String, Object>>() {
                }); // parse to map from string json
        userMapper.mapUserDataFromMap(userInfo); //данные о клиенте
        if (isFull) {
            getUserOtherInfo(token.getSbjId(), Scope.DOCS);
            getUserOtherInfo(token.getSbjId(), Scope.CTTS);
            getUserOtherInfo(token.getSbjId(), Scope.ORGS);
            getUserOtherInfo(token.getSbjId(), Scope.VHLS);
            getUserOtherInfo(token.getSbjId(), Scope.KIDS);
            getUserOtherInfo(token.getSbjId(), Scope.ADDRS);
        }
    }

    /**
     * Get map whit user contacts
     *
     * @param userName
     * @param scopeData Scope received data
     * @return
     * @throws IOException
     */
    public void getUserOtherInfo(String userName, String scopeData)
            throws IOException, OAuthSystemException, OAuthProblemException, JsonException {
        if (!inspectorExpires.inspectExpires()) {
            tokenController.getNewToken(token.getRefreshToken(), rpguConfig.getState(), Scope.USRINF, token, true);
        }
        Map<String, Object> info;
        HttpClient client = HttpClientBuilder.create().build(); //строим запрос http
        HttpGet get = new HttpGet(esiaConfig.getEsiaServer() + esiaConfig.getEsiaUserInfoPoint() + userName + scopeData);
        String result = getResponse(client, get);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = new ObjectMapper().readValue(result,
                new TypeReference<Map<String, Object>>() {
                });
        List uris = (List) map.get("elements");
        for (Object uri : uris) {
            get = new HttpGet(uri.toString());
            result = getResponse(client, get);
            info = mapper.readValue(result, new TypeReference<Map<String, Object>>() {
            });
            userMapper.mapOtherUserDateFromMap(info, scopeData);
        }
    }

    /**
     * Method does request and obtain full response from server
     *
     * @param client
     * @param get
     * @return String response(json)
     * @throws IOException
     */
    private String getResponse(HttpClient client, HttpGet get) throws IOException {
        get.addHeader(OAuth.HeaderType.CONTENT_TYPE, OAuth.ContentType.URL_ENCODED);
        get.addHeader(OAuth.HeaderType.AUTHORIZATION, String.format("%s %s", token.getTokenType(), token.getAccessToken()));
        HttpResponse response = client.execute(get);
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = response.getEntity().getContent().read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        return result.toString("UTF-8");
    }

}
