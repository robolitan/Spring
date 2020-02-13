package com.spring.services;

import com.spring.dao.RoleDao;
import com.spring.dao.UserDao;
import com.spring.models.Role;
import com.spring.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    RoleDao roleDao;

    public UserServiceImpl() {
    }

    @Override
    public List<User> getAll() {
        ArrayList<User> list = new ArrayList<>();
        Iterable<User> users = userDao.findAll();
        users.forEach(list::add);
        return list;
    }

    @Override
    public User getUser(int id) {
        return userDao.findById(id).orElse(new User());
    }

    @Override
    public void addUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRoles(Collections.singleton(roleDao.findById(1).orElse(new Role(0, "undef"))));
        userDao.save(user);
    }

    @Override
    public void editUser(User user) {
        userDao.save(user);
    }

    @Override
    public void deleteUser(int id) {
        userDao.deleteById(id);
    }
}
