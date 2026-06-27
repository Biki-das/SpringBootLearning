package com.example.FakeCommereceApp.controllers;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.FakeCommereceApp.dtos.CreateOrderRequestDTO;
import com.example.FakeCommereceApp.dtos.GetOrderResponseDTO;
import com.example.FakeCommereceApp.services.OrderService;
import com.example.FakeCommereceApp.utils.ApiResponse;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/orders")

public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public ApiResponse<List<GetOrderResponseDTO>> getAllOrders() {
        return ApiResponse.success(orderService.getAllOrders());
    }

    @GetMapping("/{id}")
    public ApiResponse<GetOrderResponseDTO> getOrderById(@PathVariable("id") Long id) {
        return ApiResponse.success(orderService.getOrderResponseById(id));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<GetOrderResponseDTO>> createOrder(@RequestBody CreateOrderRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Order created", orderService.createOrder(request)));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<GetOrderResponseDTO> deleteOrder(@PathVariable Long id) {
        return ApiResponse.success("Order deleted", orderService.deleteOrder(id));
    }

}
