package com.auth.authimplementation.SecretsApp.SecretsEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Secrets {
    @Id
    @GeneratedValue
    private long id;
    private String secret;

    // Getters and Setters
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
