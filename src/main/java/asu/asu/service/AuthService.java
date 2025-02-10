package com.example.datingapp.service;

import com.example.datingapp.dto.LoginRequest;
import com.example.datingapp.dto.RegisterRequest;
import com.example.datingapp.model.User;
import com.example.datingapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public boolean authenticateUser(LoginRequest request) {
        Optional<User> user = userRepository.findByEmail(request.getEmail());
        return user.isPresent() && passwordEncoder.matches(request.getPassword(), user.get().getPassword());
    }

    public void registerUser(RegisterRequest request) {
        User newUser = new User();
        newUser.setName(request.getName());
        newUser.setEmail(request.getEmail());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setProfilePictureUrl(request.getProfilePictureUrl());
        userRepository.save(newUser);
    }
}
