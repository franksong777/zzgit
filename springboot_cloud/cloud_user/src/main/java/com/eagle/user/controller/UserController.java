package com.eagle.user.controller;


import com.eagle.user.domain.User;
import com.eagle.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public User getBy(@PathVariable Long id){
        User user = userService.getById(id);
        return user;
    }

    @GetMapping
    public List<User> getAll(){
        List<User> users = userService.list();
        return users;
    }
}
