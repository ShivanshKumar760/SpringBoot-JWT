package com.pone.jwtauth.Mapper;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.pone.jwtauth.Entity.User;

public class toEntity {
    public static User mapRegisterRequestDTOtoUser(com.pone.jwtauth.DTO.RegisterRequestDTO dto) {
        com.pone.jwtauth.Entity.User user = new com.pone.jwtauth.Entity.User();
        PasswordEncoder passwordEncoder = new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        return user;
    }
}
