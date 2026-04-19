package com.example.MyMart.Controller;

import com.example.MyMart.Configuration.CustomUserDetails;
import com.example.MyMart.Service.JwtUtilService;
import com.example.MyMart.DTO.Request.LoginRequest;
import com.example.MyMart.DTO.Response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtilService jwtUtilService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest loginRequest){

        // 1. Authenticate username + password (using AuthenticationManager)
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            // 2. Get user details from authentication object
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();


            // 3. If authentication successful, generate JWT token
            String token = jwtUtilService.generateToken(userDetails);


            // 4. Return token + user info in response
            return ResponseEntity.ok(new LoginResponse(
                    token,
                    userDetails.getUsername(),
                    userDetails.getAuthorities().toString()
            ));
        }
        catch(BadCredentialsException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
        catch(UsernameNotFoundException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed: User not found");
        }
    }
}