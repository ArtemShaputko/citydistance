package com.closer.citydistance.controller;

import com.closer.citydistance.model.User;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    @RequestMapping("/add")
    public User add(User usr){
        return null;
    }
    @RequestMapping("/remove")
    public User remove(User usr){
        return null;
    }
    @RequestMapping("/show")
    public User show(User usr){
        return null;
    }
}
