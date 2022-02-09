package com.gunt.springboot.springboot_secur_bootstrap.dao;

import com.gunt.springboot.springboot_secur_bootstrap.entity.Role;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;


@Repository
public class RoleDAOImpl implements RoleDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("SELECT r FROM Role r", Role.class).getResultList();
    }

    @Override
    public Role getRole(String roleName) {
        return entityManager.createQuery("SELECT r FROM Role r WHERE r.roleName =: roleName", Role.class)
                .setParameter("roleName", roleName).getSingleResult();
    }

    @Override
    public Role getRoleById(Long id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public void addRole(Role role) {
        entityManager.persist(role);
    }

}