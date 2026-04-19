package com.example.MyMart.DTO.Request;

import com.example.MyMart.ENUM.Roles;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminRoleRequest {
    private String username;
    private Roles role;
}