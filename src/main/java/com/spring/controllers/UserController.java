package com.spring.controllers;

import com.spring.sevices.UserService;
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

    @GetMapping("/home")
    public String getPageHome(){
        return "home";
    }

    @GetMapping("/error")
    public String getPageError(){
        return "error";
    }

    @GetMapping("**")
    public String getPageUndef(){
        return "undef";
    }

}
