package com.example.projectSpringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projectSpringboot.model.Cost;


public interface CostRepository extends JpaRepository<Cost,Long>{

    
}
