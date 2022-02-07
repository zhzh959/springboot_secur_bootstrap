package com.gunt.springboot.springboot_secur_bootstrap.dao;

import com.gunt.springboot.springboot_secur_bootstrap.entity.User;

import java.util.List;

public interface UserDAO {

    void saveUser(User user);

    void updateUser(User user);

    void deleteUser(long id);

    List<User> getAllUsers();

    User getUserByLogin(String login);

    User getUserById(long id);
}
