package com.ptwo.jwtpractice.Config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ptwo.jwtpractice.Filter.JwtAuthFilter;
import com.ptwo.jwtpractice.service.UserService;
import com.ptwo.jwtpractice.util.JwtUtil;

//12th file to be created after creating the filter

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JwtAuthFilter jwtAuthFilter;

    public SecurityConfig(UserService userService,JwtUtil jwtUtil) {
        this.jwtAuthFilter = new JwtAuthFilter(userService, jwtUtil);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                // Disable CSRF for JWT-based APIs
                .csrf(csrf -> csrf.disable())
                // 🔥 REQUIRED FOR H2
        .headers(headers ->
            headers.frameOptions(frame -> frame.disable())
        )

                // Configure stateless session
                // .sessionManagement(session ->
                //         session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                // )
                .sessionManagement(session ->
    session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
)

                .formLogin(form -> form.disable()) // Disable form login
                .httpBasic(httpBasic -> httpBasic.disable()) // Disable HTTP Basic authentication
                // Authorization rules
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/auth/**",
                "/h2-console/**").permitAll()
                        .anyRequest().authenticated()
                )

                // Add JWT filter before UsernamePasswordAuthenticationFilter
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    
}
