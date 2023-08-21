package com.example.projectSpringboot.model;

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
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long product_Id;
     @Column(unique = true)
    private String product_name;
    private int product_price;
    private String description;
    private int avg;
    private int isUsed;
    

    @ManyToOne
    private Produce produce;
}
