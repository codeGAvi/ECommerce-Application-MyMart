package com.example.MyMart.Service;

import com.example.MyMart.Entity.Customer;
import com.example.MyMart.Exception.CustomerNotFoundException;
import com.example.MyMart.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;


    public Customer addCustomer(Customer customer){
        // just ".save" function ko call kiya and iska implementation hibernate likh dega for insert data in customer
        Customer savedCustomer = customerRepository.save(customer);
        return savedCustomer;
    }

    public Customer getCustomerById(int id){
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if(optionalCustomer.isEmpty()){
            throw new CustomerNotFoundException("Invalid id");
        }
        Customer customer = optionalCustomer.get();
        return customer;
    }
}
