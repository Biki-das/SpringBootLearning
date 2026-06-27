package com.example.FakeCommereceApp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.FakeCommereceApp.schema.OrderProducts;

public interface OrderProductRepository extends JpaRepository<OrderProducts, Long> {

    @Query("SELECT op FROM OrderProducts op JOIN FETCH op.product WHERE op.order.id = :orderId")
    List<OrderProducts> findByOrderIdWithProduct(@Param("orderId") Long orderId);

    @Query("SELECT op FROM OrderProducts op JOIN FETCH op.product WHERE op.order.id IN :orderIds")
    List<OrderProducts> findByOrderIdInWithProduct(@Param("orderIds") List<Long> orderIds);
}
