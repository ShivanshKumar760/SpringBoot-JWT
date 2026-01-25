package com.auth.authimplementation.Mapper;

import com.auth.authimplementation.DTO.AuthResponse;
import com.auth.authimplementation.DTO.LoginDTO;
import com.auth.authimplementation.DTO.UserResponseDTO;

public class toResponse {
    //UserResponse after registration
    public static UserResponseDTO mapUserToUserResponse(com.auth.authimplementation.Entity.User user){
        UserResponseDTO userResponse=new UserResponseDTO();
        userResponse.setId(user.getId());
        userResponse.setUsername(user.getUsername());
        userResponse.setMessage("User registered successfully");
        return userResponse;
    }

    //token after login

    public static AuthResponse mapTokenToAuthResponse(String token){
        AuthResponse authResponse=new AuthResponse();
        authResponse.setToken(token);
        return authResponse;
    }

    //we will need this 
    public static String toUsername(LoginDTO dto ) {
        return dto.getUsername();

    }
    public static String toPassword(LoginDTO dto){
        return dto.getPassword();
    }
}
