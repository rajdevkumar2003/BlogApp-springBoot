package com.example.demo.controllers;


import com.example.demo.entity.Blog;
import com.example.demo.repository.BlogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogRepo blogRepo;

    @GetMapping
    ResponseEntity<List<Blog>> GetBlog(){
        List<Blog> blogs= blogRepo.findAll();
        return ResponseEntity.ok(blogs);
    }

    @PostMapping
    ResponseEntity<?> PostBlog(@RequestBody Blog blog){
        blogRepo.save(blog);
        return ResponseEntity.status(HttpStatus.CREATED).body(blog);
    }
}
