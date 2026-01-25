package com.pone.jwtauth.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pone.jwtauth.DTO.AuthResponseDTO;
import com.pone.jwtauth.DTO.LoginRequestDTO;
import com.pone.jwtauth.DTO.RegisterResponseDTO;
import com.pone.jwtauth.Mapper.toResponse;
import com.pone.jwtauth.Service.AuthService;
import com.pone.jwtauth.Service.UserService;
import com.pone.jwtauth.util.JwtUtil;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(AuthService authService, UserService userService, JwtUtil jwtUtil) {
        this.authService = authService;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/signup")
    public RegisterResponseDTO registerUser(@RequestBody com.pone.jwtauth.DTO.RegisterRequestDTO registerDTO) {
        return userService.registerUser(registerDTO);
    }

    @PostMapping("/login")
        public AuthResponseDTO login(@RequestBody LoginRequestDTO request) {
        String validatedUsername = authService.login(request);
        String token = jwtUtil.generateToken(validatedUsername);
        return toResponse.mapToAuthResponseDTO(token);
    }
    
    

}
