package com.spring.models;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id_user")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_login")
    private String login;

    @Column(name = "user_f_name")
    private String firstName;

    @Column(name = "user_l_name")
    private String lastName;

    @Column(name = "birthday")
    private String birthday;

    @Column(name = "password")
    private String password;

    public User() {
    }

    public User(String login, String firstName, String lastName, String password, String birthday) {
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.password = password;
    }

    public User(int id, String login, String firstName, String lastName,  String password, String birthday) {
        this(login, firstName, lastName, password, birthday);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
