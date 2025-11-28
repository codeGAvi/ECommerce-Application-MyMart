package com.example.MyMart.DTO.Request;

import com.example.MyMart.ENUM.Gender;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest {
    private String name;
    private int age;
    private Gender gender;

    @Column(length = 10)
    private long mobNo;

    private String Email;
}
