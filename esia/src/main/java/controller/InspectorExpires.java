package controller;

import configuration.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * This component check about expired token
 */
@Component
public class InspectorExpires {

    @Autowired
    @Qualifier("rpgu")
    private Token token;

    public boolean inspectExpires(){
        return new Date().getTime() < token.getExp();
    }

}
