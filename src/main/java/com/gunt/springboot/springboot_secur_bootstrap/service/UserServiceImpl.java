package com.gunt.springboot.springboot_secur_bootstrap.service;

import com.gunt.springboot.springboot_secur_bootstrap.dao.RoleDAO;
import com.gunt.springboot.springboot_secur_bootstrap.dao.UserDAO;
import com.gunt.springboot.springboot_secur_bootstrap.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;
    private final RoleDAO roleDAO;

    @Autowired
    @Lazy
    public UserServiceImpl(UserDAO userDAO, PasswordEncoder passwordEncoder, RoleDAO roleDAO) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
        this.roleDAO = roleDAO;
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.saveUser(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        if (!user.getPassword().equals(userDAO.getUserById(user.getId()).getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        userDAO.updateUser(user);
    }

    @Override
    @Transactional
    public void deleteUser(long id) {
        userDAO.deleteUser(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByLogin(String login) {
        return userDAO.getUserByLogin(login);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(long id) {
        return userDAO.getUserById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return userDAO.getUserByLogin(login);
    }

    @Override
    public void getUserRoles(User user) {
        user.setRoles(user.getRoles().stream()
                .map(role -> roleDAO.getRole(role.getRoleName()))
                .collect(Collectors.toSet()));
    }
}
