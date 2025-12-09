package com.example.MyMart.Transformer;

import com.example.MyMart.DTO.Request.CustomerRequest;
import com.example.MyMart.DTO.Response.CustomerResponse;
import com.example.MyMart.Entity.Customer;
import lombok.Builder;
import lombok.experimental.UtilityClass;


@UtilityClass   // make all members static
public class CustomerTransformer {

    public CustomerResponse customerToCustomerResponse(Customer customer){
//        CustomerResponse customerResponse = new CustomerResponse();
//        customerResponse.setName(customer.getName());
//        customerResponse.setEmail(customer.getEmail());
//        customerResponse.setCreatedAt(customer.getCreatedAt());
//
//        return  customerResponse;

        // use builder
        return CustomerResponse.builder()
                .name(customer.getName())
                .Email(customer.getEmail())
                .createdAt(customer.getCreatedAt())
                .build();
    }

    public  Customer customerRequestToCustomer(CustomerRequest customerRequest){
//        Customer customer = new Customer();
//        customer.setEmail(customerRequest.getEmail());
//        customer.setAge(customerRequest.getAge());
//        customer.setGender(customerRequest.getGender());
//        customer.setMob_No(customerRequest.getMobNo());
//        customer.setName(customerRequest.getName());
//        return  customer;

        // using Builder
        return Customer.builder()
                .name(customerRequest.getName())
                .age(customerRequest.getAge())
                .email(customerRequest.getEmail())
                .gender(customerRequest.getGender())
                .mob_no(customerRequest.getMob_no())
                .build();
    }
}
