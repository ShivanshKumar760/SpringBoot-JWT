package com.ptwo.jwtpractice.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.ptwo.jwtpractice.dto.LoginRequestDTO;

//9th file to be created can only be created after 
//loadByUsername method is created in the UserService class and the UserDetailsService interface is implemented in the UserService class
//and jwtutil class is created and the jwt auth filter is created
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
            //What this will do is it will take the username and password and it will check if 
            // the user exists in the database and if the password is correct. 
            //If the authentication is successful,
            //it will return an Authentication object that contains the authenticated user's details. 
            //If the authentication fails, it will throw an exception.

            //it internally calls the loadUserByUsername method of the UserDetailsService implementation to retrieve the user details and then checks the password against the stored password.
        );


        System.out.println("Authentication successful for user: " + auth.getName());

        if (!auth.getName().equals(loginRequestDTO.getUsername())) {
            throw new RuntimeException("Invalid credentials");
        }

        return loginRequestDTO.getUsername();
    }
}
