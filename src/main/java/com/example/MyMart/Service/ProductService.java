package com.example.MyMart.Service;

import com.example.MyMart.DTO.Request.ProductRequest;
import com.example.MyMart.DTO.Response.ProductResponse;
import com.example.MyMart.ENUM.Category;
import com.example.MyMart.Entity.Customer;
import com.example.MyMart.Entity.Product;
import com.example.MyMart.Entity.Seller;
import com.example.MyMart.Exception.SellerNotFoundException;
import com.example.MyMart.Repository.CustomerRepository;
import com.example.MyMart.Repository.ProductRepository;
import com.example.MyMart.Repository.SellerRepository;
import com.example.MyMart.Transformer.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    CustomerRepository customerRepository;

    public ProductResponse addProduct(String username, ProductRequest productRequest){

        // get logged-in customer
        Customer customer = customerRepository.findByUsername(username);
        if(customer == null){
            throw new RuntimeException("Customer not found");
        }

        //  get seller from customer
        Seller seller = sellerRepository.findByCustomer(customer);
        if(seller == null){
            throw new SellerNotFoundException("Seller not found for this user");
        }

        //  DTO → entity
        Product product = ProductTransformer.productRequestToProduct(productRequest);

        // set relation
        product.setSeller(seller);
        seller.getProducts().add(product);

        //  save
        Seller savedSeller = sellerRepository.save(seller);

        int size = savedSeller.getProducts().size();
        Product savedProduct = savedSeller.getProducts().get(size - 1);

        return ProductTransformer.ProductToProductResponse(savedProduct);
    }

    public List<ProductResponse> getProductByCategory(Category categoroy){
        List<Product> productByCategory = productRepository.findByCategory(categoroy);

        // now convert this in product responses
        List<ProductResponse> productResponses = new ArrayList<>();
        for(Product products : productByCategory){
            productResponses.add(ProductTransformer.ProductToProductResponse(products));
        }
        return productResponses;
    }

    // here i'm using pagination over get all products
    public Page<ProductResponse> getAllProducts(Pageable pageable) {
        Page<Product> productsPage =   productRepository.findAll(pageable);
        return productsPage.map(ProductTransformer::ProductToProductResponse);
    }

    //  filter product based on category
    public Page<ProductResponse> getFilteredProduct(Category category, Pageable pageable) {
      return  productRepository.findByCategory(category,pageable).map(ProductTransformer::ProductToProductResponse);
    }

    public Page<ProductResponse> priceRangeFilter(Category category, double minPrice, double maxPrice,Pageable pageable) {
        return productRepository.findByCategoryAndPriceBetween(category,minPrice,maxPrice,pageable)
                .map(ProductTransformer::ProductToProductResponse);
    }

    public Page<ProductResponse> getProductsByPriceRange(double minPrice, double maxPrice, Pageable pageable) {
        return productRepository.findByPriceBetween(minPrice,maxPrice,pageable)
                .map(ProductTransformer::ProductToProductResponse);
    }


}
