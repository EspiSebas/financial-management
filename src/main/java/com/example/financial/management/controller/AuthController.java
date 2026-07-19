package com.example.financial.management.controller;

import com.example.financial.management.dto.auth.AuthResponse;
import com.example.financial.management.dto.auth.LoginRequest;
import com.example.financial.management.dto.auth.RegisterRequest;
import com.example.financial.management.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request){

        authService.register(request.getName(), request.getEmail(), request.getPassword());

        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){

        return ResponseEntity.ok(authService.login(request.getEmail(), request.getPassword()));

    }
}
