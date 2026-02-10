package com.ptwo.jwtpractice.util.dtoutil;

import com.ptwo.jwtpractice.dto.LoginRequestDTO;

//7th file to be created
public class UtilDTO {
    public static String toUsername(LoginRequestDTO loginRequestDTO) {
        return loginRequestDTO.getUsername();
    }

    public static String toPassword(LoginRequestDTO loginRequestDTO) {
        return loginRequestDTO.getPassword();
    }
}
