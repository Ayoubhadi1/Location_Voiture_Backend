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

	@OneToMany(mappedBy = "user")
	private Collection<Image> images;



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
				String agrement,
				String adresse,
				String ville,
				String pays,
				Collection<Image> images
				) {

		this.username = username;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.password = password;
		this.roles = roles;
		this.telephone = telephone;

		this.active = active;
		this.agrement = agrement;
		this.adresse = adresse;
		this.ville = ville;
		this.pays = pays;
		this.dateDebutEssai = dateDebutEssai;
		this.finAbonnement = finAbonnement;
		this.images = images;
	}

	public User(Long id, @NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 50) @Email String email,
				@NotBlank @Size(max = 120) String password, Set<Role> roles, boolean active,
				String poste , Collection<Image> images) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.roles = roles;
		this.active = active;
		this.images = images;
	}
	
	public User( @NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 50) @Email String email,
			@NotBlank @Size(max = 120) String password,  Collection<Image> images) {
		super();
		
		this.username = username;
		this.email = email;
		this.password = password;
		this.images = images;

	}





}


