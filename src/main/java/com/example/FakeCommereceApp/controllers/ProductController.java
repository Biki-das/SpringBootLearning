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

import com.example.FakeCommereceApp.dtos.CreateProductRequestDTO;
import com.example.FakeCommereceApp.dtos.GetProductResponseDTO;
import com.example.FakeCommereceApp.dtos.GetProductWithDetailsResponse;
import com.example.FakeCommereceApp.schema.Product;
import com.example.FakeCommereceApp.services.ProductService;
import com.example.FakeCommereceApp.utils.ApiResponse;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/products")


public class ProductController {
        private final ProductService productService;

        @GetMapping()
        public ApiResponse<List<GetProductResponseDTO>> getAllProducts() {
                return ApiResponse.success(productService.getAllProducts());
        }

        @GetMapping("/{id}")
        public ApiResponse<GetProductResponseDTO> getProductById(@PathVariable("id") Long id) {
                return ApiResponse.success(productService.getProductResponseById(id));
        }

        @GetMapping("/details/{id}")
        public ApiResponse<GetProductWithDetailsResponse> getProductWithDetailsById(@PathVariable Long id) {
                return ApiResponse.success(productService.getProductWithDetailsById(id));
        }

        @PostMapping
        public ResponseEntity<ApiResponse<Product>> createProduct(@RequestBody CreateProductRequestDTO requestDTO) {
                return ResponseEntity.status(HttpStatus.CREATED)
                                .body(ApiResponse.success("Product created", productService.createProduct(requestDTO)));
        }

        @DeleteMapping("/{id}")
        public ApiResponse<Product> deleteProduct(@PathVariable Long id) {
                return ApiResponse.success("Product deleted", productService.deleteProduct(id));
        }

        @GetMapping("/search")
        public ApiResponse<List<Product>> getProductsByCategory(@RequestParam("categoryName") String categoryName) {
                return ApiResponse.success(productService.getProductsByCategory(categoryName));
        }

        @GetMapping("/categories")
        public ApiResponse<List<String>> getUniqueCategories() {
                return ApiResponse.success(productService.getUniqueCategories());
        }

}
