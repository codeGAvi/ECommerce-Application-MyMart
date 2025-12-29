package com.example.MyMart.Service;

import com.example.MyMart.DTO.Request.ListOfOrder;
import com.example.MyMart.DTO.Request.orderItemsRequest;
import com.example.MyMart.DTO.Response.OrderEntityResponse;
import com.example.MyMart.ENUM.Status;
import com.example.MyMart.Entity.Customer;
import com.example.MyMart.Entity.OrderEntity;
import com.example.MyMart.Entity.Product;
import com.example.MyMart.Exception.CustomerNotFoundException;
import com.example.MyMart.Exception.ProductNotFoundException;
import com.example.MyMart.Repository.CustomerRepository;
import com.example.MyMart.Repository.OrderEntityRepository;
import com.example.MyMart.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderEntityService {
    private final OrderEntityRepository orderEntityRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final JavaMailSender javaMailSender;

    public OrderEntityResponse placeOrder(int cus_id, ListOfOrder listOfOrder){
        // check customer & product availability
        Optional<Customer> optionalCustomer = customerRepository.findById(cus_id);
        if(optionalCustomer.isEmpty()){
            throw new CustomerNotFoundException("invalid customer_id" + cus_id);
        }
//      Optional<Product> optionalProduct =  productRepository.findById(p_id);
//      if(optionalProduct.isEmpty()){
//          throw new ProductNotFoundException("this product is not available" + p_id);
//      }

//      Product products = optionalProduct.get();

      Customer customers = optionalCustomer.get();

      OrderEntity orderEntity = new OrderEntity();

        List<orderItemsRequest>orderRequest = listOfOrder.getListofItem();
        int total_order_value =0;
        for(orderItemsRequest orderItemsRequest:orderRequest) {
            int p_id = orderItemsRequest.getProduct_Id();
            int qty = orderItemsRequest.getQuantity();

            Optional<Product> optionalProduct =  productRepository.findById(p_id);
            if(optionalProduct.isEmpty()){
                throw new ProductNotFoundException("this product is not available" + p_id);

           }
            Product products = optionalProduct.get();
            total_order_value += products.getPrice()*qty;
            orderEntity.getProducts().add(products);

        }
      orderEntity.setStatus(Status.PLACED);
      orderEntity.setOrder_Value(total_order_value);


      orderEntity.setCustomer(customers);

      // now save orderEntity in DB
        OrderEntity savedOrder = orderEntityRepository.save(orderEntity);

        // send email for order place
        sendEmail(savedOrder);

      return OrderEntityResponse.builder()
              .id(savedOrder.getId())
              .status(savedOrder.getStatus())
              .Order_value(savedOrder.getOrder_Value())
              .build();

    }

    public void sendEmail(OrderEntity savedOrder){
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            String description = "Hi," + " " + savedOrder.getCustomer().getName() + " your order " +
                    "has been placed successfully";
            message.setTo(savedOrder.getCustomer().getEmail());
            message.setSubject("Order Placed");
            message.setText(description);

            javaMailSender.send(message);
        } catch (Exception e) {
            System.out.println(" Order placed successfully but Email sending failed : " + e.getMessage());
        }
    }

}