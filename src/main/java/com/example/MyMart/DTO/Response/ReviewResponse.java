package com.example.MyMart.DTO.Response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewResponse {
     private String comment;
     private int rating;
     private ProductResponse product;
     private CustomerResponse customer;

     // used this in get review by id
    // private CustomerResponse customer;

}
