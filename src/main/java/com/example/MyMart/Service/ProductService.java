package com.example.MyMart.Service;

import com.example.MyMart.DTO.Request.ProductRequest;
import com.example.MyMart.DTO.Response.ProductResponse;
import com.example.MyMart.ENUM.Category;
import com.example.MyMart.Entity.Product;
import com.example.MyMart.Entity.Seller;
import com.example.MyMart.Exception.SellerNotFoundException;
import com.example.MyMart.Repository.ProductRepository;
import com.example.MyMart.Repository.SellerRepository;
import com.example.MyMart.Transformer.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    SellerRepository sellerRepository;

    public ProductResponse addProduct(int seller_id,ProductRequest productRequest){

        // get Seller with given seller_id
        Optional<Seller> sellerOptional = sellerRepository.findById(seller_id);
        if(sellerOptional.isEmpty()){
           throw  new SellerNotFoundException("Invalid Seller id: ");
        }
        // DTO to entity
        Product product = ProductTransformer.productRequestToProduct(productRequest);

        // else set this seller object into product entity
        Seller seller = sellerOptional.get();

        // setting Bidirectional Relations b/w seller and products
        seller.getProducts().add(product);  // add new product in the list of products
        product.setSeller(seller); // add seller in product

        // now save product and seller in database ,,,, saved products using cascade over product in seller entity
       Seller savedSeller =  sellerRepository.save(seller);  // seller + product
        int size = savedSeller.getProducts().size();
        Product savedProduct = savedSeller.getProducts().get(size-1); // get last product from product List
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
