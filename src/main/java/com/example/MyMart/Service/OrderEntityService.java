package com.example.MyMart.Service;

import com.example.MyMart.DTO.Request.ListOfOrder;
import com.example.MyMart.DTO.Request.orderItemsRequest;
import com.example.MyMart.DTO.Response.OrderEntityResponse;
import com.example.MyMart.ENUM.Status;
import com.example.MyMart.EmailService;
import com.example.MyMart.Entity.Customer;
import com.example.MyMart.Entity.OrderEntity;
import com.example.MyMart.Entity.Product;
import com.example.MyMart.Exception.CustomerNotFoundException;
import com.example.MyMart.Exception.OrderNotFoundException;
import com.example.MyMart.Exception.ProductNotFoundException;
import com.example.MyMart.Repository.CustomerRepository;
import com.example.MyMart.Repository.OrderEntityRepository;
import com.example.MyMart.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderEntityService {
    private final OrderEntityRepository orderEntityRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final EmailService emailService;

    @Transactional
    public OrderEntityResponse placeOrder(int cus_id, ListOfOrder listOfOrder){
        // check customer & product availability
        Optional<Customer> optionalCustomer = customerRepository.findById(cus_id);
        if(optionalCustomer.isEmpty()){
            throw new CustomerNotFoundException("invalid customer_id" + cus_id);
        }


      Customer customers = optionalCustomer.get();

      OrderEntity orderEntity = new OrderEntity();

        List<orderItemsRequest>orderRequest = listOfOrder.getListofItem();
        int total_order_value =0;
        for(orderItemsRequest orderItemsRequest:orderRequest) {
            int p_id = orderItemsRequest.getProduct_Id();
            int qty = orderItemsRequest.getQuantity();

            Optional<Product> optionalProduct =  productRepository.findById(p_id);
            if(optionalProduct.isEmpty()){
                throw new ProductNotFoundException("Product not found:" + p_id);
           }
            Product products = optionalProduct.get();

            // checking stock
            if(products.getStock()<qty){
                throw new RuntimeException("Insufficiant stock for: " + products.getName());
            }
            // now reduce stock
            products.setStock(products.getStock()-qty);
            productRepository.save(products);  // saved in db
            total_order_value += products.getPrice()*qty;

            orderEntity.getProducts().add(products);

        }
      orderEntity.setStatus(Status.PLACED);
      orderEntity.setOrder_Value(total_order_value);


      orderEntity.setCustomer(customers);

      // now save orderEntity in DB
        OrderEntity savedOrder = orderEntityRepository.save(orderEntity);

   emailService.sendEmail(savedOrder); // call EmailService

      return OrderEntityResponse.builder()
              .id(savedOrder.getId())
              .status(savedOrder.getStatus())
              .Order_value(savedOrder.getOrder_Value())
              .build();

    }



    // for change/update the status of order
    public OrderEntityResponse UpdateOrderStatus(int orderId, Status newStatus) {
       Optional<OrderEntity> orderEntityOptional = orderEntityRepository.findById(orderId);
       if(orderEntityOptional.isEmpty()){
           throw new OrderNotFoundException("order with id: " + orderId + " not found");
       }
       OrderEntity orderEntity = orderEntityOptional.get();
       orderEntity.setStatus(newStatus);
       orderEntityRepository.save(orderEntity);
       return OrderEntityResponse.builder()
               .id(orderEntity.getId())
               .status(orderEntity.getStatus())
               .Order_value(orderEntity.getOrder_Value())
               .build();
    }
}