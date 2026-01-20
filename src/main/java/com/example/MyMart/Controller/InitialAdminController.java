//package com.example.MyMart.Controller;
//
//import com.example.MyMart.Service.CustomerService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/init")
//public class InitialAdminController {
//
//    @Autowired
//    private CustomerService customerService;
//
//    // do run this End point only one time
//    @PostMapping("/admin/{username}")
//    public String createinitialAdmin(@PathVariable String username){
//        return customerService.createinitialAdmin(username);
//    }
//}
