package com.example.MyMart.DTO.Response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressResponse {
    private int houseNo;
    private String city;
    private String state;
    private long pincode;
    private CustomerResponse customer;
}
