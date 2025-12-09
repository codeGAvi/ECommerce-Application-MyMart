package com.example.MyMart.Controller;

import com.example.MyMart.DTO.Request.ReviewRequest;
import com.example.MyMart.DTO.Response.ReviewResponse;
import com.example.MyMart.Entity.Review;
import com.example.MyMart.Exception.CustomerNotFoundException;
import com.example.MyMart.Service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/review")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    Logger logger = LoggerFactory.getLogger(AddressController.class);


    @PostMapping
    public ResponseEntity addReview(@RequestParam ("c_id")int customer_id,
                                    @RequestParam ("p_id") int product_id,
                                    @RequestBody ReviewRequest reviewRequest){
         try {
             logger.info("Add review API Hitted: "  + reviewRequest);
             ReviewResponse reviewResponse = reviewService.addReview(customer_id, product_id, reviewRequest);
             return new ResponseEntity<>(reviewResponse,HttpStatus.CREATED);
         }
         catch (Exception e){
             return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
         }
    }

    @GetMapping
    public ResponseEntity getReviewById(@RequestParam("p_id") int p_id){
        try{
            ReviewResponse reviewResponse =  reviewService.getReviewById(p_id);
            return new ResponseEntity<>(reviewResponse,HttpStatus.FOUND);
        }
        catch (Exception e){   // used customerException...
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // get review which has word "Good"
    @GetMapping("/word/{word}")
    public ResponseEntity getReviewByWord(@PathVariable String word){
        try{
            List<ReviewResponse> reviewList = reviewService.getReviewByWord(word);
            return  new ResponseEntity<>(reviewList,HttpStatus.FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FOUND);
        }
    }
}
