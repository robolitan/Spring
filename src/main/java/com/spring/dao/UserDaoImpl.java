package com.spring.dao;

import com.spring.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.ParameterMetadata;
import org.hibernate.query.Query;
import org.hibernate.sql.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Stream;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void save(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
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




        return null;
    }

    @Override
    public List<User> getAll() {
        Session session = sessionFactory.getCurrentSession();
        Query q = session.createQuery("From User");
        List<User> list = q.getResultList();
        return list;
    }
}
