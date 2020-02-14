package com.spring.controllers;

import com.spring.models.Role;
import com.spring.models.SearchCriteria;
import com.spring.models.User;
import com.spring.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("api/admin")
public class AdminRestController {

    @Autowired
    UserService userService;

    @GetMapping(path = "users")
    public ResponseEntity users() {
        List<User> list = userService.getAll();
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(list);
    }

    @PostMapping("add")
    public ResponseEntity add(@RequestBody User user) throws IOException {
        userService.addUser(user);
        return  ResponseEntity.status(HttpStatus.OK).body(userService.getUserByLogin(user.getLogin()));
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }

    @GetMapping("{id}")
    public ResponseEntity user(@PathVariable("id") Integer id) throws IOException {
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(userService.getUser(id));
    }

    @PostMapping("/edit")
    public ResponseEntity edit(@RequestBody SearchCriteria criteria) {
        User user = getUserParseCriteria(criteria);
        userService.editUser(user);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(user);
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
