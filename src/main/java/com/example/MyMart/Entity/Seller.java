package com.example.MyMart.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Seller {

    @Id
    private int id;

    @Column
    private String name;

    @Column
    private String city;

    @Column
    private String email;

    @Column(length = 10, unique = true)
    private String Gst_No;

    // make bidirectional relation with product
    @OneToMany(mappedBy = "seller")
    List<Product> products = new ArrayList<>();
}
