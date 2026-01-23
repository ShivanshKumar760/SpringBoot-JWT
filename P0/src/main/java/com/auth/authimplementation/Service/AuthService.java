package com.auth.authimplementation.Service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.auth.authimplementation.DTO.LoginDTO;
import com.auth.authimplementation.Mapper.toResponse;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;

    public AuthService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public String login(LoginDTO loginDTO) {
        String username = toResponse.toUsername(loginDTO);
        String password = toResponse.toPassword(loginDTO);

        Authentication auth = authenticationManager.authenticate(
            new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(username, password)
        );

        System.out.println("Authentication successful for user: " + auth.getName());

        if (!auth.getName().equals(loginDTO.getUsername())) {
            throw new RuntimeException("Invalid credentials");
        }

        return loginDTO.getUsername();
    }
}
