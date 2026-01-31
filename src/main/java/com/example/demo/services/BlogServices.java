package com.example.demo.services;

import com.example.demo.entity.Blog;
import com.example.demo.entity.User;
import com.example.demo.repository.BlogRepo;
import com.example.demo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BlogServices {
    @Autowired
    BlogRepo blogRepo;

    @Autowired
    UserRepo userRepo;

    public List<Blog> getBlogs(){
        return blogRepo.findAll();
    }

    public void postBlog(Blog blog){
        Blog newBlog=blogRepo.save(blog);

        User user= userRepo.findById(newBlog.getCreatedBy()).orElse(null);

        if(user!=null){
            List<Blog> userBlogs=user.getBlogs();
            userBlogs.add(newBlog);
            user.setBlogs(userBlogs);
            userRepo.save(user);
        }

    }
}
