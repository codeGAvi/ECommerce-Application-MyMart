package com.example.MyMart.Service;

import com.example.MyMart.DTO.Response.CustomerResponse;
import com.example.MyMart.ENUM.Gender;
import com.example.MyMart.Entity.Customer;
import com.example.MyMart.Repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)


@ExtendWith(MockitoExtension.class)
public class CustomerServiceTests {

    @Mock
   private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;


    // writing test for get customer by id
    @Test
    public void shouldReturnSavedCustomer_whenIdExits(){

       // Arrange.....make dummy customer Object &
        Customer customer = Customer.builder()
                .id(1)
                .name("test")
                .gender(Gender.MALE)
                .age(90)
                .email("test_email.com")
                .mob_no("9999999999")
                .CreatedAt(new Date(2017,04,12))
                .build();

        // and mock the function (getCustomerById) bcz , this belongs to another layer(repository)
        Mockito.when(customerRepository.findById(Mockito.any())).thenReturn(Optional.of(customer));

        // Act ..... actually testing the function getCustomerById
        CustomerResponse customerResponse = customerService.getCustomerById(1);

        // Assert
        Assertions.assertNotNull(customerResponse);
        Assertions.assertEquals("test",customerResponse.getName());
        Assertions.assertEquals("test_email.com",customerResponse.getEmail());
        Assertions.assertNotNull(customerResponse.getCreatedAt());

    }


    // Test for get CustomerByGender
    @Test
    public void shouldReturnSavedCustomer_whenGenderExists(){
      // Arrange.... make dummy customer Objects
        //Gender gender = Gender.FEMALE;
        Customer customer1 = Customer.builder()
                .name("Female1")
                .gender(Gender.FEMALE)
                .age(29)
                .email("female1@gmail.com")
                .mob_no("8888888888")
                .CreatedAt(new Date(2013,01,21)) //this comes from DB and to avoid this doing hard code
                .build();

        // make another cusotmer object
        Customer customer2 = Customer.builder()
                .name("Female2")
                .gender(Gender.FEMALE)
                .age(29)
                .email("female2@gmail.com")
                .mob_no("8888888888")
                .CreatedAt(new Date(2013,01,21)) //this comes from DB and to avoid this doing hard code
                .build();

        // add both customer object into single list
        List<Customer>customerList = Arrays.asList(customer1,customer2);

        // now mock the function getCustomerByGender
        Mockito.when(customerRepository.findByGender(Mockito.any())).thenReturn((customerList));

        // Act
       List<CustomerResponse>customerLists = customerService.getCustomerByGender(Gender.FEMALE);

       // Assert
        Assertions.assertEquals("Female1",customerLists.get(0).getName());
        Assertions.assertEquals("female1@gmail.com",customerLists.get(0).getEmail());
        Assertions.assertNotNull(customerLists.get(0).getCreatedAt());
    }
}