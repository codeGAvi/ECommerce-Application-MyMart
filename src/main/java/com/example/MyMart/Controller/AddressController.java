package com.example.MyMart.Controller;

import com.example.MyMart.DTO.Request.AddressRequest;
import com.example.MyMart.DTO.Response.AddressResponse;
import com.example.MyMart.Service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Slf4j

@RestController
@RequestMapping("/api/v1/address")
public class AddressController {

    Logger logger = LoggerFactory.getLogger(AddressController.class);

    @Autowired
    AddressService addressService;

    @PostMapping
    public ResponseEntity addAddress(@RequestParam("customer_id") int customer_id,@RequestBody AddressRequest addressRequest){
        try{
           logger.info("adding address" + addressRequest);
           AddressResponse addressResponse =  addressService.addAddress(customer_id, addressRequest);
           return new ResponseEntity<>(addressResponse,HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
