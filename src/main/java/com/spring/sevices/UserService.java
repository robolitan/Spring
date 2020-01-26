package com.spring.sevices;

import com.spring.models.User;
import java.util.List;

public interface UserService {
    List<User> getAll();
    void addUser(User user);
    void editUser(User user);
    User getUser(int id);
    void deleteUser(int id);
}
