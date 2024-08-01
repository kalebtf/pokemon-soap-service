package com.example.pokemon.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class RequestLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String ip;
    private LocalDateTime requestDate;
    private String method;
    private String request;
    private String response; // Cambiado a String para almacenar JSON
    private long duration;
}
