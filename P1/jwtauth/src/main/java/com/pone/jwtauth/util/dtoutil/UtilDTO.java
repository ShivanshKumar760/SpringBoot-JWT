package com.pone.jwtauth.util.dtoutil;

import com.pone.jwtauth.DTO.LoginRequestDTO;



public class UtilDTO {
    public static String toUsername(LoginRequestDTO dto ) {
        return dto.getUsername();

    }
    public static String toPassword(LoginRequestDTO dto){
        return dto.getPassword();
    }
}
