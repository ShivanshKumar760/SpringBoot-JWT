package com.ptwo.jwtpractice.Mapper;

import com.ptwo.jwtpractice.Entity.User;
import com.ptwo.jwtpractice.dto.AuthResponseDTO;
import com.ptwo.jwtpractice.dto.RegisterResponseDTO;
//8.5.1th file to be created
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
