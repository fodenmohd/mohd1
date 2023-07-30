package com.example.projectSpringboot.repository;


import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projectSpringboot.model.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query(value = "SELECT cost.date, cost.quantity, cost.total, produce.date, produce.quantity, produce.total_amount FROM user INNER JOIN cost ON cost.user_id = user.user_id INNER JOIN produce ON produce.user_id = user.user_id;", nativeQuery = true)
     List<Map<String, Object>> getCostandProduced();
}
