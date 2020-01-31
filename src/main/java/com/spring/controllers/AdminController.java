package com.spring.controllers;

import com.spring.models.Role;
import com.spring.models.User;
import com.spring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

@Controller
public class AdminController {

    @Autowired
    UserService userService;

    @GetMapping("/admin/all")
    public String getPageIndex(Model model, RequestMethod method) {
        model.addAttribute("usersList", userService.getAll());
        return "all_user";
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
        User user = userService.getUser(id);
        boolean roleAdmin = user.getRoles().stream().anyMatch(n -> n.getName().equals("ROLE_ADMIN"));
        model.addAttribute("user", user);
        model.addAttribute("admin", roleAdmin);
        return "edit";
    }

    @PostMapping("/admin/edit")
    public String editUser(@ModelAttribute User user, HttpServletRequest request) {
        if (request.getParameter("isAdmin") != null) {
            user.setRoles(Collections.singleton(new Role(2, "ROLE_ADMIN")));
        } else {
            user.setRoles(Collections.emptySet());
        }
        userService.editUser(user);
        return "redirect:/admin/all";
    }
}
