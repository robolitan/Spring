package com.spring.controllers;

import com.spring.models.User;
import com.spring.sevices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/all")
    public String getPageIndex(Model model) {
        model.addAttribute("usersList", userService.getAll());
        return "index";
    }

    @GetMapping("/add")
    public String getPageAdd() {
//        userService.loadUserByUsername("nikerson");
        return "add";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute("add") User user) {
        userService.addUser(user);
        return "redirect:/all";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return "redirect:/all";
    }

    @GetMapping("/edit/{id}")
    public String getPageEdit(@PathVariable int id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "edit";
    }

    @PostMapping("/edit")
    public String editUser(@ModelAttribute User user) {
        userService.editUser(user);
        return "redirect:/all";
    }
}
