package com.pone.jwtauth.Service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pone.jwtauth.DTO.RegisterRequestDTO;
import com.pone.jwtauth.DTO.RegisterResponseDTO;
import com.pone.jwtauth.Entity.User;
import com.pone.jwtauth.Mapper.toEntity;
import com.pone.jwtauth.Mapper.toResponse;
import com.pone.jwtauth.Repo.UserRepo;

@Service
public class UserService  implements UserDetailsService {
    private final UserRepo userRepo;
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
    
    //register new user function:
    public RegisterResponseDTO registerUser(RegisterRequestDTO dto){
        //check if user already exists
        if(userRepo.findByUsername(dto.getUsername()).isPresent()){
            System.out.println("Username already exists: " + dto.getUsername());
            return new RegisterResponseDTO(null, null, "Username already exists");
        }
        //map dto to entity
        User user = toEntity.mapRegisterRequestDTOtoUser(dto);
        //save user to db
        userRepo.save(user);
        //return response dto
        RegisterResponseDTO responseDTO = toResponse.mapToRegisterResponseDTO(user, "User registered successfully");
        return responseDTO;
    }

    public User saveUserDirect(User user){
        return userRepo.save(user);
    }

    //loadUserByUsername which will be used by spring security-jwt auth filter
    @Override
    public UserDetails loadUserByUsername(@org.jspecify.annotations.NonNull String usernameOrEmail)
    throws UsernameNotFoundException{
        User user = userRepo.findByUsername(usernameOrEmail)
        .orElseThrow(()-> new UsernameNotFoundException("User not found with username: " + usernameOrEmail));
         return new org.springframework.security.core.userdetails.User(
            user.getUsername(),
            user.getPassword(),
            new java.util.ArrayList<>()
        );
    }

    public boolean userExists(String username){
        return userRepo.findByUsername(username).isPresent();
    }


    public User getUserEntity(String username){
        return userRepo.findByUsername(username).orElseThrow(()->new RuntimeException("User not found"));
    }
}
