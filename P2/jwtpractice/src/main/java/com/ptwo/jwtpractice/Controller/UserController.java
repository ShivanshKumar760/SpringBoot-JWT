package com.ptwo.jwtpractice.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ptwo.jwtpractice.Mapper.toResponse;
import com.ptwo.jwtpractice.dto.AuthResponseDTO;
import com.ptwo.jwtpractice.dto.LoginRequestDTO;
import com.ptwo.jwtpractice.dto.RegisterDTO;
import com.ptwo.jwtpractice.dto.RegisterResponseDTO;
import com.ptwo.jwtpractice.service.AuthService;
import com.ptwo.jwtpractice.service.UserService;
import com.ptwo.jwtpractice.util.JwtUtil;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



//the last file 
@RestController
@RequestMapping("/api/v1/auth")
public class UserController {
    private final AuthService authService;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public UserController(AuthService authService, UserService userService, JwtUtil jwtUtil) {
        this.authService = authService;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/signup")
    public RegisterResponseDTO signup(@RequestBody RegisterDTO registerDTO) {
        return userService.registerUser(registerDTO);
    }

    @PostMapping("/login")
    public AuthResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO) {
        String username=authService.login(loginRequestDTO);
        String token = jwtUtil.generateToken(username);
        // return new AuthResponseDTO(token);

        return toResponse.mapToAuthResponseDTO(token);
    }

    @GetMapping("/test")
    public String checkAuth() {
        return "Authenticated successfully!";
    }
    
    
    
}
