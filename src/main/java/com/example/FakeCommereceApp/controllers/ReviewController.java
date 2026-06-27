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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.FakeCommereceApp.dtos.CreateReviewRequestDTO;
import com.example.FakeCommereceApp.schema.Review;
import com.example.FakeCommereceApp.services.ReviewService;
import com.example.FakeCommereceApp.utils.ApiResponse;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/reviews")

public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping
    public ApiResponse<List<Review>> getAllReviews() {
        return ApiResponse.success(reviewService.getAllReviews());
    }

    @GetMapping("/{id}")
    public ApiResponse<Review> getReviewById(@PathVariable("id") Long id) {
        return ApiResponse.success(reviewService.getReviewById(id));
    }

    @GetMapping("/search")
    public ApiResponse<List<Review>> getReviewsByProduct(@RequestParam("productId") Long productId) {
        return ApiResponse.success(reviewService.getReviewsByProduct(productId));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Review>> createReview(@RequestBody CreateReviewRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Review created", reviewService.createReview(requestDTO)));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Review> deleteReview(@PathVariable Long id) {
        return ApiResponse.success("Review deleted", reviewService.deleteReview(id));
    }
}
