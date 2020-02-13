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

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/admin/users", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getUsers() {
        List<User> list = new ArrayList<>();
        userService.getAll().forEach(list::add);
        ServiceResponse<List<User>> response = new ServiceResponse<>("success", list);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/admin/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<Object> addNewUser(@RequestBody User user) throws IOException {
        userService.addUser(user);
        return new ResponseEntity<>(new ServiceResponse("success", null), HttpStatus.OK);
    }

    @PostMapping(value = "/admin/delete", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<Object> deleteUser(@RequestBody SearchCriteria criteria){
        userService.deleteUser(criteria.getId());
        ServiceResponse resp = new ServiceResponse("success", criteria.getId());
        return new ResponseEntity<Object>(resp, HttpStatus.OK);
    }

    @GetMapping(value = "/admin/user", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<Object> getUserForEdit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServiceResponse resp = new ServiceResponse<User>("success",
                userService.getUser(Integer.parseInt(request.getParameter("id"))));
        return new ResponseEntity<Object>(resp, HttpStatus.OK);
    }

    @PostMapping("/admin/edit")
    public ResponseEntity<Object> editUser(@RequestBody SearchCriteria criteria) {
        userService.editUser(getUserParseCriteria(criteria));
        ServiceResponse resp = new ServiceResponse("success", null);
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
        Role roleAdmin = new Role(2,"ROLE_ADMIN");
        boolean roleAdminPresent = roles.stream().anyMatch(n->n.getName().equals("ROLE_ADMIN"));
        if (criteria.getIsAdmin().equals("0")) {
            if (roleAdminPresent) {
                roles.remove(roleAdmin);
            }
        } else if(criteria.getIsAdmin().equals("1")){
            if(!roleAdminPresent){
                roles.add(roleAdmin);
            }
        }
        user.setRoles(roles);
        return user;
    }
}
