package com.example.FakeCommereceApp.dtos;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@AllArgsConstructor
@Getter
@Setter
public class CreateProductRequestDTO {
    private String title;

    private String description;

    private String image;

    private BigDecimal price;

    private String category;

    private String rating;
}
