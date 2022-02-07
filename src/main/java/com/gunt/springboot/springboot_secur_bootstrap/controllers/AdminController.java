package com.gunt.springboot.springboot_secur_bootstrap.controllers;

import com.gunt.springboot.springboot_secur_bootstrap.entity.User;
import com.gunt.springboot.springboot_secur_bootstrap.service.RoleService;
import com.gunt.springboot.springboot_secur_bootstrap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String showAllUsers(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.getAllRoles());
        return "all-users";
    }

    @GetMapping("/addNewUser")
    public String addPage(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.getAllRoles());
        return "updateInfo";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {
        getUserRoles(user);
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @PutMapping("/updateinfo/{id}")
    public String editPage(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("roles", roleService.getAllRoles());
        getUserRoles(user);
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    private void getUserRoles(User user) {
        user.setRoles(user.getRoles().stream()
                .map(role -> roleService.getRole(role.getRoleName()))
                .collect(Collectors.toSet()));
    }
}
