package com.example.location_voiture_backend.entities;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

@Entity	@Data
@Table(	name = "users", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "username"),
			@UniqueConstraint(columnNames = "email") 
		})
public class User implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 20)
	private String username;

	@NotBlank
	@Size(max = 20)
	private String nom;

	@NotBlank
	@Size(max = 20)
	private String prenom;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	@NotBlank
	@Size(max = 120)
	private String password;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;
	private String telephone;

	@OneToOne(mappedBy = "user")
	private Image imageProfil ;

	private boolean active;
	private String agrement;
	private String adresse;
	private String ville;
	private String pays;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date dateDebutEssai;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date finAbonnement;

	@OneToMany(mappedBy = "user")
	private Collection<Reservation> reservations;

	@OneToMany(mappedBy = "agent")
	private Collection<Voiture> voitures;

	@OneToMany(mappedBy = "admin")
	private Collection<Categorie> categories;

	@ManyToOne
	private Pack pack;

	@OneToOne(mappedBy = "user")
	private Image cinRecto;

	@OneToOne(mappedBy = "user")
	private Image cinVerso;


	public User() {
	}
	
	public User(@NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 50) @Email String email,
			@NotBlank @Size(max = 120) String password) {
		
		this.username = username;
		this.email = email;
		this.password = password;
		
	}


	public User(
				@NotBlank @Size(max = 20) String username,
				@NotBlank @Size(max = 50) @Email String email,
				@NotBlank @Size(max = 120) String password,
				String nom,
				String prenom,
				String telephone,
				Image image,
				String agrement,
				String adresse,
				String ville,
				String pays,
				Image cinRecto,
				Image cinVerso) {

		this.username = username;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.password = password;
		this.roles = roles;
		this.telephone = telephone;
		this.imageProfil = image;
		this.active = active;
		this.agrement = agrement;
		this.adresse = adresse;
		this.ville = ville;
		this.pays = pays;
		this.dateDebutEssai = dateDebutEssai;
		this.finAbonnement = finAbonnement;
		this.cinRecto = cinRecto;
		this.cinVerso = cinVerso;
	}

	public User(Long id, @NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 50) @Email String email,
				@NotBlank @Size(max = 120) String password, Set<Role> roles, Image image, boolean active, String domaine,
				String poste , Image cinRecto, Image cinVerso) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.roles = roles;
		this.imageProfil = image;
		this.active = active;
		this.cinRecto = cinRecto;
		this.cinVerso = cinVerso;
	}
	
	public User( @NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 50) @Email String email,
			@NotBlank @Size(max = 120) String password,  Image image , Image cinRecto, Image cinVerso) {
		super();
		
		this.username = username;
		this.email = email;
		this.password = password;
		this.imageProfil = image;
		this.cinRecto = cinRecto;
		this.cinVerso = cinVerso;

	}





}


