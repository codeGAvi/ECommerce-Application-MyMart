package com.example.MyMart.Service;

import com.example.MyMart.DTO.Request.CustomerRequest;
import com.example.MyMart.DTO.Response.CustomerResponse;
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

    // make a function for customer Response and use in all API as per reqr....
    public CustomerResponse customerToCustomerResponse(Customer customer){
//        CustomerResponse customerResponse = new CustomerResponse();
//        customerResponse.setName(customer.getName());
//        customerResponse.setEmail(customer.getEmail());
//        customerResponse.setCreatedAt(customer.getCreatedAt());
//
//        return  customerResponse;

        // use builder
        return CustomerResponse.builder()
                .name(customer.getName())
                .Email(customer.getEmail())
                .createdAt(customer.getCreatedAt())
                .build();
    }

    // also make function for customerRequest
    public Customer customerRequestToCustomer(CustomerRequest customerRequest){
//        Customer customer = new Customer();
//        customer.setEmail(customerRequest.getEmail());
//        customer.setAge(customerRequest.getAge());
//        customer.setGender(customerRequest.getGender());
//        customer.setMob_No(customerRequest.getMobNo());
//        customer.setName(customerRequest.getName());
//        return  customer;

        // using Builder
        return Customer.builder()
                .name(customerRequest.getName())
                .age(customerRequest.getAge())
                .email(customerRequest.getEmail())
                .gender(customerRequest.getGender())
                .mob_No(customerRequest.getMobNo())
                .build();
    }

    public CustomerResponse addCustomer(CustomerRequest customerRequest){

        // Step-1 : Request DTO to Entitiy
        // use(call) customer request function
         Customer customer = customerRequestToCustomer(customerRequest);

        // now save this entity
        Customer savedCustomer = customerRepository.save(customer);

      // now convert savedEntitiy to Response DTO
        // directly use function of customer response
        CustomerResponse response = customerToCustomerResponse(customer);
        return  response;
    }

    public CustomerResponse getCustomerById(int id){
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if(optionalCustomer.isEmpty()){
            throw new CustomerNotFoundException("Invalid id");
        }
        Customer customer = optionalCustomer.get();

        // used response fucntion
        CustomerResponse customerResponse = customerToCustomerResponse(customer);
        return customerResponse;
    }
}
