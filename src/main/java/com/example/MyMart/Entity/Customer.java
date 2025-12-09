package com.example.MyMart.Entity;

import com.example.MyMart.ENUM.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
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
    private String mob_no;

    @CreationTimestamp
    Date CreatedAt;


    @OneToMany
    @JsonIgnore
    List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    List<OrderEntity> orders = new ArrayList<>();


}
