package com.example.demo.controllers;

import com.example.demo.entity.User;
import com.example.demo.services.AuthServices;
import com.example.demo.services.UserServices;
import com.example.demo.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthServices authServices;

    @Autowired
    private JwtUtil JwtUtil;

    @Autowired
    private UserServices userServices;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {

        String dbPassword = authServices.getPassword(user.getUsername());

        if (!dbPassword.equals(user.getPassword())) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Username or Password is wrong!");
        }

        return ResponseEntity.ok(
                JwtUtil.generateToken(user.getUsername())
        );
    }

    @PostMapping("/signup")
    public ResponseEntity<?> SignUp(@RequestBody User user) {
        userServices.postUser(user);
        return  ResponseEntity.status(HttpStatus.CREATED).body("Signed up successfully!");
    }
}
