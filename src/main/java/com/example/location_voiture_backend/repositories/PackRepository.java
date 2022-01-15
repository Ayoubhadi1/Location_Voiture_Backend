package com.example.location_voiture_backend.repositories;

import com.example.location_voiture_backend.entities.Categorie;
import com.example.location_voiture_backend.entities.Pack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PackRepository extends JpaRepository<Pack, Long> {
}
