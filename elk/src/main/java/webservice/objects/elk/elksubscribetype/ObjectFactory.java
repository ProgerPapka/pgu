//
// This file was archive by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.01.10 at 12:30:56 PM SAMT 
//


package webservice.objects.elk.elksubscribetype;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * archive in the ru.gosuslugi.epgu.lk.subscribe.types.elksubscribeservicetypes package.
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.gosuslugi.epgu.lk.subscribe.types.elksubscribeservicetypes
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SubscribeRequest }
     * 
     */
    public SubscribeRequest createSubscribeRequest() {
        return new SubscribeRequest();
    }

    /**
     * Create an instance of {@link UnsubscribeRequest }
     * 
     */
    public UnsubscribeRequest createUnsubscribeRequest() {
        return new UnsubscribeRequest();
    }

}
