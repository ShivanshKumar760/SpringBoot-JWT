package com.ptwo.jwtpractice.dto;


//3rd file to create after creating user entity and user repository is to create a dto for register request and register response
public class RegisterDTO {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
