package com.spring.controllers;

import com.spring.models.Role;
import com.spring.models.SearchCriteria;
import com.spring.models.ServiceResponse;
import com.spring.models.User;
import com.spring.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@RestController
public class AdminRestController {

    @Autowired
    UserService userService;

    @GetMapping("/admin/users")
    public ResponseEntity<Object> getUsers() {
        List<User> list = userService.getAll();
        ServiceResponse<List<User>> response = new ServiceResponse<>("success", list);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    @PostMapping("/admin/add")
    public ResponseEntity<Object> addNewUser(@RequestBody User user) throws IOException {
        userService.addUser(user);
        return new ResponseEntity<>(new ServiceResponse("success", null), HttpStatus.OK);
    }

    @PostMapping("/admin/delete")
    public ResponseEntity<Object> deleteUser(@RequestBody SearchCriteria criteria) {
        userService.deleteUser(criteria.getId());
        ServiceResponse resp = new ServiceResponse("success", criteria.getId());
        return new ResponseEntity<Object>(resp, HttpStatus.OK);
    }

    @GetMapping("/admin/user")
    public ResponseEntity<Object> getUserForEdit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServiceResponse resp = new ServiceResponse<User>("success",
                userService.getUser(Integer.parseInt(request.getParameter("id"))));
        return new ResponseEntity<Object>(resp, HttpStatus.OK);
    }

    @PostMapping("/admin/edit")
    public ResponseEntity<Object> editUser(@RequestBody SearchCriteria criteria) {
        User user = getUserParseCriteria(criteria);
        userService.editUser(user);
        ServiceResponse resp = new ServiceResponse("success", user);
        return new ResponseEntity<Object>(resp, HttpStatus.OK);
    }

    private User getUserParseCriteria(SearchCriteria criteria) {
        User user = new User();
        user.setId(criteria.getId());
        user.setLogin(criteria.getLogin());
        user.setFirstName(criteria.getFirstName());
        user.setLastName(criteria.getLastName());
        user.setBirthday(criteria.getBirthday());
        user.setPassword(criteria.getPassword());
        Set<Role> roles = criteria.getRoles();
        Role roleAdmin = new Role(2, "ROLE_ADMIN");
        boolean roleAdminPresent = roles.stream().anyMatch(n -> n.getName().equals("ROLE_ADMIN"));
        if (criteria.getIsAdmin().equals("0")) {
            if (roleAdminPresent) {
                roles.remove(roleAdmin);
            }
        } else if (criteria.getIsAdmin().equals("1")) {
            if (!roleAdminPresent) {
                roles.add(roleAdmin);
            }
        }
        user.setRoles(roles);
        return user;
    }
}
