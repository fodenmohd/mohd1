package com.example.projectSpringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projectSpringboot.model.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {
    
}
