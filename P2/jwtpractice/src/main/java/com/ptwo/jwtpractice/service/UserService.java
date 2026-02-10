package com.ptwo.jwtpractice.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ptwo.jwtpractice.Entity.User;
import com.ptwo.jwtpractice.Mapper.toEntity;
import com.ptwo.jwtpractice.Mapper.toResponse;
import com.ptwo.jwtpractice.Repo.UserRepository;
import com.ptwo.jwtpractice.dto.RegisterDTO;
import com.ptwo.jwtpractice.dto.RegisterResponseDTO;

//8th file to be created
//We need to implement a interface for the user service, which will handle the business logic for user authentication and token generation. This service will be called by the controller to process login requests and generate JWT tokens.
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

   
    //Used by auth filter
    @Override
    public UserDetails loadUserByUsername(String UsernameOrEmail) throws UsernameNotFoundException {
        User user=userRepository.findByUsername(UsernameOrEmail).orElseThrow(()->new UsernameNotFoundException("User not found with username: "+UsernameOrEmail));
        return new org.springframework.security.core.userdetails.User(
            user.getUsername(),
            user.getPassword(),
            new java.util.ArrayList<>()
        );
    }

    public RegisterResponseDTO registerUser(RegisterDTO registerDTO){
        if(userRepository.findByUsername(registerDTO.getUsername()).isPresent()){
            System.out.println("Username already exists: " + registerDTO.getUsername());
            return new RegisterResponseDTO(null, null, "Username already exists");
        }

        //map dto to entity
        // User user=new User();
        // user.setUsername(registerDTO.getUsername());
        // user.setPassword(registerDTO.getPassword());

        //at this point a better way would be to create a mapper class to map dto to entity and vice versa but for simplicity we are doing it here
        User user=toEntity.mapRegisterRequestDTOToUser(registerDTO);
        userRepository.save(user);
        
        RegisterResponseDTO responseDTO=toResponse.mapToRegisterResponseDTO(user, "User registered successfully");
        return responseDTO;
    }

    
    public boolean userExists(String username){
        return userRepository.findByUsername(username).isPresent();
    }


    public User getUserEntity(String username){
        return userRepository.findByUsername(username).orElseThrow(()->new RuntimeException("User not found"));
    }


}
