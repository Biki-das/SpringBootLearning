package com.example.FakeCommereceApp.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.FakeCommereceApp.dtos.CreateReviewRequestDTO;
import com.example.FakeCommereceApp.exceptions.BadRequestException;
import com.example.FakeCommereceApp.exceptions.ResourceNotFoundException;
import com.example.FakeCommereceApp.repositories.OrderRepository;
import com.example.FakeCommereceApp.repositories.ReviewRepository;
import com.example.FakeCommereceApp.schema.Order;
import com.example.FakeCommereceApp.schema.Product;
import com.example.FakeCommereceApp.schema.Review;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final OrderRepository orderRepository;
    private final ProductService productService;

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Review getReviewById(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found with id: " + id));
    }

    public List<Review> getReviewsByProduct(Long productId) {
        return reviewRepository.findByProductId(productId);
    }

    public Review createReview(CreateReviewRequestDTO requestDTO) {
        if (requestDTO.getOrderId() == null) {
            throw new BadRequestException("orderId is required");
        }
        if (requestDTO.getProductId() == null) {
            throw new BadRequestException("productId is required");
        }
        if (requestDTO.getRating() == null) {
            throw new BadRequestException("rating is required");
        }
        if (requestDTO.getRating().compareTo(BigDecimal.ONE) < 0
                || requestDTO.getRating().compareTo(BigDecimal.valueOf(5)) > 0) {
            throw new BadRequestException("rating must be between 1 and 5");
        }

        Order order = orderRepository.findById(requestDTO.getOrderId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Order not found with id: " + requestDTO.getOrderId()));
        Product product = productService.getProductById(requestDTO.getProductId());

        Review newReview = Review.builder()
                .order(order)
                .product(product)
                .comment(requestDTO.getComment())
                .rating(requestDTO.getRating())
                .build();
        return reviewRepository.save(newReview);
    }

    public Review deleteReview(Long id) {
        Review reviewToDelete = getReviewById(id);
        reviewRepository.delete(reviewToDelete);
        return reviewToDelete;
    }
}
