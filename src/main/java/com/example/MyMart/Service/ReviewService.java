package com.example.MyMart.Service;

import com.example.MyMart.Entity.Review;
import com.example.MyMart.Exception.CustomerNotFoundException;
import com.example.MyMart.Repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    public Review getReviewById(int id){
        Optional<Review>optionalReview = reviewRepository.findById(id);
        if(optionalReview.isEmpty()){
            throw new CustomerNotFoundException("Invalid customer_Id");
        }
        return optionalReview.get();
    }
}
