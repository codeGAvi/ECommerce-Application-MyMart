package com.example.MyMart.DTO.Request;

import com.example.MyMart.ENUM.Category;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {
    private  String name;
    private double price;
    private Category category;
}
