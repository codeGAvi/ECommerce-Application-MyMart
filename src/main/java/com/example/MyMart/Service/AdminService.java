package com.example.MyMart.Service;

import com.example.MyMart.DTO.Request.AdminRegistrationRequest;
import com.example.MyMart.DTO.Request.AdminRoleRequest;
import com.example.MyMart.ENUM.Roles;
import com.example.MyMart.Entity.Customer;
import com.example.MyMart.Entity.Seller;
import com.example.MyMart.Repository.CustomerRepository;
import com.example.MyMart.Repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Pull secret key from application.properties
    @Value("${admin.secret.key}")
    private String adminSecretKey;

    // Register Admin
    public String registerAdmin(AdminRegistrationRequest request) {

        // 1. Validate secret key
        if (!request.getSecretKey().equals(adminSecretKey)) {
            throw new RuntimeException("Invalid secret key");
        }

        // 2. Check username already exists
        Customer existing = customerRepository.findByUsername(request.getUsername());
        if (existing != null) {
            throw new RuntimeException("Username already taken");
        }

        // 3. Create admin customer
        Customer admin = Customer.builder()
                .name(request.getName())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .age(request.getAge())
                .build();
        if(admin.getRoles()==null){
            admin.setRoles(new java.util.HashSet<>());
        }
        admin.getRoles().add(Roles.ADMIN);
        customerRepository.save(admin);

        return "Admin registered successfully: " + request.getUsername();
    }



    // ─── View All Customers ───────────────────────────────────────────
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }


    // ─── View All Sellers ─────────────────────────────────────────────
    public List<Seller> getAllSellers() {
        return sellerRepository.findAll();
    }


    // ─── Assign Role ──────────────────────────────────────────────────
    public String assignRole(AdminRoleRequest request) {

        Customer customer = customerRepository.findByUsername(request.getUsername());

        if (customer == null) {
            throw new RuntimeException("Customer not found: " + request.getUsername());
        }

        if (customer.getRoles().contains(request.getRole())) {
            throw new RuntimeException("Customer already has role: " + request.getRole());
        }

        customer.getRoles().add(request.getRole());
        customerRepository.save(customer);

        return "Role " + request.getRole() + " assigned to " + request.getUsername();
    }


    // Revoke role
    public String revokeRole(AdminRoleRequest request) {

        Customer customer = customerRepository.findByUsername(request.getUsername());

        if (customer == null) {
            throw new RuntimeException("Customer not found: " + request.getUsername());
        }

        // prevent removing last role.. always there should be at least one role assigned to a customer
        if (customer.getRoles().size() == 1 && customer.getRoles().contains(request.getRole())) {
            throw new RuntimeException("Cannot remove the only role from a customer");
        }

        // prevent removing ADMIN role from self
        if (request.getRole() == Roles.ADMIN && customer.getRoles().contains(Roles.ADMIN)) {
            throw new RuntimeException("Cannot revoke ADMIN role");
        }

        customer.getRoles().remove(request.getRole());
        customerRepository.save(customer);

        return "Role " + request.getRole() + " revoked from " + request.getUsername();
    }
}