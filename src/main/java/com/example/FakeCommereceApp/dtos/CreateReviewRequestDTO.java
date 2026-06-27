package com.example.FakeCommereceApp.dtos;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateReviewRequestDTO {
    private Long orderId;
    private Long productId;
    private String comment;
    private BigDecimal rating;
}
