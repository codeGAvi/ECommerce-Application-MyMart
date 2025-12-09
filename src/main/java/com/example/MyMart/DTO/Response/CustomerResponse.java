package com.example.MyMart.DTO.Response;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CustomerResponse {
    private String name;
    private String Email;
    private Date createdAt;
}
