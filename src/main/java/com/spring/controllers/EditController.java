package com.spring.controllers;

import com.spring.models.User;
import com.spring.sevices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EditController {
    @Autowired
    UserService userService;

    @GetMapping("/edit/{id}")
    public String getPage(@PathVariable int id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "edit";
    }

    @PostMapping("/edit")
    public String editUser(@ModelAttribute User user) {
        userService.editUser(user);
        return "redirect:/all";
    }
}