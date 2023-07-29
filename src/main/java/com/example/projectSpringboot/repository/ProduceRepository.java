package com.example.projectSpringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projectSpringboot.model.Produce;

public interface ProduceRepository extends JpaRepository<Produce,Long>{
    
}
