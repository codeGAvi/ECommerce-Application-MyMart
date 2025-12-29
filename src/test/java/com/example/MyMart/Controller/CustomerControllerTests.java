//package com.example.MyMart.Controller;
//
//import com.example.MyMart.DTO.Request.CustomerRequest;
//import com.example.MyMart.DTO.Response.CustomerResponse;
//import com.example.MyMart.ENUM.Gender;
//import com.example.MyMart.Service.CustomerService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.Date;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(CustomerController.class)
//public class CustomerControllerTests {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private CustomerService customerService;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Test
//    public void returnCustomerDetails_AfterAddedSuccessfully() throws Exception {
//
//        // Arrange : Request
//        CustomerRequest customerRequest = CustomerRequest.builder()
//                .name("test_controller_name")
//                .age(25)
//                .Email("test_controller.com")
//                .gender(Gender.MALE)
//                .mob_no("1111111111")
//                .build();
//
//        // Response
//        CustomerResponse customerResponse = CustomerResponse.builder()
//                .name("test_controller_name")
//                .createdAt(new Date())
//                .Email("test_controller.com")
//                .build();
//
//        // Mocking service
//        when(customerService.addCustomer(Mockito.any(CustomerRequest.class)))
//                .thenReturn(customerResponse);
//
//        // Act + Assert using MockMvc
//        mockMvc.perform(post("/customer/add")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(customerRequest)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name").value("test_controller_name"))
//                .andExpect(jsonPath("$.email").value("test_controller.com"))
//                .andExpect(jsonPath("$.createdAt").exists());
//    }
//}
