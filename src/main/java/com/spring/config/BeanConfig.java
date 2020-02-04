package com.spring.config;

import com.spring.dao.RoleDao;
import com.spring.dao.RoleDaoImpl;
import com.spring.dao.UserDao;
import com.spring.dao.UserDaoImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BeanConfig {
    @Bean(name = "userDao")
    public UserDao getApplicantDAO() {
        return new UserDaoImpl();
    }

    @Bean(name = "roleDao")
    public RoleDao getApplicantDao(){
        return new RoleDaoImpl();
    }

    @Bean(name = "encoder")
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
