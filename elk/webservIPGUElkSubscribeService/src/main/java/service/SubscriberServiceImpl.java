package service;

import org.springframework.beans.factory.annotation.Autowired;
import wsdl.gosuslugi.smev.rev120315.BaseMessageType;
import wsdl.gosuslugi.smev.rev120315.HeaderType;
import wsdl.gosuslugi.epgu.lk.subscribe.ElkSubscribeService;

import java.time.LocalDateTime;

public class SubscriberServiceImpl implements SubscriberService {

    @Autowired
    private ElkSubscribeService service;

    @Override
    public void subscribeToGetData(String token, LocalDateTime timestamp) {
        HeaderType header = new HeaderType();
        BaseMessageType baseMessage = new BaseMessageType();

        service.process(header,baseMessage);//TODO соответстуещие хэдэр и тело xml
    }

    @Override
    public void unsubscribeToGetData(String token) {
        HeaderType header = new HeaderType();
        BaseMessageType baseMessage = new BaseMessageType();

        service.process(header,baseMessage);//TODO соответстуещие хэдэр и тело xml
    }
}
