package com.example.MyMart.Repository;

import com.example.MyMart.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Integer> {

    List<Review> findByCommentContaining(String word);
}
