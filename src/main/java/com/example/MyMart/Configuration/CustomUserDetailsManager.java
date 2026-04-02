package com.example.MyMart.Configuration;

import com.example.MyMart.Entity.Customer;
import com.example.MyMart.Entity.Seller;
import com.example.MyMart.Exception.CustomerNotFoundException;
import com.example.MyMart.Repository.CustomerRepository;
import com.example.MyMart.Repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsManager implements UserDetailsService{

    @Autowired
    CustomerRepository customerRepository;

    // for customer
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByUsername(username);
        if(customer == null){
            throw  new CustomerNotFoundException("invalid username");
        }
        return new CustomUserDetails(customer);
    }

}
