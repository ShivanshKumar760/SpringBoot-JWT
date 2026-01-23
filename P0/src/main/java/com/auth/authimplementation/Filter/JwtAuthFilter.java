package com.auth.authimplementation.Filter;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import com.auth.authimplementation.Service.UserService;
import com.auth.authimplementation.utils.JwtUtils;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter{
    private final JwtUtils jwtUtils;
    private final UserService userService;
    public JwtAuthFilter(JwtUtils jwtUtils, UserService userService) {
        this.jwtUtils = jwtUtils;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,FilterChain filterChain) throws ServletException, IOException {

            String authHeader = request.getHeader("Authorization");
            if(authHeader!=null && authHeader.startsWith("Bearer ")){
                String token= authHeader.substring(7);
                if(jwtUtils.validateTokenBooleanWithClaims(token)){
                    String username = jwtUtils.extractUserNameWithClaims(token);
                    System.out.println("Authenticated user: " + username);
                    UserDetails userDetails = userService.loadUserByUsername(username);

                    UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                    );

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
            filterChain.doFilter(request, response);
    }
    
}
