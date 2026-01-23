package com.auth.authimplementation.Mapper;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.auth.authimplementation.Entity.User;

public class toEntity {
    public static User mapRegisterDTOToUser(com.auth.authimplementation.DTO.RegisterDTO registerDTO,PasswordEncoder passwordEncoder){
        User user=new User();
        user.setUsername(registerDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        return user;
    }

    //for LoginDTO if needed in future
    public static User mapLoginDTOToUser(com.auth.authimplementation.DTO.LoginDTO loginDTO){
        User user=new User();
        user.setUsername(loginDTO.getUsername());
        user.setPassword(loginDTO.getPassword());
        return user;
    }
}
