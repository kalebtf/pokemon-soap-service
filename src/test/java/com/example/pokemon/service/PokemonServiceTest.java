package com.example.pokemon.service;

import com.example.pokemon.model.RequestLog;
import com.example.pokemon.repository.RequestLogRepository;
import com.example.pokemon.soap.PokemonSoapResponse;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class PokemonServiceTest {

    @InjectMocks
    private PokemonService pokemonService;

    @Mock
    private RequestLogRepository requestLogRepository;

    @Test
    public void testGetPokemonDetails() {
        String name = "pikachu";
        when(requestLogRepository.save(any(RequestLog.class))).thenReturn(null);
        PokemonSoapResponse response = pokemonService.getPokemonDetails(name);
        assertNotNull(response);
    }
}
