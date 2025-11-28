package com.example.MyMart.DTO.Response;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponse {
    private String name;
    private String Email;
    private Date createdAt;
}
