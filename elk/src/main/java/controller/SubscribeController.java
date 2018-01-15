package controller;

import configuration.AuthToken;
import configuration.Scope;
import configuration.Token;
import data.EsiaUser;
import exception.JsonException;
import org.apache.log4j.Logger;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import webservice.client.IPGUElkSubscribeService.SubscriberService;


import java.time.LocalDateTime;

@Service
public class SubscribeController {

    private Logger logger = Logger.getLogger(SubscribeController.class);

    @Autowired
    private SubscriberService subscriberService;
    @Autowired
    private TokenController tokenController;
    @Autowired
    @Qualifier("obtaining")
    private Token token;
    @Autowired
    private AuthToken authToken;
    @Autowired
    private EsiaUser user;

    private boolean isGetTokenFromEsia() {
        try {
            tokenController.getNewToken(authToken.getCode(), authToken.getState(),
                    Scope.ELK+user.getId(), token, false);
            return true;
        } catch (OAuthSystemException e) {
            logger.error("OAuth system error", e);
        } catch (OAuthProblemException e) {
            logger.error("OAuth connection error", e);
        } catch (JsonException e) {
            logger.error("Parse json error", e);
        }
        return false;
    }

    public void subscribe(LocalDateTime timestamp) {
        if (isGetTokenFromEsia()) {
            subscriberService.subscribeToGetData(token.getAccessToken(), timestamp);
        }
    }

    public void unsubscribe() {
        if (isGetTokenFromEsia()) {
            subscriberService.unsubscribeToGetData(token.getAccessToken());
        }
    }

}
