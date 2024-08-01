package com.example.pokemon.soap;

import com.example.pokemon.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class PokemonEndpoint {

    private static final String NAMESPACE_URI = "http://example.com/pokemon";

    @Autowired
    private PokemonService pokemonService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "PokemonSoapRequest")
    @ResponsePayload
    public PokemonSoapResponse getPokemonDetails(@RequestPayload PokemonSoapRequest request) {
        PokemonSoapResponse response= pokemonService.getPokemonDetails(request.getName());
        // Parsear responseData y llenar PokemonSoapResponse
        //PokemonSoapResponse response = new PokemonSoapResponse();
        // Asignar valores a response
        return response;
    }
}