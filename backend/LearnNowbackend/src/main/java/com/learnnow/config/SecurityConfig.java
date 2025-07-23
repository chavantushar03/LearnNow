package com.learnnow.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
<<<<<<< HEAD
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
=======
>>>>>>> abhishek
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.learnnow.security.JwtAuthenticationFilter;
<<<<<<< HEAD

=======
	
>>>>>>> abhishek
@Configuration
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
<<<<<<< HEAD
                .requestMatchers("/auth/**","/feedbacks/**").permitAll()
=======
                .requestMatchers("/auth/**",            // Login/Register APIs
                        "/questions/**" ,     // 👈 Quiz Question APIs (public)
                        "/swagger-ui/**",         // 👈 Swagger UI static files
                        "/v3/api-docs/**",        // 👈 OpenAPI JSON docs
                        "/swagger-ui.html",       // 👈 Swagger main page (Spring Boot 2.x fallback)
                        "/webjars/**" ,
                        "/teachers/**",
                        "/courses/**",
                        "/students/**",
                        "/quizzes/**"
                		).permitAll()
>>>>>>> abhishek
                .anyRequest().authenticated()
            )
            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
