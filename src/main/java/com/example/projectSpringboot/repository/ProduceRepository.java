package com.example.projectSpringboot.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.projectSpringboot.model.Produce;

public interface ProduceRepository extends JpaRepository<Produce,Long>{
      @Query(value = "SELECT cost.date, cost.quantity, cost.total, produce.total_amount FROM user INNER JOIN cost ON cost.user_id = user.user_id INNER JOIN produce ON produce.user_id = user.user_id where cost.date = produce.date;", nativeQuery = true)
     List<Map<String, Object>> getCostandProduced(); 



     @Query(value = "SELECT cost.date, cost.quantity, cost.total, produce.total_amount FROM user INNER JOIN cost ON cost.user_id = user.user_id INNER "+
     "JOIN produce ON produce.user_id = user.user_id where cost.date = produce.date  and cost.date BETWEEN ?1 and ?2 ;", nativeQuery = true)
     List<Map<String, Object>> getCostandProducedByDate(LocalDate starDate , LocalDate enDate); 
}
