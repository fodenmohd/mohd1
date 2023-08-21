package com.example.projectSpringboot.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.projectSpringboot.model.login;
import com.example.projectSpringboot.services.login_services;

@CrossOrigin
@RestController
@RequestMapping("api/Account")
public class loginController{
    private final login_services loginServices;

    public loginController(login_services loginServices) {
        this.loginServices = loginServices;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody login login) {
        Optional<login> existingUser = loginServices.getByUsername(login.getUsername());
        if (existingUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
        }

        login registeredUser = loginServices.registerUser(login);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }
    
    @PostMapping("/login")
    public ResponseEntity<Object> authenticate(@RequestParam("username") String username,
                                               @RequestParam("password") String password) {
        if (loginServices.authenticate(username, password)) {
            Optional<login> user = loginServices.getByUsername(username);
            if (user.isPresent()) {
                Map<String, Object> response = new HashMap<>();
                response.put("id", user.get().getId());
                response.put("username", user.get().getUsername());
                response.put("roles", user.get().getRoles());
                return ResponseEntity.ok(response);
            }
            // if(user.isPresent()){
            //     loginDTO loginDTO = new loginDTO();
            //     loginDTO.setId(user.get().getId());
            //     loginDTO.setUsername(user.get().getUsername());
            //     loginDTO.setRoles(user.get().getRoles());
            //     return ResponseEntity.ok(loginDTO);
            // }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
    }
}
