package com.spring.services;

import com.spring.models.User;

import java.util.List;

public interface UserService {
    List<User> getAll();

    void addUser(User user);

    void editUser(User user);

    void deleteUser(int id);
}
