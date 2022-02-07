package com.gunt.springboot.springboot_secur_bootstrap.securityConfig;

import com.gunt.springboot.springboot_secur_bootstrap.entity.Role;
import com.gunt.springboot.springboot_secur_bootstrap.entity.User;
import com.gunt.springboot.springboot_secur_bootstrap.service.RoleService;
import com.gunt.springboot.springboot_secur_bootstrap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataBaseInit {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public DataBaseInit(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    private void dataBaseInit() {
        Role roleAdmin = new Role("ADMIN");
        Role roleUser = new Role("USER");
        Set<Role> adminSet = new HashSet<>();
        Set<Role> userSet = new HashSet<>();

        roleService.addRole(roleAdmin);
        roleService.addRole(roleUser);

        adminSet.add(roleAdmin);
        adminSet.add(roleUser);
        userSet.add(roleUser);

        User admin = new User("Petr", "Petrov", (byte) 22, "pet@pet.com", "admin",
                "admin", adminSet);
        User user = new User("Sergey", "Sergeev", (byte) 55, "ser@ser.com", "user",
                "user", userSet);

        userService.saveUser(admin);
        userService.saveUser(user);

    }
}
