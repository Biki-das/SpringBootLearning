package com.example.FakeCommereceApp.controllers;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.FakeCommereceApp.dtos.CreateOrderRequestDTO;
import com.example.FakeCommereceApp.schema.Order;
import com.example.FakeCommereceApp.services.OrderService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/orders")

public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public Order createOrder(@RequestBody CreateOrderRequestDTO request) {
        return orderService.createOrder(request);
    }

}
