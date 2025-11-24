package com.example.MyMart.Entity;

import com.example.MyMart.ENUM.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column(nullable = false)
   private int age;

    @Column(unique = true)
    private String email;

    @Enumerated(value = EnumType.STRING)
    private Gender gender ;

    @Column(length = 10)
    private long mob_No;

    @CreationTimestamp
    Date CreatedAt;

//    // Bidirectional relation with address-entity
//    @OneToOne(mappedBy = "customer")
//    Address address;

//    //Bidirectional relation with review-entity
//    @OneToMany(mappedBy = "customer")   //(1---M)
//    List<Review> reviews = new ArrayList<>();  // bcz one customer have many reviews

    @OneToMany  //---> for uni-directional 1-M
    @JoinColumn(name = "customer_id")
    @JsonIgnore
    List<Review> reviews = new ArrayList<>();  // bcz one customer have many reviews


}
