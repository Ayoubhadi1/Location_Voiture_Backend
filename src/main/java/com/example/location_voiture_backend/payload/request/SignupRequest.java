package com.example.location_voiture_backend.payload.request;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.Set;

public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;
 
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    
    private Set<String> role;
    
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    private String nom;
    private String prenom;
    private String telephone;
    private String image ;
    private boolean active;
    private String adresse;
    private String ville;
    private String pays;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date dateDebutEssai;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date finAbonnement;


    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public Set<String> getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getImage() {
        return image;
    }

    public boolean isActive() {
        return active;
    }


    public String getAdresse() {
        return adresse;
    }

    public String getVille() {
        return ville;
    }

    public String getPays() {
        return pays;
    }

    public Date getDateDebutEssai() {
        return dateDebutEssai;
    }

    public Date getFinAbonnement() {
        return finAbonnement;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public void setDateDebutEssai(Date dateDebutEssai) {
        this.dateDebutEssai = dateDebutEssai;
    }

    public void setFinAbonnement(Date finAbonnement) {
        this.finAbonnement = finAbonnement;
    }
}