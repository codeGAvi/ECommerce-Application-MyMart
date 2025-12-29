package com.example.MyMart.DTO.Request;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListOfOrder {
    // for passing multiple products id to place multiple items in single order
 List<orderItemsRequest> listofItem = new ArrayList<>();
}
