package com.example.location_voiture_backend.repositories;

import java.util.Optional;

import com.example.location_voiture_backend.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByName(String name);
}
