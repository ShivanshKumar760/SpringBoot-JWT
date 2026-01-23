package com.auth.authimplementation.Service;

import java.util.Collections;


import org.jspecify.annotations.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

// import com.auth.authimplementation.Config.SecurityConfig;
// import com.auth.authimplementation.DTO.AuthResponse;
// import com.auth.authimplementation.DTO.LoginDTO;
import com.auth.authimplementation.DTO.RegisterDTO;
import com.auth.authimplementation.DTO.UserResponseDTO;
import com.auth.authimplementation.Entity.User;
import com.auth.authimplementation.Mapper.toEntity;
// import com.auth.authimplementation.Mapper.toResponse;
import com.auth.authimplementation.Repository.UserRepo;

//Mapper
//toEntity have two method mapRegisterDTOToUser and mapLoginDTOToUser
//toResponse have two method mapUserToUserResponse and mapTokenToAuthResponse
@Service
public class UserService implements UserDetailsService {
    private UserRepo userRepo;
    // private final AuthenticationManager authenticationManager;
    // public UserService(UserRepo userRepo, AuthenticationManager authenticationManager){
    //     this.userRepo=userRepo;
    //     this.authenticationManager=authenticationManager;
    // }

    public UserService(UserRepo userRepo){
        this.userRepo=userRepo;
    }

    //register user service
    public UserResponseDTO saveUser(RegisterDTO registerDTO,PasswordEncoder passwordEncoder){
        User user= toEntity.mapRegisterDTOToUser(registerDTO,passwordEncoder);
        User savedUser=userRepo.save(user);
        // UserResponseDTO userResponse= new UserResponseDTO();
        // userResponse.setId(savedUser.getId());
        // userResponse.setUsername(savedUser.getUsername());
        // return userResponse;
        return com.auth.authimplementation.Mapper.toResponse.mapUserToUserResponse(savedUser);
    }

    //login user service
    // public String login(LoginDTO loginDTO){
    //     String username = toResponse.toUsername(loginDTO);
    //     String password = toResponse.toPassword(loginDTO);
    //     Authentication auth = authenticationManager.authenticate(
    //         new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(username, password)
    //     );
    //     System.out.println("Authentication successful for user: " + auth.getName());
    //     LoginDTO request=loginDTO;
    //     if(!auth.getName().equals(request.getUsername())) {
    //         throw new RuntimeException("Invalid credentials");
    //     }
    //     return request.getUsername();
        
    // } cant create here cause :
//     GitHub Copilot
// The problem lies in the circular dependency between the JwtAuthFilter, UserService, and SecurityConfig classes. Here's the dependency chain:

// SecurityConfig depends on JwtAuthFilter (to add it to the security filter chain).
// JwtAuthFilter depends on UserService (to load user details during token validation).
// UserService indirectly depends on SecurityConfig (via AuthenticationManager, which is typically defined as a bean in SecurityConfig).
// This creates a circular dependency that Spring cannot resolve.

    public User saveUserDirect(User user){
        return userRepo.save(user);
    }


    @Override
    //crucial method for authentication
    //how
    //1. loadUserByUsername is called by spring security authentication manager
    //2. it fetches user from database using userRepo
    //3. if user is found, it returns UserDetails object with username, password and authorities
    public UserDetails loadUserByUsername(@NonNull String usernameOremail) throws UsernameNotFoundException{
        User user=userRepo.findByUsername(usernameOremail).orElseThrow(()->new RuntimeException("User not found"));
        
        
        return new org.springframework.security.core.userdetails.User(
            user.getUsername(),
            user.getPassword(),
            Collections.emptyList()
        );
    }

    public boolean userExists(String username){
        return userRepo.findByUsername(username).isPresent();
    }


    public User getUserEntity(String username){
        return userRepo.findByUsername(username).orElseThrow(()->new RuntimeException("User not found"));
    }


    
}
