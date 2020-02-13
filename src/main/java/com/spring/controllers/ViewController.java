package com.spring.controllers;

import com.spring.models.User;
import com.spring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

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

    @GetMapping("/admin")
    public String getPageIndex(Model model) {
        model.addAttribute("usersList", userService.getAll());
        model.addAttribute("user", new User());
        return "admin";
    }
}
