package com.example.MyMart.Controller;

import com.example.MyMart.Entity.Customer;
import com.example.MyMart.Exception.CustomerNotFoundException;
import com.example.MyMart.INPUT_Requests.CustomerRequest;
import com.example.MyMart.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping
    public ResponseEntity addCustomer(@RequestBody Customer customer){
       Customer savedCustomer =  customerService.addCustomer(customer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }
    


    @GetMapping
    public ResponseEntity getCustomerById(@RequestParam("id") int id){
        try {
            Customer customer = customerService.getCustomerById(id);
            return  new ResponseEntity(customer,HttpStatus.FOUND);
        }catch (CustomerNotFoundException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }


    }
}
