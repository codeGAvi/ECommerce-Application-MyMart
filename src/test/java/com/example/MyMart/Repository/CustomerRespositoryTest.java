package com.example.MyMart.Repository;

import com.example.MyMart.ENUM.Gender;
import com.example.MyMart.Entity.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class CustomerRespositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    void shouldSucceedWhen_validCustomerIsSaved(){
        // Arrange
        Customer customer = Customer.builder()
                .name("test_user")
                .email("test.com")
                .age(60)
                .gender(Gender.MALE)
                .mob_no("0000000000")
                .build();
        // Act
       Customer savedCustomer =  customerRepository.save(customer);     // this will not be Save in original DB ,.... will be saved in RAM

        // Assert
        Assertions.assertEquals("test_user",savedCustomer.getName());
        Assertions.assertNotEquals(0,savedCustomer.getId());
        Assertions.assertNotNull(savedCustomer.getCreatedAt());
        // we can write more tests....
    }
}
