package com.example.MyMart.DTO.Request;

import com.example.MyMart.ENUM.Gender;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminRegistrationRequest {
    private String name;
    private String username;
    private String password;
    private String email;
    private int age;
    private String phoneNumber;
    private Gender gender;
    private String secretKey;  // must match server-side key
}