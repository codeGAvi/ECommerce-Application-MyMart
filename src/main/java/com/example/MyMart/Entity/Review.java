package com.example.MyMart.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.hibernate.engine.internal.Cascade;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column
    private String comment;

    @Min(value = 1)
    @Max(value = 5)

    @Column
    private  int rating;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    Customer customer;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    @JsonIgnore
    Product product;

}
