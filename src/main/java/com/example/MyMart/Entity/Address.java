package com.example.MyMart.Entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@Table(name ="address_details")  // in database column name for this table will be ""address_details" / instead of address
@Builder
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private int houseNo;

    @Column
    private String city;

    @Column
    private String state;

    @Column(unique = true)  // to male this column with unique values
    private long pincode;

    // making entity- relationship ........  here unidirect  rional relation with 1 to 1
    @OneToOne
    @JoinColumn(name = "customer_id")     // foreign-key
    Customer customer;
}
