//
// This file was archive by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.01.11 at 10:23:27 AM SAMT 
//


package webservice.objects.archive;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * archive in the archive package.
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: archive
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ArchiveDescription }
     * 
     */
    public ArchiveDescription createArchiveDescription() {
        return new ArchiveDescription();
    }

    /**
     * Create an instance of {@link FileDescriptions }
     * 
     */
    public FileDescriptions createFileDescriptions() {
        return new FileDescriptions();
    }

    /**
     * Create an instance of {@link FileDescription }
     * 
     */
    public FileDescription createFileDescription() {
        return new FileDescription();
    }

}
