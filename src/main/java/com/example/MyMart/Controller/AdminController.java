//package com.example.MyMart.Controller;
//
//import com.example.MyMart.Service.CustomerService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/admin")
//public class AdminController {
//    @Autowired
//    CustomerService customerService;
//
//
//    @PutMapping("/promote/seller/{c_id}")
//    @PreAuthorize("hasRole('ADMIN')")
//    public String promoteToSeller(@PathVariable int c_id){
//        customerService.promoteToSeller(c_id);
//        return "Customer having " + c_id + " is promoted to Seller";
//    }
//
//    @PutMapping("/promote/admin/{c_id}")
//    @PreAuthorize("hasRole('ADMIN')")
//    public String promoteToAdmin(@PathVariable int c_id){
//         customerService.promoteToAdmin(c_id);
//         return "Now he " + c_id + " promoted to Admin";
//    }
//}
