package com.example.MyMart.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Review {

    @Id
    @Column
    int id;

    @Column
    private String comment;

    @Min(value = 1)
    @Max(value = 5)
    @Column
    private  int rating;


    @ManyToOne //(M---1) Opposite from in customer entitiy
    @JoinColumn(name = "customer_id")
    Customer customer;


    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnore
    Product product;

}
