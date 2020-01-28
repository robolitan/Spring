package com.spring.sevices;

import com.spring.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> getAll();

    void addUser(User user);

    void editUser(User user);

    User getUser(int id);

    void deleteUser(int id);

    UserDetails loadUserByUsername(String s) throws UsernameNotFoundException;
}
