package com.example.FakeCommereceApp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.FakeCommereceApp.dtos.CreateProductRequestDTO;
import com.example.FakeCommereceApp.repositories.ProductRepository;
import com.example.FakeCommereceApp.schema.Product;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
    
    public Product createProduct(CreateProductRequestDTO requestDTO) {
        Product newProduct = Product.builder()
                        .title(requestDTO.getTitle())
                        .description(requestDTO.getDescription())
                        .price(requestDTO.getPrice())
                        .category(requestDTO.getCategory())
                        .image(requestDTO.getImage())
                        .rating(requestDTO.getRating())
                        .build();
        return productRepository.save(newProduct);  // this will save the new product to the database and return the saved entity (with generated ID)
    }

    public Product deleteProduct(Long id) {
        Product productToDelete = getProductById(id);
        productRepository.delete(productToDelete);
        return productToDelete; 
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public List<String> getUniqueCategories() {
        return productRepository.findUniqueCategories();
    }
}
