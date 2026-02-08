package com.example.demo.services;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthServices {
    @Autowired
    UserRepo userRepo;

    public String getPassword(String username){
        User user = userRepo.findByUsername(username).orElse(null);
        if(user==null)return "";
        else return user.getPassword();
    }
}
