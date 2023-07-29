package com.example.projectSpringboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.projectSpringboot.exception.ResourceNotFoundException;
import com.example.projectSpringboot.model.Product;
import com.example.projectSpringboot.repository.ProductRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    
    private ProductRepository productRepository;


    // get all prodduct
    @GetMapping("/product")
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

     
    // post product
    @PostMapping("/product")
    public Product addProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }
    

    // update product
       @PutMapping("/product/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        Product updateProduct2 = productRepository.findById(id)
        .orElseThrow(() ->new ResourceNotFoundException("product not found"));
      
            updateProduct2.setProduct_name(product.getProduct_name());
            updateProduct2.setProduct_price(product.getProduct_price());
            updateProduct2.setDescription(product.getDescription());
           
            
           Product createproduct = productRepository.save(updateProduct2);
        
          

            return ResponseEntity.ok(createproduct);
    }


     //get  by ID
    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getItemById(@PathVariable Long id) {
        Product product = productRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Invalid Id"));
        return ResponseEntity.ok(product);
    }

    //delete product
    @DeleteMapping("/product/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteProduct(@PathVariable("id") Long id) {
        Product findProduct = productRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        productRepository.delete(findProduct);

        Map<String,Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
 
    }


        


    


    
    }





