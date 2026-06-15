package com.example.FakeCommereceApp.repositories;

import com.example.FakeCommereceApp.schema.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
