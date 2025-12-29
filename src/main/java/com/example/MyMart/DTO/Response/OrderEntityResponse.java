package com.example.MyMart.DTO.Response;

import com.example.MyMart.ENUM.Status;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrderEntityResponse {
  private int id;
  private Status status;
  private int Order_value;
}
