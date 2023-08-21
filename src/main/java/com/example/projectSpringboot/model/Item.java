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
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long item_Id;
    @Column(unique = true)
    private String item_Name;
    private int item_price;
    private String item_Description;
    
     @ManyToOne
    private Product product;
}
