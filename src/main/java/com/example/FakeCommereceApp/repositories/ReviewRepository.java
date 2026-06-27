package com.example.FakeCommereceApp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.FakeCommereceApp.schema.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByProductId(Long productId);

    List<Review> findByOrderId(Long orderId);
}
