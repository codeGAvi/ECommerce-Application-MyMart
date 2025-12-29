package com.example.MyMart.DTO.Request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class orderItemsRequest {
    private int product_Id;
    private int quantity;
}
