package com.example.FakeCommereceApp.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.FakeCommereceApp.dtos.CreateOrderRequestDTO;
import com.example.FakeCommereceApp.repositories.OrderProductRepository;
import com.example.FakeCommereceApp.repositories.OrderRepository;
import com.example.FakeCommereceApp.repositories.ProductRepository;
import com.example.FakeCommereceApp.schema.Order;
import com.example.FakeCommereceApp.schema.OrderProducts;
import com.example.FakeCommereceApp.schema.OrderStatus;
import com.example.FakeCommereceApp.schema.Product;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final ProductRepository productRepository;

    @Transactional
    public Order createOrder(CreateOrderRequestDTO request) {
        // 1. create the parent order
        Order order = orderRepository.save(
                Order.builder().status(OrderStatus.PENDING).build());

        // 2. one order_products row per item
        for (CreateOrderRequestDTO.OrderItem item : request.getItems()) {
            Product product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found: " + item.getProductId()));

            orderProductRepository.save(
                    OrderProducts.builder()
                            .order(order)
                            .product(product)
                            .quantity(item.getQuantity())
                            .build());
        }
        return order;
    }
}
