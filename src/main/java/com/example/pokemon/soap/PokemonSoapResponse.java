package com.example.pokemon.soap;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PokemonSoapResponse {
    private List<String> abilities;
    private int baseExperience;
    private List<String> heldItems;
    private int id;
    private String name;
    private List<String> locationAreaEncounters;
}
