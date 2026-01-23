// package com.auth.authimplementation.DTO;

// //this will be sent as a response when user registers
// public class UserResponseDTO {
//     private Long id;//to hold the null value
//     private String username;
//     private String message;
//     //constructor to set all fields
//     public UserResponseDTO(Long id, String username, String message){
//         this.id=id;
//         this.username=username;
//         this.message=message;
//     }
//     //default constructor
//     public UserResponseDTO(){}

//     //Setters
//     public void setId(long id){
//         this.id=id;
//     }
//     public void setUsername(String username){
//         this.username=username;
//     }
//     public void setMessage(String message){
//         this.message=message;
//     }

//     //Getters

//     public long getId(){
//         return this.id;
//     }

//     public String getUsername(){
//         return this.username;
//     }
//     public String getMessage(){
//         return this.message;
//     }
// }


package com.auth.authimplementation.DTO;

public class UserResponseDTO {
    private Long id; // Wrapper class to handle null values
    private String username;
    private String message;

    // Constructor to set all fields
    public UserResponseDTO(Long id, String username, String message) {
        this.id = id;
        this.username = username;
        this.message = message;
    }

    // Default constructor
    public UserResponseDTO() {}

    // Setters
    public void setId(Long id) { // Change parameter type to Long
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    // Getters
    public Long getId() { // Change return type to Long
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getMessage() {
        return message;
    }
}
