package com.example.FakeCommereceApp.services;

import com.example.FakeCommereceApp.schema.Category;


import com.example.FakeCommereceApp.dtos.CreateCategoryRequestDTO;
import com.example.FakeCommereceApp.exceptions.ResourceNotFoundException;
import com.example.FakeCommereceApp.repositories.CategoryRepository;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

   public Category  createCategory(CreateCategoryRequestDTO requestDTO) {
        Category newCategory = Category.builder()
                        .name(requestDTO.getName())
                        .build();
        return categoryRepository.save(newCategory);  

   }

   public List<Category> getAllCategories() {
        return categoryRepository.findAll();
   }

   public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
   }

   public void deleteCategory(Long id) {
        Category categoryToDelete = getCategoryById(id);
        categoryRepository.delete(categoryToDelete);
   }
}
