package com.ptwo.jwtpractice.Mapper;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.ptwo.jwtpractice.Entity.User;
import com.ptwo.jwtpractice.dto.RegisterDTO;
//8.5th file to be created
public class toEntity {
    public static User mapRegisterRequestDTOToUser(RegisterDTO registerRequestDTO) {
        User user = new User();
        user.setUsername(registerRequestDTO.getUsername());
        //i dont want to save password in database as plain text so i will encrypt it using bcrypt and then save it in database
        PasswordEncoder passwordEncoder = new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
        return user;
    }
}
