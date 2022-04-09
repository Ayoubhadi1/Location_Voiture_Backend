package com.example.location_voiture_backend.controllers;

import com.example.location_voiture_backend.repositories.ImageRepository;
import com.example.location_voiture_backend.repositories.RoleRepository;
import com.example.location_voiture_backend.repositories.UserRepository;
import com.example.location_voiture_backend.entities.*;

import com.example.location_voiture_backend.payload.response.MessageResponse;
import com.example.location_voiture_backend.security.services.UserDetailsImpl;

import com.example.location_voiture_backend.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
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
	private ImageRepository imageRepository;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	private IUserService userService;


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

	@PostMapping("/uploadAgrement/{idUser}")
	public ResponseEntity<MessageResponse> uploadImageUser(@RequestParam("image") MultipartFile file ,
														   @PathVariable int idUser ,
														   @RequestHeader Map<String, String> headers)
			throws IOException {

		Image image = Image.builder()
				.name(file.getOriginalFilename())
				.type(file.getContentType())
				.image(ImageUtility.compressImage(file.getBytes())).build();
		if(headers.get("typeimage").equals("agrement")){ image.setTypeImage(TypeImage.AGREMENT);}



		//image.setUser(userRepository.getById(Long.parseLong(String.valueOf(idUser))));
		User u = userRepository.getById(Long.parseLong(String.valueOf(idUser)));
		image.setUser(u);
		imageRepository.save(image);



		return ResponseEntity.status(HttpStatus.OK)
				.body(new MessageResponse("Image uploaded successfully: " +
						file.getOriginalFilename()));
	}

	@GetMapping("/agrementEnvoye/{id}")
	public Boolean agrementEnvoye(@PathVariable Long id) {
		return userService.agrementEnvoye(id);
	}
	

	
	
}