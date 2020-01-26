package com.spring.controllers;

import com.spring.models.User;
import com.spring.sevices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AddController {

    @Autowired
    UserService service;

    @RequestMapping("/add")
    public String getPage() {
        return "add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String getMessage(@ModelAttribute("add") User user) {
        service.addUser(user);
        return "redirect:/all";
    }
}
