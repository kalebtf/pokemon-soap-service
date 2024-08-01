package com.example.pokemon.service;

import com.example.pokemon.model.RequestLog;
import com.example.pokemon.repository.RequestLogRepository;
import com.example.pokemon.soap.PokemonSoapResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PokemonService {

    @Value("${pokemon.api.url}")
    private String apiUrl;

    private final RequestLogRepository requestLogRepository;

    public PokemonService(RequestLogRepository requestLogRepository) {
        this.requestLogRepository = requestLogRepository;
    }

    public PokemonSoapResponse getPokemonDetails(String name) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://pokeapi.co/api/v2/pokemon/" + name;

        long startTime = System.currentTimeMillis();
        String response = restTemplate.getForObject(url, String.class);
        long duration = System.currentTimeMillis() - startTime;

        // Parsear response y llenar PokemonSoapResponse
        PokemonSoapResponse soapResponse = parseResponse(response);

        // Convertir PokemonSoapResponse a JSON
        String jsonResponse = convertToJson(soapResponse);

        // Guardar detalles del request en la base de datos
        RequestLog log = new RequestLog();
        log.setIp(getServerIp());
        log.setRequestDate(LocalDateTime.now());
        log.setMethod("getPokemonDetails");
        log.setRequest(url);
        log.setResponse(jsonResponse);
        log.setDuration(duration);
        requestLogRepository.save(log);

        return soapResponse;
    }

    private String convertToJson(PokemonSoapResponse soapResponse) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(soapResponse);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{}";
        }
    }

    private String getServerIp() {
        String ip;
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            ip = "Unknown";
        }
        return ip;
    }

    private PokemonSoapResponse parseResponse(String response) {
        ObjectMapper objectMapper = new ObjectMapper();
        PokemonSoapResponse pokemonSoapResponse = new PokemonSoapResponse();
        try {
            JsonNode rootNode = objectMapper.readTree(response);
            pokemonSoapResponse.setId(rootNode.get("id").asInt());
            pokemonSoapResponse.setName(rootNode.get("forms").get(0).get("name").asText());
            pokemonSoapResponse.setBaseExperience(rootNode.get("base_experience").asInt());

            // Parsing abilities
            List<String> abilities = new ArrayList<>();
            for (JsonNode abilityNode : rootNode.get("abilities")) {
                abilities.add(abilityNode.get("ability").get("name").asText());
            }
            pokemonSoapResponse.setAbilities(abilities);

            // Parsing heldItems
            List<String> heldItems = new ArrayList<>();
            for (JsonNode heldItemNode : rootNode.get("held_items")) {
                heldItems.add(heldItemNode.get("item").get("name").asText());
            }
            pokemonSoapResponse.setHeldItems(heldItems);

            // Parsing locationAreaEncounters
            String locationUrl = rootNode.get("location_area_encounters").asText();
            pokemonSoapResponse.setLocationAreaEncounters(Collections.singletonList(locationUrl));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return pokemonSoapResponse;
    }
}
