package com.example.MyMart.Service;

import com.example.MyMart.DTO.Request.SellerRequest;
import com.example.MyMart.DTO.Response.SellerResponse;
import com.example.MyMart.Entity.Seller;
import com.example.MyMart.Repository.SellerRepository;
import com.example.MyMart.Transformer.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SellerService {

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public SellerResponse addSeller(SellerRequest sellerRequest) {
        Seller seller = SellerTransformer.sellerRequestToSeller(sellerRequest);
        Seller savedSeller = sellerRepository.save(seller);
       return SellerTransformer.sellerToSellerResponse(savedSeller);
    }
}
