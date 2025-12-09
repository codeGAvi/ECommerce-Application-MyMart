package com.example.MyMart.Controller;

import com.example.MyMart.DTO.Request.CustomerRequest;
import com.example.MyMart.DTO.Response.CustomerResponse;
import com.example.MyMart.ENUM.Gender;
import com.example.MyMart.Entity.Customer;
import com.example.MyMart.Exception.CustomerNotFoundException;
import com.example.MyMart.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping
    public ResponseEntity addCustomer(@RequestBody CustomerRequest customerRequest){
       CustomerResponse customerResponse =  customerService.addCustomer(customerRequest);
        return new ResponseEntity<>(customerResponse, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity getCustomerById(@RequestParam("id") int id){
        try {
            CustomerResponse customerResponse  = customerService.getCustomerById(id);
            return  new ResponseEntity(customerResponse,HttpStatus.FOUND);
        }catch (CustomerNotFoundException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    // filter the customer based on male  and return list of customer response
    @GetMapping("/gender/{gender}")
    public ResponseEntity getCustomerByGender(@PathVariable Gender gender){
        List<CustomerResponse> customerResponsesList = customerService.getCustomerByGender(gender);
        return new ResponseEntity<>(customerResponsesList,HttpStatus.FOUND);
    }

    // filter the customer based on age
    @GetMapping("/age/{age}")
    public ResponseEntity getCustomerByAge(@PathVariable int age){
        List<CustomerResponse> customerResponses = customerService.getCustomerByAge(age);
        return new ResponseEntity<>(customerResponses,HttpStatus.FOUND);
    }
}
