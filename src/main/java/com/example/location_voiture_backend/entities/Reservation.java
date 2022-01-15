package com.example.location_voiture_backend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity @Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Reservation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int dureeReservation;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date dateReservation;
    @ManyToOne
    private User user;

    public Double prixReservation() {
        return null;
    }
}
