package com.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@ComponentScan("com.spring")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userDetailService")
    UserDetailsService userDetailsService;

    @Autowired
    PasswordEncoder encoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                    /*.antMatchers("/css/*").permitAll()
                    .antMatchers("/login").not().fullyAuthenticated()
                    .antMatchers("/admin").hasRole("ADMIN")
                    .antMatchers("/user").hasAnyRole("USER","ADMIN")
                    .anyRequest().authenticated()*/.anyRequest().permitAll()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/user")
                    .failureUrl("/login")
                    .permitAll()
                .and()
                     .logout()
                     .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                     .logoutSuccessUrl("/login")
                     .permitAll()
                /*.and()
                    .exceptionHandling()
                    .accessDeniedPage("/error");*/;
    }
}
