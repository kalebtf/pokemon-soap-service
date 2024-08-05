package com.example.pokemon.soap;

import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@XmlRootElement(name = "PokemonSoapResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class PokemonSoapResponse {
    private List<String> abilities;
    private int baseExperience;
    private List<String> heldItems;
    private int id;
    private String name;
    private List<String> locationAreaEncounters;
    // No-argument constructor
    public PokemonSoapResponse() {
    }

    public List<String> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<String> abilities) {
        this.abilities = abilities;
    }

    public int getBaseExperience() {
        return baseExperience;
    }

    public void setBaseExperience(int baseExperience) {
        this.baseExperience = baseExperience;
    }

    public List<String> getHeldItems() {
        return heldItems;
    }

    public void setHeldItems(List<String> heldItems) {
        this.heldItems = heldItems;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getLocationAreaEncounters() {
        return locationAreaEncounters;
    }

    public void setLocationAreaEncounters(List<String> locationAreaEncounters) {
        this.locationAreaEncounters = locationAreaEncounters;
    }

    // Parameterized constructor
    public PokemonSoapResponse(int id, String name, int baseExperience, List<String> abilities, List<String> heldItems, List<String> locationAreaEncounters) {
        this.id = id;
        this.name = name;
        this.baseExperience = baseExperience;
        this.abilities = abilities;
        this.heldItems = heldItems;
        this.locationAreaEncounters = locationAreaEncounters;
    }
}
