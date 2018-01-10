package web.controller;

import configuration.*;
import configuration.EsiaConfig;
import configuration.RpguConfigToConnect;
import controller.*;
import exception.JsonException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthAuthzResponse;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Controller
public class EsiaController {

    @Autowired
    private RpguConfigToConnect rpguConfig;
    @Autowired
    private CertificateUtil certificateUtil;
    @Autowired
    private GeneratorTimestamp timestampUtil;
    @Autowired
    private EsiaConfig esiaConfig;
    @Autowired
    private TokenController tokenController;
    @Autowired
    @Qualifier("rpgu")
    private Token token; //session token to get user data from esia for rpgu
    @Autowired
    private RequestToEsiaController requestToEsia;
    @Autowired
    private AuthToken authToken;

    private static Log log = LogFactory.getLog(EsiaController.class);

    /**
     * Mapping to page with "Войти через Есиа" button.
     *
     * @return index.jsp page
     */
    @RequestMapping("/")
    public ModelAndView init() {
        return new ModelAndView("index");
    }

    /**
     * Mapping to Esia(redirect to esia authorize with rpgu parameters).
     * Mapping happen when user click "Войти через Есиа" button.
     *
     * @return redirect to esia uri
     * @throws OAuthSystemException
     */
    @RequestMapping(value = "/esia-login", method = RequestMethod.GET)
    public ModelAndView esiaAuthorize() throws OAuthSystemException {
        String timestamp = timestampUtil.generateTimestamp();
        String clientSecret = certificateUtil.getUrlSafeSign(
                Scope.OPENID + timestamp +
                        rpguConfig.getClientID() + rpguConfig.getState());

        OAuthClientRequest request = OAuthClientRequest
                .authorizationLocation(esiaConfig.getEsiaServer() + esiaConfig.getEsiaCodePoint())
                .setClientId(rpguConfig.getClientID())
                .setRedirectURI(rpguConfig.getRedirectUrl())
                .setScope(Scope.OPENID)
                .setResponseType(ResponseType.CODE)
                .setState(rpguConfig.getState())
                .setParameter("client_secret", clientSecret)
                .setParameter("timestamp", timestamp)
                .setParameter("access_type", rpguConfig.getAccessType())
                .buildQueryMessage();
        return new ModelAndView(new RedirectView(request.getLocationUri()));
    }

    /**
     * This method run when esia redirect to redirect_url
     *
     * @param request esia request
     */
    @RequestMapping(value = "/esia-ok", method = RequestMethod.GET)
    public ModelAndView getAccessCode(HttpServletRequest request) {
        OAuthAuthzResponse response;
        try {
            response = OAuthAuthzResponse.oauthCodeAuthzResponse(request);
            String code = response.getCode();
            String state = response.getState();
            authToken.setCode(code);
            authToken.setState(state);
            tokenController.getNewToken(code, state, Scope.USRINF, token, false); //получаем токен
            requestToEsia.getUserInfo(token.getSbjId(), true); //получаем все данные
            return new ModelAndView("profile");
        } catch (OAuthProblemException | OAuthSystemException | UnsupportedEncodingException | JsonException e) {
            return new ModelAndView("error");
        } catch (IOException e) {
            return new ModelAndView("error2");
        }
    }

}
