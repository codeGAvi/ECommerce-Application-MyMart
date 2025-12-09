package com.example.MyMart.DTO.Response;

import com.example.MyMart.ENUM.Category;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {
    private  String name;
    private double price;
    private Category category;
    private SellerResponse seller;  // instead of returning entire seller entity just returning sellerResponse


    // used this in get review by id
    //private Category category;
    //private SellerResponse seller;  // instead of returning entire seller entity just returning sellerResponse
}
