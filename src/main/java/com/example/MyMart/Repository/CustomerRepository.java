package com.example.MyMart.Repository;

import com.example.MyMart.ENUM.Gender;
import com.example.MyMart.Entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    // filter based on Gender
    List<Customer> findByGender(Gender gender);

    // filter based on age
    List<Customer>findByAgeLessThanEqual(int age);

}
