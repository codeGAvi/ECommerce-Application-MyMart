package com.example.MyMart.Service;

import com.example.MyMart.DTO.Request.CustomerRequest;
import com.example.MyMart.DTO.Response.CustomerResponse;
import com.example.MyMart.ENUM.Gender;
import com.example.MyMart.Entity.Customer;
import com.example.MyMart.Exception.CustomerNotFoundException;
import com.example.MyMart.Repository.CustomerRepository;
import com.example.MyMart.Transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public CustomerResponse addCustomer(CustomerRequest customerRequest){

        // Request_DTO to Entity
        Customer customer=   CustomerTransformer.customerRequestToCustomer(customerRequest);
        customer.setRoles("USER");  // Can have multiple roles
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        Customer savedCustomer = customerRepository.save(customer);

        //Entity to DTO_Response
        CustomerResponse response = CustomerTransformer.customerToCustomerResponse(savedCustomer);
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

//    public void promoteToSeller(int c_id) {
//        Customer customer = customerRepository.findById(c_id).orElseThrow(()-> new CustomerNotFoundException("invalid customer id."));
//        // now set the role
//        customer.setRoles(Set.of(Roles.USER,Roles.SELLER));
//        customerRepository.save(customer);
  //  }

//    public void promoteToAdmin(int c_id) {
//        Customer customer = customerRepository.findById(c_id).orElseThrow(()-> new CustomerNotFoundException("invalid customer id"));
//        customer.setRoles(Set.of(Roles.USER,Roles.SELLER,Roles.ADMIN));
//        customerRepository.save(customer);
//    }
//
//    public String createinitialAdmin(String username) {
//       Customer customer =  customerRepository.findByUsername(username);
//       if(customer==null){
//           throw  new CustomerNotFoundException("invalid username");
//       }
//       if(customer.getRoles().contains(Roles.ADMIN)){
//           return "User is already Admin";
//       }
//       // else gave role of "Admin" to this user
//        customer.getRoles().add(Roles.ADMIN);
//       customerRepository.save(customer);
//       return "Initial Admin created successfully";
//    }
}
