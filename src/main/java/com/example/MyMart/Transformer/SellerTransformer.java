package com.example.MyMart.Transformer;

import com.example.MyMart.DTO.Request.SellerRequest;
import com.example.MyMart.DTO.Response.SellerResponse;
import com.example.MyMart.Entity.Seller;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;

@UtilityClass
public class SellerTransformer {
    public Seller sellerRequestToSeller(SellerRequest sellerRequest){
        return Seller.builder()
                .name(sellerRequest.getName())
                .email(sellerRequest.getEmail())
                .gst_no(sellerRequest.getGst_no())
                .city(sellerRequest.getCity())
                .products(new ArrayList<>())  // to avoid null pointer exception
                .build();
    }
    // seller entity to Response seller
    public SellerResponse sellerToSellerResponse(Seller seller){
        return SellerResponse.builder()
                .name(seller.getName())
                .email(seller.getEmail())
                .build();
    }
}
