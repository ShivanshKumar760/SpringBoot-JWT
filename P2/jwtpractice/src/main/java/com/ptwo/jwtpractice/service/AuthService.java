package com.ptwo.jwtpractice.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.ptwo.jwtpractice.dto.LoginRequestDTO;

//9th file to be created
@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;

    public AuthService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public String login(LoginRequestDTO loginRequestDTO) {
        String username = loginRequestDTO.getUsername();
        String password = loginRequestDTO.getPassword();

        //or 

        // String username = UtilDTO.toUsername(loginRequestDTO);
        // String password = UtilDTO.toPassword(loginRequestDTO);

        // Perform authentication using the authentication manager

        Authentication auth = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(username, password)
        );


        System.out.println("Authentication successful for user: " + auth.getName());

        if (!auth.getName().equals(loginRequestDTO.getUsername())) {
            throw new RuntimeException("Invalid credentials");
        }

        return loginRequestDTO.getUsername();
    }
}
