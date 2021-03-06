package com.example.location_voiture_backend.controllers;

import com.example.location_voiture_backend.entities.*;
import com.example.location_voiture_backend.repositories.ImageRepository;
import com.example.location_voiture_backend.repositories.RoleRepository;
import com.example.location_voiture_backend.repositories.UserRepository;
import com.example.location_voiture_backend.payload.request.LoginRequest;
import com.example.location_voiture_backend.payload.request.SignupRequest;
import com.example.location_voiture_backend.payload.response.JwtResponse;
import com.example.location_voiture_backend.payload.response.MessageResponse;
import com.example.location_voiture_backend.security.jwt.JwtUtils;
import com.example.location_voiture_backend.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	ImageRepository imageRepository;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		User u = userRepository.findById(userDetails.getId()).get();

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(),
												 roles,
											     u.getImages()));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()),
				 			 signUpRequest.getNom(),
							signUpRequest.getPrenom(),
							signUpRequest.getTelephone(),
							signUpRequest.getAdresse(),
							signUpRequest.getVille(),
							signUpRequest.getPays(),
							null
							);
		user.setActive(true);

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "agent":
					Role agentRole = roleRepository.findByName(ERole.ROLE_AGENT)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(agentRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(user);
	}

	@PostMapping("/upload/image")
	public ResponseEntity<MessageResponse> uplaodImage(@RequestParam("image") MultipartFile file )
			throws IOException {

		imageRepository.save(Image.builder()
				.name(file.getOriginalFilename())
				.type(file.getContentType())
				.image(ImageUtility.compressImage(file.getBytes())).build());

		return ResponseEntity.status(HttpStatus.OK)
				.body(new MessageResponse("Image uploaded successfully: " +
						file.getOriginalFilename()));
	}

	@PostMapping("/uploadImage/{idUser}")
	public ResponseEntity<MessageResponse> uploadImageUser(@RequestParam("image") MultipartFile file ,
														   @PathVariable int idUser ,
														   @RequestHeader Map<String, String> headers)
			throws IOException {

		Image image = Image.builder()
				.name(file.getOriginalFilename())
				.type(file.getContentType())
				.image(ImageUtility.compressImage(file.getBytes())).build();
		if(headers.get("typeimage").equals("profilImage")){ image.setTypeImage(TypeImage.PROFIL_IMAGE);}
		else if(headers.get("typeimage").equals("cinRecto")){ image.setTypeImage(TypeImage.CIN_RECTO);}
		else if(headers.get("typeimage").equals("cinVerso")){ image.setTypeImage(TypeImage.CIN_VERSO);}
		else if(headers.get("typeimage").equals("agrement")){ image.setTypeImage(TypeImage.AGREMENT);}


		//image.setUser(userRepository.getById(Long.parseLong(String.valueOf(idUser))));
		User u = userRepository.getById(Long.parseLong(String.valueOf(idUser)));
		image.setUser(u);
		imageRepository.save(image);



		return ResponseEntity.status(HttpStatus.OK)
				.body(new MessageResponse("Image uploaded successfully: " +
						file.getOriginalFilename()));
	}

	@GetMapping("/test2")
	public String test2(@RequestHeader Map<String, String> headers){
		return headers.get("typeImage");
	}

	@GetMapping(path = {"/get/imageProfil/info/{idUser}"})
	public Image getImageDetails(@PathVariable("idUser") int idUser) throws IOException {

		final Optional<Image> dbImage = imageRepository.findByUserIdAndAndTypeImage(Long.parseLong(String.valueOf(idUser)) , TypeImage.valueOf("PROFIL_IMAGE"));

		return Image.builder()
				.name(dbImage.get().getName())
				.type(dbImage.get().getType())
				.image(ImageUtility.decompressImage(dbImage.get().getImage())).build();
	}

	@GetMapping(path = {"/get/profileImage/{idUser}"})
	public ResponseEntity<byte[]> getImage(@PathVariable("idUser") int idUser) throws IOException {

		final Optional<Image> dbImage = imageRepository.findByUserIdAndAndTypeImage(Long.parseLong(String.valueOf(idUser)) , TypeImage.valueOf("PROFIL_IMAGE"));

		return ResponseEntity
				.ok()
				.contentType(MediaType.valueOf(dbImage.get().getType()))
				.body(ImageUtility.decompressImage(dbImage.get().getImage()));
	}
}
