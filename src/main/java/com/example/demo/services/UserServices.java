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
public class UserServices {
    @Autowired
    private BlogRepo blogRepo;

    @Autowired
    private UserRepo userRepo;

    public List<User> getUsers(){
        return userRepo.findAll();
    }

    public void postUser(User user){
        userRepo.save(user);
    }

    public User getUserById(ObjectId id){
        User user = userRepo.findById(id).orElse(null);
        return user;
    }

    public List<Blog> getBlogsOfUser(ObjectId id){
        User user=userRepo.findById(id).orElse(null);
        if(user!=null){
            return user.getBlogs();
        }
        else return null;
    }

    public User patchUser(ObjectId id, User newUser){
        User user =userRepo.findById(id).orElse(null);
        if(user!=null){
            user.setName(newUser.getName());
            user.setUsername(newUser.getUsername());
            user.setPassword(newUser.getPassword());
            userRepo.save(user);
            return user;
        }
        return null;
    }

    public void deleteUser(ObjectId id){
        User user=userRepo.findById(id).orElse(null);
        if(user==null)return;

        List<Blog> blogs=user.getBlogs();

        for(Blog it:blogs){
            blogRepo.deleteById(it.getId());
        }

        userRepo.deleteById(id);
        return;
    }

}
