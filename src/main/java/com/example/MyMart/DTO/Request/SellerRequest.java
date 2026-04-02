package com.example.MyMart.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SellerRequest {
    private String name;
    private  int gst_no;
    private  String city;
    private  String email;
}
