package com.example.projectSpringboot.model;



import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table
public class Cost{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long cost_Id;
    private Long item_Id;
    private int quantity;
    private int total;
    private Date date;
}
