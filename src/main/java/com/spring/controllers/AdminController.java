package com.spring.controllers;

import com.spring.models.User;
import com.spring.sevices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

    @Autowired
    UserService userService;

    @GetMapping("/admin/all")
    public String getPageIndex(Model model) {
        model.addAttribute("usersList", userService.getAll());
        return "index";
    }

    @GetMapping("/admin/add")
    public String getPageAdd() {
        return "add";
    }

    @PostMapping("/admin/add")
    public String addUser(@ModelAttribute("add") User user) {
        userService.addUser(user);
        return "redirect:/admin/all";
    }

    @PostMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return "redirect:/admin/all";
    }

    @GetMapping("/admin/edit/{id}")
    public String getPageEdit(@PathVariable int id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "edit";
    }

    @PostMapping("/admin/edit")
    public String editUser(@ModelAttribute User user) {
        userService.editUser(user);
        return "redirect:/admin/all";
    }

}
