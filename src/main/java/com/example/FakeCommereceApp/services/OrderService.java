package com.example.FakeCommereceApp.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.FakeCommereceApp.dtos.CreateOrderRequestDTO;
import com.example.FakeCommereceApp.dtos.GetOrderResponseDTO;
import com.example.FakeCommereceApp.exceptions.BadRequestException;
import com.example.FakeCommereceApp.exceptions.ResourceNotFoundException;
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

    public List<GetOrderResponseDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        if (orders.isEmpty()) {
            return new ArrayList<>();
        }

        List<Long> orderIds = orders.stream().map(Order::getId).toList();

        Map<Long, List<OrderProducts>> linesByOrderId = orderProductRepository
                .findByOrderIdInWithProduct(orderIds).stream()
                .collect(Collectors.groupingBy(op -> op.getOrder().getId()));

        List<GetOrderResponseDTO> responseDTOs = new ArrayList<>();
        for (Order order : orders) {
            responseDTOs.add(mapToResponseDTO(
                    order, linesByOrderId.getOrDefault(order.getId(), new ArrayList<>())));
        }
        return responseDTOs;
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
    }

    public GetOrderResponseDTO getOrderResponseById(Long id) {
        Order order = getOrderById(id);
        return mapToResponseDTO(order, orderProductRepository.findByOrderIdWithProduct(id));
    }

    @Transactional
    public GetOrderResponseDTO createOrder(CreateOrderRequestDTO request) {
        if (request.getItems() == null || request.getItems().isEmpty()) {
            throw new BadRequestException("items is required and must not be empty");
        }

        List<Long> productIds = new ArrayList<>();
        for (CreateOrderRequestDTO.OrderItem item : request.getItems()) {
            if (item.getProductId() == null) {
                throw new BadRequestException("productId is required for every item");
            }
            if (item.getQuantity() == null || item.getQuantity() < 1) {
                throw new BadRequestException("quantity must be at least 1 for every item");
            }
            productIds.add(item.getProductId());
        }

        Map<Long, Product> productsById = productRepository.findAllById(productIds).stream()
                .collect(Collectors.toMap(Product::getId, Function.identity()));

        Order order = orderRepository.save(
                Order.builder().status(OrderStatus.PENDING).build());

        List<OrderProducts> lines = new ArrayList<>();
        for (CreateOrderRequestDTO.OrderItem item : request.getItems()) {
            Product product = productsById.get(item.getProductId());
            if (product == null) {
                throw new ResourceNotFoundException("Product not found with id: " + item.getProductId());
            }
            lines.add(OrderProducts.builder()
                    .order(order)
                    .product(product)
                    .quantity(item.getQuantity())
                    .build());
        }

        orderProductRepository.saveAll(lines);

        return mapToResponseDTO(order, lines);
    }

    public GetOrderResponseDTO deleteOrder(Long id) {
        Order orderToDelete = getOrderById(id);
        GetOrderResponseDTO response = mapToResponseDTO(
                orderToDelete, orderProductRepository.findByOrderIdWithProduct(id));
        orderRepository.delete(orderToDelete);
        return response;
    }

    private GetOrderResponseDTO mapToResponseDTO(Order order, List<OrderProducts> orderProducts) {
        List<GetOrderResponseDTO.OrderItemResponse> items = new ArrayList<>();
        BigDecimal totalAmount = BigDecimal.ZERO;

        for (OrderProducts orderProduct : orderProducts) {
            Product product = orderProduct.getProduct();
            BigDecimal lineTotal = product.getPrice()
                    .multiply(BigDecimal.valueOf(orderProduct.getQuantity()));
            totalAmount = totalAmount.add(lineTotal);

            items.add(GetOrderResponseDTO.OrderItemResponse.builder()
                    .productId(product.getId())
                    .title(product.getTitle())
                    .price(product.getPrice())
                    .quantity(orderProduct.getQuantity())
                    .lineTotal(lineTotal)
                    .build());
        }

        return GetOrderResponseDTO.builder()
                .id(order.getId())
                .status(order.getStatus())
                .createdAt(order.getCreatedAt())
                .items(items)
                .totalAmount(totalAmount)
                .build();
    }
}
