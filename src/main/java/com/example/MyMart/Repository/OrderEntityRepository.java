package com.example.MyMart.Repository;

import com.example.MyMart.Entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderEntityRepository extends JpaRepository<OrderEntity,Integer> {
}
