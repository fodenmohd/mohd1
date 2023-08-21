package com.example.projectSpringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

// import com.example.projectSpringboot.controller.Admin;
import com.example.projectSpringboot.model.Item;

public interface ItemRepository extends JpaRepository<Item,Long>{

    // Admin getAdminByEmail(Object email);
    
}
