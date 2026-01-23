package com.auth.authimplementation.DTO;

//this will be sent as a response when user logs in
public class AuthResponse {
    private String token;

    //Setter
    public void setToken(String token){
        this.token=token;
    }

    //Getter
    public String getToken(){
        return this.token;
    }
}
