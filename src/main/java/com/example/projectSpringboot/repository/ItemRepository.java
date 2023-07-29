package com.example.projectSpringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projectSpringboot.model.Item;

public interface ItemRepository extends JpaRepository<Item,Long>{
    
}
