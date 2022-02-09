package com.gunt.springboot.springboot_secur_bootstrap.service;

import com.gunt.springboot.springboot_secur_bootstrap.dao.RoleDAO;
import com.gunt.springboot.springboot_secur_bootstrap.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleDAO roleDAO;

    @Autowired
    public RoleServiceImpl(RoleDAO roleDao) {
        this.roleDAO = roleDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Role> getAllRoles() {
        return roleDAO.getAllRoles();
    }

    @Override
    @Transactional(readOnly = true)
    public Role getRole(String roleName) {
        return roleDAO.getRole(roleName);
    }

    @Override
    @Transactional(readOnly = true)
    public Role getRoleById(Long id) {
        return roleDAO.getRoleById(id);
    }

    @Override
    @Transactional
    public void addRole(Role role) {
        roleDAO.addRole(role);
    }

}
