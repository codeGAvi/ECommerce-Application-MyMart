package com.example.MyMart.DTO.Request;

import com.example.MyMart.ENUM.Gender;
import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerRequest {
    private String name;
    private int age;
    private Gender gender;

    @Column(length = 10)
    private String mob_no;

    private String Email;
}
