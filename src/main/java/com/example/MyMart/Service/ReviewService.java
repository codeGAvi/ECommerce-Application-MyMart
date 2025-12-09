package com.example.MyMart.Service;

import com.example.MyMart.DTO.Request.ReviewRequest;
import com.example.MyMart.DTO.Response.ReviewResponse;
import com.example.MyMart.Entity.Customer;
import com.example.MyMart.Entity.Product;
import com.example.MyMart.Entity.Review;
import com.example.MyMart.Exception.CustomerNotFoundException;
import com.example.MyMart.Exception.ProductNotFoundException;
import com.example.MyMart.Repository.CustomerRepository;
import com.example.MyMart.Repository.ProductRepository;
import com.example.MyMart.Repository.ReviewRepository;
import com.example.MyMart.Transformer.ReviewTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    public ReviewResponse addReview(int customer_id, int product_id, ReviewRequest reviewRequest){
        Optional<Customer>  optionalCustomer = customerRepository.findById(customer_id);
        if(optionalCustomer.isEmpty()){
            throw new CustomerNotFoundException("Invalid customer_id: " + customer_id);
        }

        Optional<Product> optionalProduct = productRepository.findById(product_id);
        if(optionalProduct.isEmpty()){
            throw new ProductNotFoundException("Invalid Product_id " + product_id);
        }

        Customer customer = optionalCustomer.get();
        Product  product = optionalProduct.get();

        // Review DTO to Review Entity
        Review review = ReviewTransformer.reviewRequestToReview(reviewRequest);


        // relationship --> foreign key;
        review.setCustomer(customer);
        review.setProduct(product);

       // save review in entity   AND CASCADEDED over customer and product inside Review ENTITY
       Review savedReview =  reviewRepository.save(review);  // product +  customer + review

       // review Entity to Response DTO
        return ReviewTransformer.reviewToReviewResponse(savedReview); // save only last review
    }


    public ReviewResponse getReviewById(int p_id){
        Optional<Product>optionalProduct = productRepository.findById(p_id);
        if(optionalProduct.isEmpty()){
            throw new CustomerNotFoundException("Invalid product_Id");
        }
        Product product = optionalProduct.get();

        List<Review> reviews = product.getReviews();

        ReviewResponse reviewResponse = new ReviewResponse();
        for(Review reviewss : reviews){
            reviewResponse = ReviewTransformer.reviewToReviewResponse(reviewss);
        }
        return reviewResponse;

    }

    public List<ReviewResponse>getReviewByWord(String word){
         List<Review> reviews = reviewRepository.findByCommentContaining(word);
          if(reviews.isEmpty()){
              throw new ProductNotFoundException("not found with this word : " + word);
          }
         // convert this into review response
       List<ReviewResponse> reviewResponse = new ArrayList<>();
        for(Review review : reviews){
            reviewResponse.add(ReviewTransformer.reviewToReviewResponse(review));
        }
        return reviewResponse;
    }
}
