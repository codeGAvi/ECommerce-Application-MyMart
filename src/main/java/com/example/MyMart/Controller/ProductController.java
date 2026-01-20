package com.example.MyMart.Controller;

import com.example.MyMart.DTO.Request.ProductRequest;
import com.example.MyMart.DTO.Response.ProductResponse;
import com.example.MyMart.ENUM.Category;
import com.example.MyMart.Exception.SellerNotFoundException;
import com.example.MyMart.Service.ProductService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    // get all  / pagination
    @GetMapping("/AllProducts")
    public ResponseEntity<Page<ProductResponse>> getAllProducts(@RequestParam(defaultValue ="0") int page,
                                               @RequestParam(defaultValue = "5") int size){
        Pageable pageable = PageRequest.of(page,size);
        Page<ProductResponse> response = productService.getAllProducts(pageable);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    // now adding pagination with sorting
    @GetMapping("/SortedProducts")
    public ResponseEntity<Page<ProductResponse>> getsortedProducts(@RequestParam(defaultValue = "0") int page,
                                                                   @RequestParam(defaultValue = "4") int size,

                                                                   // by default sorting on price basis , we can sort by also name, price , category
                                                                   @RequestParam(defaultValue = "price") String sortBy,

                                                                   @RequestParam(defaultValue = "asc") String direction){

        // now make object of sort
        Sort sort = direction.equalsIgnoreCase("asc")? Sort.by(sortBy).ascending():Sort.by(sortBy).descending();

        // now make object of pageable
        Pageable pageable = PageRequest.of(page,size,sort);

        // now make service call
        Page<ProductResponse> response = productService.getAllProducts(pageable); // called same service method of previous one
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    // filtering
    @GetMapping("/FilteredProduct")
    public ResponseEntity<Page<ProductResponse>>filterProduct(@RequestParam(required  = false) Category category,
                                                              @RequestParam(defaultValue = "0")int page,
                                                              @RequestParam(defaultValue = "5")int size,
                                                              @RequestParam(defaultValue = "name")String sortBy,
                                                              @RequestParam(defaultValue = "asc")String direction){

        Sort sort = direction.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page,size,sort);

        Page<ProductResponse> responses;
        if(category != null){
            // then call function with category
            responses = productService.getFilteredProduct(category,pageable);
        }
        else{
            // if category null means category not required the  simply call get all products
           responses = productService.getAllProducts(pageable);
        }
        return new ResponseEntity<>(responses,HttpStatus.OK);
    }

    // prince range filter ...// full-fledged filtration
    @GetMapping("/priceRange")
    public ResponseEntity<Page<ProductResponse>>priceRangeFilter(@RequestParam(required = false)Category category,
                                                                 @RequestParam(required = false)Double minPrice,
                                                                 @RequestParam(required = false) Double maxPrice,
                                                                 @RequestParam(defaultValue = "0")int page,
                                                                 @RequestParam(defaultValue = "5")int size,
                                                                 @RequestParam(defaultValue = "name")String sortBy,
                                                                 @RequestParam(defaultValue = "asc")String direction){
        Sort sort = direction.equalsIgnoreCase("asc")
                ?Sort.by(sortBy).ascending()
                :Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page,size,sort);

        Page<ProductResponse>responses;
        if(category != null && minPrice != null && maxPrice != null){
            responses = productService.priceRangeFilter(category,minPrice,maxPrice,pageable);
        }
        else if(category != null){
            responses = productService.getFilteredProduct(category,pageable);
        }
        else if (minPrice != null && maxPrice != null) {
            responses = productService.getProductsByPriceRange(minPrice, maxPrice, pageable);
        }
        else {
            responses = productService.getAllProducts(pageable);
        }

        return  new ResponseEntity<>(responses,HttpStatus.OK);
    }
}
