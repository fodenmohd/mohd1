package com.example.projectSpringboot.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;


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
    public Product addProduct(@RequestBody Product p) {
        Product p2 = new Product();
                p2.setAvg(p.getAvg());
                p2.setDescription(p.getDescription());
                p2.setProduct_name(p.getProduct_name());
                p2.setProduct_price(p.getProduct_price());
                p2.setIsUsed(p.getIsUsed());
            return productRepository.save(p2);
    }
    

    // update product
       @PutMapping("/update_product/{id}")
       @Transactional
       public Optional<Product> updateProduct(@PathVariable("id") Long id,@RequestBody Product p){
            return productRepository.findById(id).map(p2->{
                p2.setAvg(p.getAvg());
                p2.setDescription(p.getDescription());
                p2.setProduct_name(p.getProduct_name());
                p2.setProduct_price(p.getProduct_price());
                p2.setIsUsed(p.getIsUsed());
                return p2;
            });
       }

 

     //get  by ID
    @GetMapping("/get_product_by_id/{id}")


    public Product getProductById(@PathVariable("id") Long id){
        Optional<Product> p = productRepository.findById(id);
        if(p.isPresent()){
            return p.get();
        }else{
            return new Product();
        }
    }

    //delete product
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteProduct(@PathVariable("id") Long id) {
        Product findProduct = productRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        productRepository.delete(findProduct);

        Map<String,Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
 
    }

    @GetMapping("/getCurrentProducts")
    public List<Product> getCurrentProduct()
    {
        List<Product> k = productRepository.getCurrentProduct();
        return k;
    }
    
    }






