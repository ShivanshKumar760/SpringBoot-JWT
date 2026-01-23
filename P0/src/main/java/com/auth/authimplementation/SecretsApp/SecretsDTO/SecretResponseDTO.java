package com.auth.authimplementation.SecretsApp.SecretsDTO;

public class SecretResponseDTO {
    private long id;
    private String secret;
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getSecret() {
        return this.secret;
    }
    public void setSecret(String secret) {
        this.secret = secret;
    }
    
}
