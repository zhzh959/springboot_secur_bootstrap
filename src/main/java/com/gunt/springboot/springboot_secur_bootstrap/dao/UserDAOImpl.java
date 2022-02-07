package com.gunt.springboot.springboot_secur_bootstrap.dao;

import com.gunt.springboot.springboot_secur_bootstrap.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public void deleteUser(long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    public User getUserByLogin(String login) {
        return entityManager.createQuery("SELECT u FROM User u WHERE u.login =: login", User.class)
                .setParameter("login", login).getSingleResult();
    }

    @Override
    public User getUserById(long id) {
        return entityManager.find(User.class, id);
    }
}
