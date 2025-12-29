package com.example.MyMart.Controller;

import com.example.MyMart.DTO.Request.ListOfOrder;
import com.example.MyMart.DTO.Response.OrderEntityResponse;
import com.example.MyMart.Service.OrderEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderEntityController {

    private final OrderEntityService orderEntityService;

    @PostMapping
    public ResponseEntity placeOrder(@RequestParam("cus_id")int cus_id, @RequestBody ListOfOrder listOfOrder){
        try {
            OrderEntityResponse orderEntityResponse = orderEntityService.placeOrder(cus_id, listOfOrder);
            return new ResponseEntity<>(orderEntityResponse,HttpStatus.CREATED);
        }
        catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.ACCEPTED);
        }
    }
}
