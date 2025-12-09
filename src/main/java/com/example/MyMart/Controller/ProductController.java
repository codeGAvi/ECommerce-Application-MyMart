package com.example.MyMart.Controller;

import com.example.MyMart.DTO.Request.ProductRequest;
import com.example.MyMart.DTO.Response.ProductResponse;
import com.example.MyMart.ENUM.Category;
import com.example.MyMart.Exception.SellerNotFoundException;
import com.example.MyMart.Service.ProductService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController  {

    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity addProduct(@RequestParam("seller_id") int seller_id,@RequestBody ProductRequest productRequest){
        try {
            ProductResponse productResponse = productService.addProduct(seller_id,productRequest);
            return new ResponseEntity<>(productResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    // get product by category
    @GetMapping("/category/{category}")
    public ResponseEntity getProductByCategory(@PathVariable Category category){
        List<ProductResponse> responses = productService.getProductByCategory(category);
        return new ResponseEntity<>(responses, HttpStatus.FOUND);
    }
}
