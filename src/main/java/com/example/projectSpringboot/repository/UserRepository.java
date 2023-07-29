package com.example.projectSpringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projectSpringboot.model.User;

public interface UserRepository extends JpaRepository<User,Long>{
    
}
