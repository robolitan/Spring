package com.spring.controllers;

import com.spring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String getPageLogin(){
        return "login";
    }

    @GetMapping("/user")
    public String getPageHome(){
        return "user";
    }

}
