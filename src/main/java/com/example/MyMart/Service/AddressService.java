package com.example.MyMart.Service;

import com.example.MyMart.DTO.Request.AddressRequest;
import com.example.MyMart.DTO.Response.AddressResponse;
import com.example.MyMart.Entity.Address;
import com.example.MyMart.Entity.Customer;
import com.example.MyMart.Exception.CustomerNotFoundException;
import com.example.MyMart.Repository.AddressRepository;
import com.example.MyMart.Repository.CustomerRepository;
import com.example.MyMart.Transformer.AddressTransformer;
import com.example.MyMart.Transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    CustomerRepository customerRepository;

    public AddressResponse addAddress(int customer_id, AddressRequest addressRequest){
        Optional<Customer> customerOptional = customerRepository.findById(customer_id);
        if(customerOptional.isEmpty()){
            throw new CustomerNotFoundException("Invalid customer_id" + customer_id);
        }
        Customer customer = customerOptional.get();

        // Request DTO to Entity
        Address address = AddressTransformer.addressRequestToAddress(addressRequest);

        // set relationship --> set foreign key
        address.setCustomer(customer);

        // save the entity
      Address savedAddress = addressRepository.save(address);

      // Entity to AddressResponse
        AddressResponse addressResponse = AddressTransformer.addressToAddressResponse(savedAddress);
        return  addressResponse;
    }
}
