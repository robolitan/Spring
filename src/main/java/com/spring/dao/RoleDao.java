package com.spring.dao;

import com.spring.models.Role;

public interface RoleDao {
    Role get(int i);
    Role getByName(String name);
}
