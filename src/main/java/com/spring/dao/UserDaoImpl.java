package com.spring.dao;

import com.spring.models.Role;
import com.spring.models.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private EntityManager entityManager;

    @Override
    public void save(User user) {
        user.setRoles(Collections.singleton((Role) entityManager.find(Role.class, 1)));
        entityManager.persist(user);
    }

    @Override
    public void delete(int id) {
        entityManager.remove(entityManager.find(User.class, id));
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public User get(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User getUserByLogin(String login) {
        String HQL = "FROM User WHERE login=:login";
        Query query = (Query) entityManager.createQuery(HQL);
        query.setParameter("login", login);
        User userFromDB = (User) query.getSingleResult();
        return userFromDB;
    }

    @Override
    public List<User> getAll() {
        String HQL = "FROM User";
        List<User> list = entityManager.createQuery(HQL).getResultList();
        return list;
    }
}
