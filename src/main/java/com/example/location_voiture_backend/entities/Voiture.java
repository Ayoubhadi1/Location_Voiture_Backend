package com.example.location_voiture_backend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Voiture {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String immatriculation;
    private String marque;
    private String model;
    private String description;
    private Double kilometrage;
    private Double prixParJour;

    @ElementCollection
    private Collection<String> images;

    private Boolean disponibilite;

    @ManyToOne
    private Categorie categorie;

    @ManyToOne
    private User agent;
}
