package com.example.demo.controllers;

import com.example.demo.entity.Blog;
import com.example.demo.entity.User;
import com.example.demo.services.UserServices;
import org.bson.types.ObjectId;
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

    @GetMapping("/{id}")
    ResponseEntity<User> GetUser(@PathVariable ObjectId id){
        User user=userServices.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/blogs/{id}")
    ResponseEntity<List<Blog>> GetBlogsOfUser(@PathVariable ObjectId id){
        List<Blog> blogs=userServices.getBlogsOfUser(id);
        return ResponseEntity.ok(blogs);
    }

    @PatchMapping("/edit/{id}")
    ResponseEntity<?> PatchUser(@PathVariable ObjectId id, @RequestBody User user){
        User newUser= userServices.patchUser(id,user);
        if(newUser==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("user id doesn't exist!");
        }
        return ResponseEntity.ok(newUser);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<?> DeleteUser(@PathVariable ObjectId id){
        userServices.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
