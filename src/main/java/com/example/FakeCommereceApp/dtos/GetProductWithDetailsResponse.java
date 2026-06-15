package com.example.FakeCommereceApp.dtos;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder

public class GetProductWithDetailsResponse extends GetProductResponseDTO {
    private String categoryName;
}
