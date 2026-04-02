package com.example.MyMart.Service;

import com.example.MyMart.DTO.Request.SellerRequest;
import com.example.MyMart.ENUM.Roles;
import com.example.MyMart.Entity.Customer;
import com.example.MyMart.Entity.Seller;
import com.example.MyMart.Repository.CustomerRepository;
import com.example.MyMart.Repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class MakeSellerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    SellerRepository sellerRepository;

    public String makeSeller(SellerRequest request){

        // 🔐 logged-in user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        Customer customer = customerRepository.findByUsername(username);

        // check already seller
        Seller existing = sellerRepository.findByCustomer(customer);
        if(existing != null){
            throw new RuntimeException("Already a seller");
        }

        // ✅ create seller
        Seller seller = new Seller();
        seller.setCustomer(customer);
        seller.setName(request.getName());
        seller.setCity(request.getCity());
        seller.setEmail(request.getEmail());

        // 🔥 GST from request
        seller.setGst_no(request.getGst_no());

        sellerRepository.save(seller);

        // update role
        if(!customer.getRoles().contains(Roles.SELLER)){
            customer.getRoles().add(Roles.SELLER);
        }
        customerRepository.save(customer);

        return "now you are a seller" + request.getName();
    }
}
