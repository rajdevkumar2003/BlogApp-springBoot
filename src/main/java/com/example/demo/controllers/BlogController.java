package com.example.demo.controllers;


import com.example.demo.entity.Blog;
import com.example.demo.entity.User;
import com.example.demo.repository.BlogRepo;
import com.example.demo.services.BlogServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    private BlogServices blogServices;

    @GetMapping
    ResponseEntity<List<Blog>> GetBlog(){
        List<Blog> blogs= blogServices.getBlogs();
        return ResponseEntity.ok(blogs);
    }

    @PostMapping
    ResponseEntity<?> PostBlog(@RequestBody Blog blog){
        blogServices.postBlog(blog);
        return ResponseEntity.status(HttpStatus.CREATED).body(blog);
    }

    @GetMapping("/createdBy/{id}")
    ResponseEntity<?> GetBlogOwner(@PathVariable ObjectId id){
        User user=blogServices.GetBlogOwner(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
