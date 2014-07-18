
package com.cm;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.cm package. 
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

    private final static QName _SyncchannelResponse_QNAME = new QName("http://cm.com/", "syncchannelResponse");
    private final static QName _Syncchannel_QNAME = new QName("http://cm.com/", "syncchannel");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.cm
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Syncchannel }
     * 
     */
    public Syncchannel createSyncchannel() {
        return new Syncchannel();
    }

    /**
     * Create an instance of {@link SyncchannelResponse }
     * 
     */
    public SyncchannelResponse createSyncchannelResponse() {
        return new SyncchannelResponse();
    }

    /**
     * Create an instance of {@link ChannelInfo }
     * 
     */
    public ChannelInfo createChannelInfo() {
        return new ChannelInfo();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SyncchannelResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cm.com/", name = "syncchannelResponse")
    public JAXBElement<SyncchannelResponse> createSyncchannelResponse(SyncchannelResponse value) {
        return new JAXBElement<SyncchannelResponse>(_SyncchannelResponse_QNAME, SyncchannelResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Syncchannel }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cm.com/", name = "syncchannel")
    public JAXBElement<Syncchannel> createSyncchannel(Syncchannel value) {
        return new JAXBElement<Syncchannel>(_Syncchannel_QNAME, Syncchannel.class, null, value);
    }

}
