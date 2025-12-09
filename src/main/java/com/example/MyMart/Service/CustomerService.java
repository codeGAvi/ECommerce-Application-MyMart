package com.example.MyMart.Service;

import com.example.MyMart.DTO.Request.CustomerRequest;
import com.example.MyMart.DTO.Response.CustomerResponse;
import com.example.MyMart.ENUM.Gender;
import com.example.MyMart.Entity.Customer;
import com.example.MyMart.Exception.CustomerNotFoundException;
import com.example.MyMart.Repository.CustomerRepository;
import com.example.MyMart.Transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public CustomerResponse addCustomer(CustomerRequest customerRequest){

        // Request_DTO to Entity
        Customer customer=   CustomerTransformer.customerRequestToCustomer(customerRequest);
        // save entitiy in database
        Customer savedCustomer = customerRepository.save(customer);
        //Entity to DTO_Response
        CustomerResponse response = CustomerTransformer.customerToCustomerResponse(customer);
        return  response;
    }

    public CustomerResponse getCustomerById(int id){
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if(optionalCustomer.isEmpty()){
            throw new CustomerNotFoundException("Invalid id");
        }
        Customer customer = optionalCustomer.get();
        return CustomerTransformer.customerToCustomerResponse(customer);

    }

    public List<CustomerResponse>getCustomerByGender(Gender gender){

        // Normal Approach to filter
//        List<Customer> customers = customerRepository.findAll();
//        List<Customer> customerByGender = new ArrayList<>();
//        for(Customer customer:customers){
//            if(customer.getGender() == gender){
//                customerByGender.add(customer);
//            }
//        }
//
//        // make customer response
//        List<CustomerResponse>  customerResponses = new ArrayList<>();
//        for(Customer customersByGender : customerByGender){
//            // now convert this filtered customer in customerResponse
//            customerResponses.add(CustomerTransformer.customerToCustomerResponse(customersByGender));
//        }
//
//        return customerResponses;


        // Optimised Approach to filter
        List<Customer> customerByGender = customerRepository.findByGender(gender);
        // now convert this in response
        List<CustomerResponse> customerResponses = new ArrayList<>();
        for(Customer customers: customerByGender){
            customerResponses.add(CustomerTransformer.customerToCustomerResponse(customers));
        }
        return customerResponses;
    }

    // filter customer by age
    public List<CustomerResponse> getCustomerByAge(int age){
          List<Customer> customers = customerRepository.findByAgeLessThanEqual(age);

        // now convert this into customerResponse
        List<CustomerResponse> customerResponses = new ArrayList<>();
        for(Customer customerByAge: customers){
            customerResponses.add(CustomerTransformer.customerToCustomerResponse(customerByAge));
        }
        return  customerResponses;
    }

}
