//package com.example.MyMart.Service;
//
//import com.example.MyMart.Entity.Product;
//import com.example.MyMart.Exception.ProductNotFoundException;
//import com.example.MyMart.Repository.OrderRepository;
//import com.example.MyMart.Repository.ProductRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class OrderService {
//
//    @Autowired
//    OrderRepository orderRepository;
//
//    @Autowired
//    ProductRepository productRepository;
//
//    public OrderResponse addOrder(int cus_id, int p_id){
//        // skip customer check
//        // check product availability
//      Optional<Product> optionalProduct =  productRepository.findById(p_id);
//      if(optionalProduct.isEmpty()){
//          throw new ProductNotFoundException("this product is not availlable");
//      }
//
//      Product products = optionalProduct.get();
//
//      orderRepository.save(orders)
//    }
//}
