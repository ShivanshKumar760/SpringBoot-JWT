package com.auth.authimplementation.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue
    private long id;
    private String username;
    private String password;

    //Setters
    public void setId(long id){
        this.id=id;
    }
    public void setUsername(String username){
        this.username=username;
    }
    public void setPassword(String password){
        this.password=password;
    }

    //Getters

    public long getId(){
        return this.id;
    }

    public String getUsername(){{
        return this.username;
    }}

    public String getPassword(){
        return this.password;
    }


}
