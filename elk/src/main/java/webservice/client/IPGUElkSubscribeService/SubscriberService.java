package webservice.client.IPGUElkSubscribeService;

import exception.ElkServiceException;

import java.time.LocalDateTime;

public interface SubscriberService {

    boolean subscribeToGetData(String token, LocalDateTime timestamp) throws ElkServiceException;

    boolean unsubscribeToGetData(String token) throws ElkServiceException;

}
