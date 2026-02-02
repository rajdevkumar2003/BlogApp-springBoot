package com.example.demo.services;

import com.example.demo.entity.Blog;
import com.example.demo.entity.User;
import com.example.demo.repository.BlogRepo;
import com.example.demo.repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BlogServices {
    @Autowired
    BlogRepo blogRepo;

    @Autowired
    UserRepo userRepo;

    public List<Blog> getBlogs(){
        return blogRepo.findAll();
    }

    public Blog getBlog(ObjectId id){
        Blog blog=blogRepo.findById(id).orElse(null);
        if(blog==null)return null;
        else return blog;
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

    public User GetBlogOwner(ObjectId id){
        Blog bl=blogRepo.findById(id).orElse(null);
        if(bl!=null){
            User user=userRepo.findById(bl.getCreatedBy()).orElse(null);
            return user;
        }
        return null;
    }

    public Blog patchBlog(ObjectId id, Blog newBlog){
        Blog blog=blogRepo.findById(id).orElse(null);
        blog.setContent(newBlog.getContent());
        blog.setTitle(newBlog.getTitle());
        blogRepo.save(blog);
        return blog;
    }

    public void deleteBlog(ObjectId id){
        blogRepo.deleteById(id);
        return;
    }
}
