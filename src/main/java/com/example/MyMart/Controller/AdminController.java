package com.example.MyMart.Controller;

import com.example.MyMart.DTO.Request.AdminRegistrationRequest;
import com.example.MyMart.DTO.Request.AdminRoleRequest;
import com.example.MyMart.Entity.Customer;
import com.example.MyMart.Entity.Seller;
import com.example.MyMart.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;


    // Register Admin (public — protected by secret key)
    @PostMapping("/register")
    public String registerAdmin(@RequestBody AdminRegistrationRequest request) {
        return adminService.registerAdmin(request);
    }


    // View All Customers
    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return adminService.getAllCustomers();
    }


    // View All Sellers
    @GetMapping("/sellers")
    public List<Seller> getAllSellers() {
        return adminService.getAllSellers();
    }


    // Assign Role
    @PostMapping("/assign-role")
    public String assignRole(@RequestBody AdminRoleRequest request) {
        return adminService.assignRole(request);
    }


    //Revoke Role
    @PostMapping("/revoke-role")
    public String revokeRole(@RequestBody AdminRoleRequest request) {
        return adminService.revokeRole(request);
    }
}