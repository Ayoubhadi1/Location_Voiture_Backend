package com.example.location_voiture_backend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;

@Entity @Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Categorie {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;

    @OneToMany(mappedBy = "categorie")
    private Collection<Voiture> voitures;

    @ManyToOne
    private User admin;
}
