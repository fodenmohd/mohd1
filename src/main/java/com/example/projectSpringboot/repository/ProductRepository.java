package com.example.projectSpringboot.repository;


import java.util.Optional;


import java.util.List;
// import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projectSpringboot.model.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query(value = "SELECT * from product where is_used = 1",nativeQuery = true)
    List<Product> getCurrentProduct();
}
