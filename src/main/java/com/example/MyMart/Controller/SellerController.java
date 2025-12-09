package com.example.MyMart.Controller;

import com.example.MyMart.DTO.Request.SellerRequest;
import com.example.MyMart.DTO.Response.SellerResponse;
import com.example.MyMart.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/seller")
public class SellerController {

    @Autowired
    SellerService sellerService;

    @PostMapping
    public ResponseEntity addSeller(@RequestBody SellerRequest sellerRequest){
        SellerResponse sellerResponse = sellerService.addSeller(sellerRequest);
        return  new ResponseEntity<>(sellerResponse, HttpStatus.CREATED);
    }
}
