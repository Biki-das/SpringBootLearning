package com.example.FakeCommereceApp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.FakeCommereceApp.schema.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(String category); 

    @Query(nativeQuery = true, value ="SELECT DISTINCT c.name FROM products p INNER JOIN categories c ON p.category_id = c.id")
    List<String> findUniqueCategories();

    @Query(nativeQuery = true, value = "SELECT p.* FROM products p INNER JOIN categories c ON p.category_id = c.id WHERE c.name = :categoryName")
    List<Product> getProductsByCategory(@Param("categoryName") String categoryName);

    @Query("SELECT p FROM Product p JOIN FETCH p.category WHERE p.id = :id")
    List<Product> findProductWithDetailsById(@Param("id") Long id);

}
