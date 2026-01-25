package com.pone.jwtauth.DTO;

public class RegisterResponseDTO {
    private Long id;
    private String username;
    private String message;

    public RegisterResponseDTO(Long id, String username, String message) {
        this.id = id;
        this.username = username;
        this.message = message;
    }
    public RegisterResponseDTO() {
    }
    public Long getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public String getMessage() {
        return message;
    }

    //setters
    public void setId(Long id) {
        this.id = id;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    

}
