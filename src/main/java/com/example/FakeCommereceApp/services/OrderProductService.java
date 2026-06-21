package com.example.FakeCommereceApp.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.FakeCommereceApp.dtos.CreateOrderRequestDTO;
import com.example.FakeCommereceApp.repositories.OrderProductRepository;
import com.example.FakeCommereceApp.repositories.ProductRepository;
import com.example.FakeCommereceApp.schema.Order;
import com.example.FakeCommereceApp.schema.OrderProducts;
import com.example.FakeCommereceApp.schema.Product;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderProductService {
    private final OrderProductRepository orderProductRepository;
    private final ProductRepository productRepository;

    public List<OrderProducts> createOrderProducts(Order order, List<CreateOrderRequestDTO.OrderItem> items) {
        List<OrderProducts> rows = items.stream().map(item -> {
            Product product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found: " + item.getProductId()));

            return OrderProducts.builder()
                    .order(order)
                    .product(product)
                    .quantity(item.getQuantity())
                    .build();
        }).collect(Collectors.toList());

        return orderProductRepository.saveAll(rows);
    }
}
