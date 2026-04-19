//package com.example.MyMart.Configuration;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.ProviderManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//import java.util.Arrays;
//
//@Configuration
//public class SecurityConfig {
//
//    @Autowired
//    private CustomUserDetailsManager customUserDetailsManager;
//
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//        http
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(auth -> auth
//
//                        // Swagger
//                        .requestMatchers(
//                                "/swagger-ui/**",
//                                "/v3/api-docs/**",
//                                "/swagger-ui.html"
//                        ).permitAll()
//
//                        // Public
//                        .requestMatchers("/login").permitAll()
//                        .requestMatchers(HttpMethod.POST, "/api/v1/customer/**").permitAll()
//                        .requestMatchers(HttpMethod.GET, "/api/v1/product/**").permitAll()
//
//
//                        // USER
//                        .requestMatchers(HttpMethod.POST, "/api/v1/order/**").hasRole("USER")
//                        .requestMatchers(HttpMethod.POST, "/make-seller").hasRole("USER")
//
//                        // SELLER
//                        .requestMatchers(HttpMethod.POST, "/api/v1/product/**").hasRole("SELLER")
//                        .requestMatchers(HttpMethod.PUT, "/api/v1/product/**").hasRole("SELLER")
//                        .requestMatchers(HttpMethod.DELETE, "/api/v1/product/**").hasRole("SELLER")
//
//                        // ADMIN
//                       //  Admin register is public but secret-key protected
//                        .requestMatchers(HttpMethod.POST, "/api/v1/admin/register").permitAll()
//                        .requestMatchers("/api/v1/admin/**").hasRole("ADMIN")
//
//                        .anyRequest().authenticated()
//                )
//                .httpBasic();
//
//        return http.build();
//    }
//
//    // Authentication provider for Customer
//    @Bean
//    public DaoAuthenticationProvider customerAuthenticationProvider() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setUserDetailsService(customUserDetailsManager);
//        provider.setPasswordEncoder(passwordEncoder());
//        return provider;
//    }
//
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//}


// JWT Implementation
package com.example.MyMart.Configuration;

import com.example.MyMart.DTO.Request.LoginRequest;
import com.example.MyMart.DTO.Response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.net.Authenticator;

@Configuration
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsManager customUserDetailsManager;

    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                // ✅ Stateless — no sessions
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                .authorizeHttpRequests(auth -> auth

                        // Swagger
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-ui.html"
                        ).permitAll()

                        // Public
                        .requestMatchers("/login").permitAll()   //  first authenticate to get JWT token
                        .requestMatchers(HttpMethod.POST, "/api/v1/customer/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/product/**").permitAll()

                        // Admin register
                        .requestMatchers(HttpMethod.POST, "/api/v1/admin/register").permitAll()

                        // USER
                        .requestMatchers(HttpMethod.POST, "/api/v1/order/**").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/make-seller").hasRole("USER")

                        // SELLER
                        .requestMatchers(HttpMethod.POST, "/api/v1/product/**").hasRole("SELLER")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/product/**").hasRole("SELLER")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/product/**").hasRole("SELLER")

                        // ADMIN
                        .requestMatchers("/api/v1/admin/**").hasRole("ADMIN")

                        .anyRequest().authenticated()
                )

                // JWT filter instead of httpBasic
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider customerAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customUserDetailsManager);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    // Required for LoginController
    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(customerAuthenticationProvider());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
