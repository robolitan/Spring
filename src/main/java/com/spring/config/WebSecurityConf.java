package com.spring.config;

import com.spring.sevices.UserService;
import com.spring.sevices.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConf extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService service;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(service);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/admin**").access("hasAnyRole('ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/","/register","/login").permitAll();
        http.authorizeRequests().antMatchers("/home").access("hasAnyRole('ROLE_ADMIN','ROLE_USER')");

        http.authorizeRequests().antMatchers("/all").access("hasRole('ROLE_ADMIN')");
    }
}
