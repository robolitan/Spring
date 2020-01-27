package com.spring.sevices;

import com.spring.dao.UserDao;
import com.spring.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public UserServiceImpl() {
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User getUser(int id) {
        return userDao.get(id);
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        userDao.delete(id);
    }

    @Override
    @Transactional
    public void editUser(User user) {
        userDao.update(user);

    }

    @Override
    @Transactional
    public void addUser(User user) {
        userDao.save(user);
    }
}
