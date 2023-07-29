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
import com.example.projectSpringboot.model.Item;
import com.example.projectSpringboot.repository.ItemRepository;


@CrossOrigin
@RestController
@RequestMapping("/api/item")
public class ItemController {
    @Autowired
    private ItemRepository itemRepository;


    // get all item
    @GetMapping("/GetAllItem")
    public List<Item> getAllItem() {
        return itemRepository.findAll();
    }

    // post item
    @PostMapping("/CreateItem")
    public Item addItem(@RequestBody Item item) {
        return itemRepository.save(item);
    }
  

    //   update item
       @PutMapping("/UpdateItem/{id}")
    public ResponseEntity<?> updateItem(@PathVariable("id") Long id, @RequestBody Item item) {
        Item updateItem2 = itemRepository.findById(id)
        .orElseThrow(() ->new ResourceNotFoundException("product not found"));
      
            updateItem2.setItem_Name(item.getItem_Name());
            updateItem2.setItem_price(item.getItem_price());
            updateItem2.setItem_Description(item.getItem_Description());
           
            
           Item createitem = itemRepository.save(updateItem2);
        
          

            return ResponseEntity.ok(createitem);
    }



    //  //get  by ID
    // @GetMapping("/item/{id}")
    // public ResponseEntity<Item> getItemById(@PathVariable Long id) {
    //     Item item = itemRepository.findById(id)
    //     .orElseThrow(() -> new ResourceNotFoundException("Invalid Id"));
    //     return ResponseEntity.ok(item);
    // }




    

//delete item
    @DeleteMapping("/DeleteItem/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteItem(@PathVariable("id") Long id) {
        Item findItem = itemRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("item not found"));
        itemRepository.delete(findItem);

        Map<String,Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
 
    }

    
}