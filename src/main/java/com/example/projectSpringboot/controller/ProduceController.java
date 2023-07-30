package com.example.projectSpringboot.controller;

import java.time.LocalDate;
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
import com.example.projectSpringboot.model.Produce;
import com.example.projectSpringboot.repository.ProduceRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/produce")
public class ProduceController {

    @Autowired

    private ProduceRepository produceRepository;


    // get all produce
    @GetMapping("/GetAllproduce")
    public List<Produce> getAllProduce() {
        return produceRepository.findAll();
    }

    @GetMapping("/GetCostAndProduced")
    public  List<Map<String, Object>> getCostandProduced(){
        return produceRepository.getCostandProduced();
    }
    
    @GetMapping("/GetCostAndProduced/{startDate}/{endDate}")
    public  List<Map<String, Object>> getCostandProducedDate(@PathVariable("startDate") String startDate ,@PathVariable("endDate") String endDate){
        return produceRepository.getCostandProducedByDate(LocalDate.parse(startDate),LocalDate.parse(endDate) );
    }

     // post produce
    @PostMapping("/CreateProduce")
    public Produce addProduce(@RequestBody Produce produce) {
        return produceRepository.save(produce);
    }
  


     // update produce
       @PutMapping("/UpdateProduce/{id}")
    public ResponseEntity<?> updateProduce(@PathVariable("id") Long id, @RequestBody Produce produce) {
        Produce updateProduce2 = produceRepository.findById(id)
        .orElseThrow(() ->new ResourceNotFoundException("product not found"));
      
            updateProduce2.setProduct_Id(produce.getProduct_Id());
            updateProduce2.setQuantity(produce.getQuantity());
            updateProduce2.setTotal_Amount(produce.getTotal_Amount());
            updateProduce2.setDate(produce.getDate());

        Produce createproduce = produceRepository.save(updateProduce2);

            return ResponseEntity.ok(createproduce);
    }

    // //get  by ID
    // @GetMapping("/produce/{id}")
    // public ResponseEntity<Produce> getProduceById(@PathVariable Long id) {
    //     Produce produce = produceRepository.findById(id)
    //     .orElseThrow(() -> new ResourceNotFoundException("Invalid Id"));
    //     return ResponseEntity.ok(produce);
    // }



    
    //delete product produce
    @DeleteMapping("/DeleteProduce/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteProduce(@PathVariable("id") Long id) {
        Produce findProduce = produceRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Product not produced"));
        produceRepository.delete(findProduce);

        Map<String,Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
 
    }



    }






