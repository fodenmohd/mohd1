package com.example.projectSpringboot.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import lombok.Data;

@Data
@Entity
@Table
public class Produce {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long produce_Id;
    private Long product_Id;
    private int quantity;
    private Long Total_Amount;
      @Column(unique = true)
    private LocalDate date;
    private Long userId;


    @ManyToOne
    private Product product;
    
}
