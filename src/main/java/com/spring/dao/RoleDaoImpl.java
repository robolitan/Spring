package com.spring.dao;

import com.spring.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.EntityManager;

public class RoleDaoImpl implements RoleDao {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Role get(int id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public Role getByName(String name) {
        return null;
    }
}
