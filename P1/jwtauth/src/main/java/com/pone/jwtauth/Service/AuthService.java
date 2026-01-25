package com.pone.jwtauth.Service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.pone.jwtauth.DTO.LoginRequestDTO;
import com.pone.jwtauth.util.dtoutil.UtilDTO;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;

    public AuthService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public String login(LoginRequestDTO loginDTO) {
        String username = UtilDTO.toUsername(loginDTO);
        String password = UtilDTO.toPassword(loginDTO);

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
