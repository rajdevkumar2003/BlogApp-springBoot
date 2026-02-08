package com.example.demo.controllers;

import com.example.demo.entity.User;
import com.example.demo.services.AuthServices;
import com.example.demo.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthServices authServices;

    @Autowired
    JwtUtil JwtUtil;

    @PostMapping("/login")
    ResponseEntity<?> Login(@RequestBody User user){
        String pswd =authServices.getPassword(user.getUsername());
        if(pswd!= user.getPassword())return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username or Password is wrong!");

        return  ResponseEntity.status(HttpStatus.ACCEPTED).body(JwtUtil.generateToken(user.getUsername()));
    }
}
