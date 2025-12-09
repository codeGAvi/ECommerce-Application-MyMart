package com.example.MyMart.Repository;

import com.example.MyMart.Entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Integer> {
}
