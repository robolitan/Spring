package com.spring.controllers;

import com.spring.sevices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DeleteController {

    @Autowired
    UserService userService;

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id){
        userService.deleteUser(id);
        return "redirect:/all";
    }
}
