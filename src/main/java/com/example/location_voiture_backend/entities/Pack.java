package com.example.location_voiture_backend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;

@Entity @Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Pack {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private Double prix;
    private int duree;
    @OneToMany(mappedBy = "pack")
    private Collection<User> users;
}
