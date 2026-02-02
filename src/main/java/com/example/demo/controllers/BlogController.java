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
import org.springframework.web.service.annotation.DeleteExchange;

import java.util.List;

@RestController
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    private BlogServices blogServices;

    @GetMapping
    ResponseEntity<List<Blog>> GetBlogs(){
        List<Blog> blogs= blogServices.getBlogs();
        return ResponseEntity.ok(blogs);
    }

    @PostMapping
    ResponseEntity<?> PostBlog(@RequestBody Blog blog){
        blogServices.postBlog(blog);
        return ResponseEntity.status(HttpStatus.CREATED).body(blog);
    }

    @GetMapping("/{id}")
    ResponseEntity<?> GetBlog(@PathVariable ObjectId id){
        Blog blog=blogServices.getBlog(id);
        if(blog==null)return ResponseEntity.badRequest().body("wrong id");
        else return ResponseEntity.ok(blog);
    }

    @GetMapping("/createdBy/{id}")
    ResponseEntity<?> GetBlogOwner(@PathVariable ObjectId id){
        User user=blogServices.GetBlogOwner(id);
        if(user==null)return ResponseEntity.badRequest().body("blog doesn't exist!");
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PatchMapping("/edit/{id}")
    ResponseEntity<Blog> PatchBlog(@PathVariable ObjectId id, @RequestBody Blog blog){
        Blog newBlog=blogServices.patchBlog(id,blog);
        return ResponseEntity.ok(newBlog);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<?> DeleteBlog(@PathVariable ObjectId id){
        blogServices.deleteBlog(id);
        return ResponseEntity.noContent().build();
    }
}
