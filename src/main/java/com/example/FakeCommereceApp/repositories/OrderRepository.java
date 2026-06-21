package com.example.FakeCommereceApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.FakeCommereceApp.schema.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    
}
