package com.auth.authimplementation.DTO;

public class RegisterDTO {
    private String username;
    private String password;

    //Setters
    public void setUsername(String username){
        this.username=username;
    }
    public void setPassword(String password){
        this.password=password;
    }

    //Getters
    public String getUsername(){
        return this.username;
    }
    public String getPassword(){
        return this.password;
    }
    
}
