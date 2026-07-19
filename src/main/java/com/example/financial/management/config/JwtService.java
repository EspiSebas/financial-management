package com.example.financial.management.config;

import com.example.financial.management.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    public String generateToken(User user) {

    }

    public String extractUsername(String token) {

    }

    public boolean isTokenValid(String token, UserDetails userDetails) {

    }

}
