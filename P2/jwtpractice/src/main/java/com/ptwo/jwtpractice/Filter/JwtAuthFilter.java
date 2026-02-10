package com.ptwo.jwtpractice.Filter;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ptwo.jwtpractice.service.UserService;
import com.ptwo.jwtpractice.util.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//11th file to be created after creating the service 

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    private final UserService userService;
    private final JwtUtil jwtUtil;
    public JwtAuthFilter(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

     @Override

     public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) 
     throws ServletException , IOException{
        String authHeader=request.getHeader("Authorization");
        String token=null;
        String username=null;

        String path = request.getRequestURI();
        if (path.startsWith("/h2-console")) {
        filterChain.doFilter(request, response);
        return;
    }

        if(authHeader!=null && authHeader.startsWith("Bearer ")){
            token=authHeader.substring(7);
            try{
                if(jwtUtil.validateToken(token)){
                    username=jwtUtil.extractUsername(token);
                    // Here you can set the authentication in the security context if needed
                    System.out.println("Authenticated user: " + username);
                    UserDetails userDetails=userService.loadUserByUsername(username);
                    UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                    );

                    // Set the authentication in the security context
                    SecurityContextHolder.getContext().setAuthentication(authToken);


                }
            }catch(Exception e){
                System.out.println("Error validating token: " + e.getMessage());
            }
            
        }
        filterChain.doFilter(request, response);
     }
}
