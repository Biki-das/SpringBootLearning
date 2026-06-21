package com.example.FakeCommereceApp.services;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.FakeCommereceApp.dtos.CreateProductRequestDTO;
import com.example.FakeCommereceApp.exceptions.BadRequestException;
import com.example.FakeCommereceApp.exceptions.ResourceNotFoundException;
import com.example.FakeCommereceApp.dtos.GetProductResponseDTO;
import com.example.FakeCommereceApp.dtos.GetProductWithDetailsResponse;
import com.example.FakeCommereceApp.repositories.ProductRepository;
import com.example.FakeCommereceApp.schema.Category;
import com.example.FakeCommereceApp.schema.Product;


@Service
@AllArgsConstructor

public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public List<GetProductResponseDTO> getAllProducts() {
     List<Product> products = productRepository.findAll();
     List<GetProductResponseDTO> responseDTOs = new ArrayList<>();
       for (Product product: products) {
       responseDTOs.add(mapToResponseDTO(product));
       }
       return responseDTOs;
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }

    public GetProductResponseDTO getProductResponseById(Long id) {
        return mapToResponseDTO(getProductById(id));
    }

    private GetProductResponseDTO mapToResponseDTO(Product product) {
        return GetProductResponseDTO.builder()
                .id(product.getId())
                .title(product.getTitle())
                .description(product.getDescription())
                .price(product.getPrice())
                .image(product.getImage())
                .rating(product.getRating())
                .build();
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
    
    public Product createProduct(CreateProductRequestDTO requestDTO) {
        if (requestDTO.getCategoryId() == null) {
            throw new BadRequestException("categoryId is required");
        }
        Category category = categoryService.getCategoryById(requestDTO.getCategoryId());
        Product newProduct = Product.builder()
                        .title(requestDTO.getTitle())
                        .description(requestDTO.getDescription())
                        .price(requestDTO.getPrice())
                        .category(category)
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

    public GetProductWithDetailsResponse getProductWithDetailsById(Long id) {
        return productRepository.findProductWithDetailsById(id)
                .stream()
                .findFirst()
                .map(product -> GetProductWithDetailsResponse.builder()
                        .id(product.getId())
                        .title(product.getTitle())
                        .description(product.getDescription())
                        .price(product.getPrice())
                        .image(product.getImage())
                        .rating(product.getRating())
                        .categoryName(product.getCategory().getName())
                        .build())
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }
}
