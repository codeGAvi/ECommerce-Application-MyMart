package com.example.MyMart.DTO.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

// Authenticate request
public class LoginRequest {
    private String username;
    private String password;
}