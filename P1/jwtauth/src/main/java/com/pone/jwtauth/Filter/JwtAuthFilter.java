package com.pone.jwtauth.Filter;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.pone.jwtauth.Service.UserService;
import com.pone.jwtauth.util.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter{ 
    private final UserService userService;
    private final JwtUtil   jwtUtil;
    public JwtAuthFilter(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }


    //this is a middleware that will intercept every request to check for jwt token in the authorization header
    //filterchain is like next() in expressjs
    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // TODO Auto-generated method stub

        //extract string authorization header
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;
        //check if header is present and starts with Bearer
        if(authHeader != null && authHeader.startsWith("Bearer ")){
            token = authHeader.substring(7);
            try{
                username = jwtUtil.extractUsername(token);
                System.out.println("Authenticated user: " + username);
                UserDetails userDetails = userService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }catch(Exception e){
                System.out.println("Invalid token: " + e.getMessage());
            }
        }
        filterChain.doFilter(request, response);
        
    }
}
