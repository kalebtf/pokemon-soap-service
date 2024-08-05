package com.example.pokemon.soap;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {
    private final static QName _PokemonSoapRequest_QNAME = new QName("http://example.com/pokemon", "PokemonSoapRequest");
    private final static QName _PokemonSoapResponse_QNAME = new QName("http://example.com/pokemon", "PokemonSoapResponse");

    public ObjectFactory() {}

    public PokemonSoapRequest createPokemonSoapRequest() {
        return new PokemonSoapRequest();
    }

    public PokemonSoapResponse createPokemonSoapResponse() {
        return new PokemonSoapResponse();
    }

    public JAXBElement<PokemonSoapRequest> createPokemonSoapRequest(PokemonSoapRequest value) {
        return new JAXBElement<>(_PokemonSoapRequest_QNAME, PokemonSoapRequest.class, null, value);
    }

    public JAXBElement<PokemonSoapResponse> createPokemonSoapResponse(PokemonSoapResponse value) {
        return new JAXBElement<>(_PokemonSoapResponse_QNAME, PokemonSoapResponse.class, null, value);
    }
}
