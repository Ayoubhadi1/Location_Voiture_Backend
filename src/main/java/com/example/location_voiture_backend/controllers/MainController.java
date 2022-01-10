package com.example.location_voiture_backend.controllers;

import com.example.location_voiture_backend.repositories.RoleRepository;
import com.example.location_voiture_backend.repositories.UserRepository;
import com.example.location_voiture_backend.entities.*;

import com.example.location_voiture_backend.payload.response.MessageResponse;
import com.example.location_voiture_backend.security.services.UserDetailsImpl;

import com.example.location_voiture_backend.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class MainController {
	

	
	@Autowired
	private UserRepository userRepository ;
	
	@Autowired
	private RoleRepository rolesRepository ;
	
	@Autowired
	PasswordEncoder encoder;
	

	

	
	@GetMapping("/roles")
	public String[] roles() {
		
	    return ERole.names() ;
	}

	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	
	@GetMapping("/user")
	@PreAuthorize("hasRole('USER') or hasRole('AGENT') or hasRole('ADMIN')")
	public String userAccess() {
		return "User Content.";
	}
	

	

	
	@GetMapping("/agent")
	@PreAuthorize("hasRole('AGENT') or hasRole('ADMIN')")
	public String agentAccess() {
		return "Agent Board.";
	}
	

	

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}
	

	
	
}