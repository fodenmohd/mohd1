package com.example.projectSpringboot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projectSpringboot.model.login;

public interface LoginRepository extends JpaRepository<login,Long> {
    boolean findByUsername(String username);
    Optional<login> getByUsername(String username);


 
}