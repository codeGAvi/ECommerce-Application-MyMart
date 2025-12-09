package com.example.MyMart.DTO.Request;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressRequest {
    private int houseNo;
    private String city;
    private String state;
    private long pincode;
}
