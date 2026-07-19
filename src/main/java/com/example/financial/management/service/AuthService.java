package com.example.financial.management.service;

import com.example.financial.management.dto.auth.AuthResponse;
import com.example.financial.management.dto.auth.LoginRequest;
import com.example.financial.management.entity.User;
import com.example.financial.management.repository.AuthRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(AuthRepository authRepository, PasswordEncoder passwordEncoder) {
        this.authRepository = authRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String register(String name,String email, String password){
        if(authRepository.existsByEmail(email)){
            throw  new RuntimeException("User already exists");
        }

        User user = new User();
        String passwordBy = passwordEncoder.encode(password);

        user.setName(name);
        user.setEmail(email);
        user.setPassword(passwordBy);

        authRepository.save(user);

        return "User created correctly";
    }


    public AuthResponse login(String email, String password) {

        User user = authRepository.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return null;
    }


}
