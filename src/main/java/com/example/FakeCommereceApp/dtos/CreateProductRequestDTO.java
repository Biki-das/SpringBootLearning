package com.example.FakeCommereceApp.dtos;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequestDTO {
    private String title;

    private String description;

    private String image;

    private BigDecimal price;

    private BigDecimal rating;

    private Long categoryId;
}
