package com.example.MyMart.Transformer;

import com.example.MyMart.DTO.Request.ReviewRequest;
import com.example.MyMart.DTO.Response.ReviewResponse;
import com.example.MyMart.Entity.Review;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ReviewTransformer {

    // DTO to entity
    public Review reviewRequestToReview(ReviewRequest reviewRequest){
        return Review.builder()
                .comment(reviewRequest.getComment())
                .rating(reviewRequest.getRating())
                .build();
    }

    // entity to DTO
    public ReviewResponse reviewToReviewResponse(Review review){
        return ReviewResponse.builder()
                .comment(review.getComment())
                .rating(review.getRating())
                .customer(CustomerTransformer.customerToCustomerResponse(review.getCustomer()))
                .product(ProductTransformer.ProductToProductResponse(review.getProduct()))
                .build();
    }
}
