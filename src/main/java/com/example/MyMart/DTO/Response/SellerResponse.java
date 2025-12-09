package com.example.MyMart.DTO.Response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SellerResponse {
    private String name;
    private String email;
}