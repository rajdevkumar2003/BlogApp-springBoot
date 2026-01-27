package com.example.demo.controllers;

import com.example.demo.entity.User;
import com.example.demo.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserServices userServices;

    @GetMapping
    ResponseEntity<List<User>> GetUsers(){
        List<User> users=userServices.getUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping
    ResponseEntity<User> PostUser(@RequestBody User newUser){
        userServices.postUser(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }
}
