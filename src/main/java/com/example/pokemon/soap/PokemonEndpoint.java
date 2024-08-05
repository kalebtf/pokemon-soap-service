package com.example.pokemon.soap;

import com.example.pokemon.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

@Endpoint
public class PokemonEndpoint {

    private static final String NAMESPACE_URI = "http://example.com/pokemon";
    private final ObjectFactory objectFactory = new ObjectFactory();

    @Autowired
    private PokemonService pokemonService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "PokemonSoapRequest")
    @ResponsePayload
    public JAXBElement<PokemonSoapResponse> getPokemonDetails(@RequestPayload JAXBElement<PokemonSoapRequest> request) {
        PokemonSoapRequest pokemonSoapRequest = request.getValue();
        PokemonSoapResponse response = pokemonService.getPokemonDetails(pokemonSoapRequest.getName());

        return objectFactory.createPokemonSoapResponse(response);
    }
}