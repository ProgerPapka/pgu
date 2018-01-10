package service;

import java.time.LocalDateTime;

public interface SubscriberService {

    boolean subscribeToGetData(String token, LocalDateTime timestamp);

    boolean unsubscribeToGetData(String token);

}
