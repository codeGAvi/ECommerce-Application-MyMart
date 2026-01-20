package com.example.MyMart.Repository;

import com.example.MyMart.DTO.Request.ListOfOrder;
import com.example.MyMart.DTO.Response.ProductResponse;
import com.example.MyMart.ENUM.Category;
import com.example.MyMart.Entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Range;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {

    // filter product based on category
    List<Product> findByCategory(Category category);

    // filter by category and sorting(pagination)
    Page<Product> findByCategory(Category category, Pageable pageable);

    // price range filter
    Page<Product> findByCategoryAndPriceBetween(Category category, double minPrice, double maxPrice,Pageable pageable);

    // without category
    Page<Product> findByPriceBetween(double minPrice, double maxPrice, Pageable pageable);
}
