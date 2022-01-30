package com.example.location_voiture_backend.repositories;

import java.util.Optional;

import com.example.location_voiture_backend.entities.Image;
import com.example.location_voiture_backend.entities.TypeImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByName(String name);
    Optional<Image> findByUserIdAndAndTypeImage(Long userId , TypeImage name);

}
