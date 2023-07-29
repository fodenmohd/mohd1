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
import com.example.projectSpringboot.model.User;
import com.example.projectSpringboot.repository.UserRepository;



@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class UserController  {
    
    @Autowired
    private UserRepository userRepository;

        // get all user
    @GetMapping("/GetAlluser")
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

        // post user
    @PostMapping("/createuser")
    public User addUser(@RequestBody User user) {
        return userRepository.save(user);
    }
  

     // update user
       @PutMapping("/UpdateUser/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        User updateUser2 = userRepository.findById(id)
        .orElseThrow(() ->new ResourceNotFoundException("user not found"));
      
            updateUser2.setUserName(user.getUserName());
            updateUser2.setPassword(user.getPassword());
            updateUser2.setRole(user.getRole());
            updateUser2.setStutus(user.getStutus());
            
           
            
           User createuser = userRepository.save(updateUser2);
        
          

            return ResponseEntity.ok(createuser);
    }

    // //get  by ID
    // @GetMapping("/user/{id}")
    // public ResponseEntity<User> getUserById(@PathVariable Long id) {
    //     User user = userRepository.findById(id)
    //             .orElseThrow(() -> new ResourceNotFoundException("Invalid Id"));
    //     return ResponseEntity.ok(user);
    // }

        //delete audit
    @DeleteMapping("/DeleteUser/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteUser(@PathVariable("id") Long id) {
        User findUser = userRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userRepository.delete(findUser);

        Map<String,Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
 
    }
    







    




}
