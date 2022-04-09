package com.example.location_voiture_backend.services;

import com.example.location_voiture_backend.entities.*;

import com.example.location_voiture_backend.repositories.RoleRepository;
import com.example.location_voiture_backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean agrementEnvoye(Long idUser) {
        User user = userRepository.findById(idUser).get();
        for(Image i : user.getImages()){
            if(i.getTypeImage() == TypeImage.AGREMENT) return true;
        }
        return false;
    }
}
