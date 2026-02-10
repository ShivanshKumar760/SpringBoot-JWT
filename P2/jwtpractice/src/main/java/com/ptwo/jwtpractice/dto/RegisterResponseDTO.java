package com.ptwo.jwtpractice.dto;

//4th file to create after creating user entity and user repository and register request dto is to create a register response dto to send response to the user after registration

public class RegisterResponseDTO {
     private Long id;
    private String username;
    private String message;
    //we need to send message to the user cause chances are sometimes user will try to register with existing username so we need to send message to the user that username already exist or registration successful
    //and if user try to register with an existing username it will throw an exception so we need to handle that exception and send message to the user that username already exist

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
