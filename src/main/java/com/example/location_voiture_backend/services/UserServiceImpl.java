package com.example.location_voiture_backend.services;

import com.example.location_voiture_backend.entities.ERole;
import com.example.location_voiture_backend.entities.Role;
import com.example.location_voiture_backend.entities.User;

import com.example.location_voiture_backend.repositories.RoleRepository;
import com.example.location_voiture_backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements IUserService {


}
