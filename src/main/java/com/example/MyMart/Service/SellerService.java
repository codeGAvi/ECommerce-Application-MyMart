package com.example.MyMart.Service;

import com.example.MyMart.DTO.Request.SellerRequest;
import com.example.MyMart.DTO.Response.SellerResponse;
import com.example.MyMart.Entity.Seller;
import com.example.MyMart.Repository.SellerRepository;
import com.example.MyMart.Transformer.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService {

    @Autowired
    SellerRepository sellerRepository;

    public SellerResponse addSeller(SellerRequest sellerRequest) {
        // DTO Request to Entity
        Seller seller = SellerTransformer.sellerRequestToSeller(sellerRequest);
        // save the seller entity in DB
        Seller savedSeller = sellerRepository.save(seller);
        // saved seller(Enitity) to Seller Response
       return SellerTransformer.sellerToSellerResponse(seller);
    }
}
