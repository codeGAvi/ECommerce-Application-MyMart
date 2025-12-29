package com.example.MyMart.Repository;

import com.example.MyMart.DTO.Request.ListOfOrder;
import com.example.MyMart.ENUM.Category;
import com.example.MyMart.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {

    // filter product based on category
    List<Product> findByCategory(Category category);
}
