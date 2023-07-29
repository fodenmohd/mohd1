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
import com.example.projectSpringboot.model.Cost;
import com.example.projectSpringboot.repository.CostRepository;





@CrossOrigin
@RestController
@RequestMapping("/api/cost")
public class CostController {
     
    @Autowired
    private CostRepository costRepository;

    // get all costProduction
    @GetMapping("/GetAllCost")
    public List<Cost> getAllCosts() {
        return costRepository.findAll();
    }

    // post cost
    @PostMapping("/CreateCost")
    public Cost addCost(@RequestBody Cost cost) {
        return costRepository.save(cost);
    }

     // update cost
       @PutMapping("/UpdateCost/{id}")
    public ResponseEntity<?> updateCost(@PathVariable("id") Long id, @RequestBody Cost cost) {
        Cost updateCost2 = costRepository.findById(id)
        .orElseThrow(() ->new ResourceNotFoundException("product not found"));
      
            updateCost2.setItem_Id(cost.getItem_Id());
            updateCost2.setQuantity(cost.getQuantity());
            updateCost2.setTotal(cost.getTotal());
            updateCost2.setDate(cost.getDate());
            
           
            
           Cost createcost = costRepository.save(updateCost2);
        
          

            return ResponseEntity.ok(createcost);
    }

    // //get  by ID
    // @GetMapping("/cost/{id}")
    // public ResponseEntity<Cost> getCostById(@PathVariable Long id) {
    //     Cost cost = costRepository.findById(id)
    //     .orElseThrow(() -> new ResourceNotFoundException("Invalid Id"));
    //     return ResponseEntity.ok(cost);
    // }
  
    //delete cost
    @DeleteMapping("/DeleteCost/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteCost(@PathVariable("id") Long id) {
        Cost findCost = costRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Cost not found"));
        costRepository.delete(findCost);

        Map<String,Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
 
    }


    
}
