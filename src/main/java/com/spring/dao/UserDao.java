package com.spring.dao;

import com.spring.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDao{
    void save(User user);

    void delete(int id);

    void update(User user);

    User get(int id);

    List<User> getAll();

    User getUserByLogin(String login);
}
