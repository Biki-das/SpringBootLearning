package com.example.FakeCommereceApp.controllers;

import java.util.List;

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

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/products")


public class ProductController {
        private final ProductService productService; 
        
        @GetMapping()
        public List<GetProductResponseDTO> getAllProducts() {
                return productService.getAllProducts();
        }

        @GetMapping("/{id}")
        public GetProductResponseDTO getProductById(@PathVariable("id") Long id) {
                return productService.getProductResponseById(id);
        }

        @GetMapping("/details/{id}")
        public GetProductWithDetailsResponse getProductWithDetailsById(@PathVariable Long id) {
                return productService.getProductWithDetailsById(id);
        }

        @PostMapping
        public Product createProduct(@RequestBody CreateProductRequestDTO requestDTO) {
                return productService.createProduct(requestDTO);
        }

        @DeleteMapping("/{id}")
        public Product deleteProduct(@PathVariable Long id) {
                return productService.deleteProduct(id);        
        }

        @GetMapping("/search")
        public List<Product> getProductsByCategory(@RequestParam("categoryName") String categoryName) {
                return productService.getProductsByCategory(categoryName);  
        }

        @GetMapping("/categories")
        public List<String> getUniqueCategories() {
                return productService.getUniqueCategories(); 
        }
        
}
