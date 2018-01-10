package service;

import java.time.LocalDateTime;

public interface SubscriberService {

    void subscribeToGetData(String token, LocalDateTime timestamp);

    void unsubscribeToGetData(String token);

}
