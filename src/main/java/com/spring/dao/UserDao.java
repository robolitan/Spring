package com.spring.dao;

import com.spring.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserDao extends CrudRepository<User, Integer> {

    @Override
    <S extends User> S save(S user);

    @Override
    void deleteById(Integer integer);

    @Override
    Optional<User> findById(Integer integer);

    @Override
    Iterable<User> findAll();

    Optional<User> findByLogin(String login);

}


//

    /* void save(User user); +

    void delete(int id); +

    void update(User user);

    User get(int id); +

    List<User> getAll();  +

    User getUserByLogin(String login);*  +  /
}

     */
