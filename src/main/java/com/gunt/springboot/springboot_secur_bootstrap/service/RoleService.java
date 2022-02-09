package com.gunt.springboot.springboot_secur_bootstrap.service;

import com.gunt.springboot.springboot_secur_bootstrap.entity.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    List<Role> getAllRoles();

    Role getRole(String roleName);

    Role getRoleById(Long id);

    void addRole(Role role);
}
