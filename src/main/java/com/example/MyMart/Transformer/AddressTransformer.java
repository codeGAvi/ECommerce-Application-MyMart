package com.example.MyMart.Transformer;


import com.example.MyMart.DTO.Request.AddressRequest;
import com.example.MyMart.DTO.Response.AddressResponse;
import com.example.MyMart.Entity.Address;
import lombok.experimental.UtilityClass;


@UtilityClass
public class AddressTransformer {

    public Address addressRequestToAddress(AddressRequest addressRequest){
        return Address.builder()
                .state(addressRequest.getState())
                .city(addressRequest.getCity())
                .houseNo(addressRequest.getHouseNo())
                .pincode(addressRequest.getPincode())
                .build();
    }

    public AddressResponse addressToAddressResponse(Address address){
        return AddressResponse.builder()
                .city(address.getCity())
                .state(address.getState())
                .houseNo(address.getHouseNo())
                .pincode(address.getPincode())
                .customer(CustomerTransformer.customerToCustomerResponse(address.getCustomer()))
                .build();
    }
}
