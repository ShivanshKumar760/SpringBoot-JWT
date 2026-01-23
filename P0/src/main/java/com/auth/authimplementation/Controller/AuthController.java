package com.auth.authimplementation.Controller;

// import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.authimplementation.DTO.AuthResponse;
import com.auth.authimplementation.DTO.LoginDTO;
import com.auth.authimplementation.DTO.RegisterDTO;
import com.auth.authimplementation.DTO.UserResponseDTO;
// import com.auth.authimplementation.Entity.User;
import com.auth.authimplementation.Mapper.toResponse;
import com.auth.authimplementation.Service.AuthService;
import com.auth.authimplementation.Service.UserService;
import com.auth.authimplementation.utils.JwtUtils;

@RestController
@RequestMapping("/auth")
public class AuthController {
// private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtil;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthService authService;

    public AuthController( JwtUtils jwtUtil,
                          UserService userService, PasswordEncoder passwordEncoder, AuthService authService) {
        // this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authService = authService;
    }


    @PostMapping("/signup")
    public UserResponseDTO signup(@RequestBody RegisterDTO request){
        if (userService.userExists(request.getUsername())) {
            System.out.println("Username already exists: " + request.getUsername());
            return new UserResponseDTO(null, null, "Username already exists");
        }

        return userService.saveUser(request,passwordEncoder);
    }


//     @PostMapping("/login")
//     public AuthResponse login(@RequestBody LoginDTO request) {
//         try {
//             String username= AuthMapper.toUsername(request);
// //            String userName=request.getUsername();
//             String pass=AuthMapper.toPassword(request);
//             Authentication auth= authenticationManager.authenticate(
//                     new UsernamePasswordAuthenticationToken(username, pass)
//             );

// //            if(auth.getName().equals(request.getUsername())){
// //
// //                System.out.println(auth.getName());
// //
// //
// //            }
//             System.out.println(auth.getName());
//             if (!auth.getName().equals(request.getUsername())) {
//                 throw new RuntimeException("Invalid credentials");
//             }
//             String token = jwtUtil.generateToken(request.getUsername());
// //          return new LoginResponseDTO(token);
//             return AuthMapper.toReponse(token);

//         } catch (AuthenticationException ex) {
//             throw new RuntimeException("Invalid username or password");
//         }
//     }


    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginDTO request) {
        String validatedUsername = authService.login(request);
        String token = jwtUtil.generateToken(validatedUsername);
        return toResponse.mapTokenToAuthResponse(token);
    }

}

