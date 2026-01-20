package com.example.MyMart.Entity;

import com.example.MyMart.ENUM.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private double price;

    @Enumerated(value = EnumType.STRING)
    private Category category;


    // make bidirectional relation with seller
    @ManyToOne
    @JoinColumn(name = "seller_id")
    Seller seller;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    List<Review> reviews = new ArrayList<>();


    @ManyToMany(mappedBy = "products")
    List<OrderEntity> orders = new ArrayList<>();

}
