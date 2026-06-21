package com.example.FakeCommereceApp.dtos;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderRequestDTO {
    private List<OrderItem> items;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrderItem {
        private Long productId;
        private Integer quantity;
    }
}
