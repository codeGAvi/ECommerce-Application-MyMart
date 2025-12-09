package com.example.MyMart.Transformer;

import com.example.MyMart.DTO.Request.ProductRequest;
import com.example.MyMart.DTO.Response.ProductResponse;
import com.example.MyMart.Entity.Product;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;

@UtilityClass
public class ProductTransformer {

    public Product productRequestToProduct(ProductRequest productRequest){
        return Product.builder()
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .category(productRequest.getCategory())
                .reviews(new ArrayList<>())
                .orders(new ArrayList<>())
                .build();
    }

    public ProductResponse ProductToProductResponse(Product product){
        return ProductResponse.builder()
                .name(product.getName())
                .price(product.getPrice())
                .category(product.getCategory())
                .seller(SellerTransformer.sellerToSellerResponse(product.getSeller()))

                // used in get reviews
                //2. .category(product.getCategory())
                //3..seller(SellerTransformer.sellerToSellerResponse(product.getSeller()))

                .build();
    }
}
