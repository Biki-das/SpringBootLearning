package com.example.FakeCommereceApp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.FakeCommereceApp.schema.OrderProducts;

public interface OrderProductRepository extends JpaRepository<OrderProducts, Long> {

    List<OrderProducts> findByOrderId(Long orderId);
}
