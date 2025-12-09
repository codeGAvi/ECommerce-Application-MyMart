package com.example.MyMart.Entity;

import com.example.MyMart.ENUM.Status;
import jakarta.persistence.*;
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
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    @Column
    private  long order_Value;

    @ManyToMany
    @JoinTable
    List<Product> products = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "customer_id")
    Customer customer;
}
