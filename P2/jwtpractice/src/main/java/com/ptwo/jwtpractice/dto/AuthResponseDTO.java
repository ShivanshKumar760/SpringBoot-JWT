package com.ptwo.jwtpractice.dto;
//6th file to be created
public class AuthResponseDTO {
    private String token;

    public AuthResponseDTO(String token) {
        this.token = token;

    }

    public AuthResponseDTO() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
