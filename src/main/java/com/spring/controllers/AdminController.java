package com.spring.controllers;

import com.spring.models.Role;
import com.spring.models.User;
import com.spring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AdminController {

    @Autowired
    UserService userService;

    @GetMapping("/admin")
    public String getPageIndex(Model model) {
        model.addAttribute("usersList", userService.getAll());
        model.addAttribute("user", new User());
        return "admin";
    }

    @PostMapping("/admin/add")
    public String addUser(@ModelAttribute User user) {
        userService.addUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @PostMapping("/admin/edit")
    public String editUser(@ModelAttribute User user, @RequestParam("isAdmin") String isAdmin) {
        List<Role> roles = user.getRoles();
        Role adminRole = new Role(2, "ROLE_ADMIN");
        if(isAdmin.toLowerCase().equals("admin") & roles.size() < 2){
            roles.add(adminRole);
        } else if(roles.size() > 1){
            roles = roles.stream().filter(n->!n.getName().equals("ROLE_ADMIN")).collect(Collectors.toList());
        }
        user.setRoles(roles);
        userService.editUser(user);
        return "redirect:/admin";
    }
}
