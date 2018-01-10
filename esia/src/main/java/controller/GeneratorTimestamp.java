package controller;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class to get timestamp
 */
@Component
public class GeneratorTimestamp {

    public String generateTimestamp(){
        return new SimpleDateFormat("yyyy.MM.dd HH:mm:ss Z").format(new Date());
    }

}
