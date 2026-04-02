package com.example.MyMart.Controller;


import com.example.MyMart.DTO.Request.SellerRequest;
import com.example.MyMart.Service.MakeSellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MakeSellerController {

    @Autowired
    MakeSellerService sellerService;

    @PostMapping("/make-seller")
    public String makeSeller(@RequestBody SellerRequest request){
        return sellerService.makeSeller(request);
    }
}