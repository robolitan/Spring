package com.spring.dao;

import com.spring.models.Role;
import com.spring.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public void save(User user) {
        Session session = sessionFactory.getCurrentSession();
        user.setRoles(Collections.singleton((Role)session.get(Role.class,1)));
        user.setPassword(encoder.encode(user.getPassword()));
        session.persist(user);
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.get(User.class, id));
    }

    @Override
    public void update(User user) {
        Session session = sessionFactory.getCurrentSession();
        User userById = session.get(User.class, user.getId());
        user.setPassword(userById.getPassword());
        session.merge(user);
    }

    @Override
    public User get(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, id);
    }

    @Override
    public User getUserByLogin(String login) {
        Session session = sessionFactory.openSession();
        String HQL = "FROM User WHERE login=:login";
        Query query = session.createQuery(HQL);
        query.setParameter("login",login);
        User userFromDB = (User) query.getSingleResult();
        return userFromDB;
    }

    @Override
    public List<User> getAll() {
        Session session = sessionFactory.getCurrentSession();
        Query q = session.createQuery("From User");
        List<User> list = q.getResultList();
        return list;
    }
}
