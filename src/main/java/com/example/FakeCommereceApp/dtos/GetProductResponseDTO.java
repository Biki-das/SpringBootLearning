package com.example.FakeCommereceApp.dtos;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class GetProductResponseDTO {
    private Long id;
    private String title;
    private String description;
    private String image;
    private BigDecimal rating;
    private BigDecimal price;
}
