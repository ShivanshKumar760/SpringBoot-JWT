package com.pone.jwtauth.Mapper;

import com.pone.jwtauth.DTO.AuthResponseDTO;
import com.pone.jwtauth.DTO.RegisterResponseDTO;
import com.pone.jwtauth.Entity.User;

public class toResponse {
    //UserResponse after registration
    public static RegisterResponseDTO mapToRegisterResponseDTO(User user,String message) {
        RegisterResponseDTO responseDTO = new RegisterResponseDTO();
        responseDTO.setId(user.getId());
        responseDTO.setUsername(user.getUsername());
        responseDTO.setMessage(message);
        return responseDTO;
    }   

    //token response after login
    public static AuthResponseDTO mapToAuthResponseDTO(String token) {
        AuthResponseDTO authResponseDTO = new AuthResponseDTO();
        authResponseDTO.setToken(token);
        return authResponseDTO;
    }
}
