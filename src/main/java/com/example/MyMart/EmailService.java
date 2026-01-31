package com.example.MyMart;


import com.example.MyMart.Entity.OrderEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;

    @Async
    public void sendEmail(OrderEntity savedOrder){
        try {
            Thread.sleep(10000);  // test delay
            SimpleMailMessage message = new SimpleMailMessage();
            String description = "Hi," + " " + savedOrder.getCustomer().getName() + " your order " +
                    "has been placed successfully";
            message.setTo(savedOrder.getCustomer().getEmail());
            message.setSubject("Order Placed");
            message.setText(description);

            mailSender.send(message);
        } catch (Exception e) {
            System.out.println("async mail failed" + e.getMessage());
        }
    }
}
