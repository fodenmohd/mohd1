package com.example.projectSpringboot.model;



import java.time.LocalDate;
// import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
      @Column(unique = true)
    private LocalDate date;
    private Long product_Id;
    private Long totalValue;
    private Long userId;


    @OneToOne
    private Item item;

   @ManyToOne
    private Product product;

  
}
