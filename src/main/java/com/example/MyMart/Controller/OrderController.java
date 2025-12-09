//package com.example.MyMart.Controller;
//
//import com.example.MyMart.Service.OrderService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/v1/order")
//public class OrderController {
//
//    @Autowired
//    OrderService orderService;
//
//    @PostMapping
//    public ResponseEntity addOrder(@RequestParam("cus_id")int cus_id, @RequestParam("p_id") int p_id){
//        OrderResponse orderResponse = orderService.addOrder(cus_id, p_id);
//        return  new ResponseEntity<>(orderResponse, HttpStatus.ACCEPTED);
//    }
//}
