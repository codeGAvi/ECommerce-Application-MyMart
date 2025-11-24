package com.example.MyMart.Controller;

import com.example.MyMart.Entity.Review;
import com.example.MyMart.Exception.CustomerNotFoundException;
import com.example.MyMart.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customer")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @GetMapping("reviews")
    public ResponseEntity getReviewById(@RequestParam("id") int id){
        try{
           Review reviews =  reviewService.getReviewById(id);
           return new ResponseEntity<>(reviews,HttpStatus.FOUND);
        }
        catch (CustomerNotFoundException e){   // used customerException...
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
