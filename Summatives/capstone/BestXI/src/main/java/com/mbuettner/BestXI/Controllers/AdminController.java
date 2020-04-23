/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.BestXI.Controllers;

import com.mbuettner.BestXI.Dao.roleDao;
import com.mbuettner.BestXI.Dao.userDao;
import com.mbuettner.BestXI.Entities.Role;
import com.mbuettner.BestXI.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author mbuet
 */
@Controller
public class AdminController {
    
    @Autowired
    userDao users;
    
    @Autowired
    roleDao roles;
    
//    @Autowired
//    PasswordEncoder encoder;

    @GetMapping("/admin")
    public String displayAdminPage(Model model) {
        model.addAttribute("users", users.getAllUsers());
        return "admin";
    }
    
    @PostMapping("/addUser")
    public String addUser(String firstName, String lastName, String username, String password){
        User user = new User();
        Role role = roles.getRoleById(2);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setPassword(password);
        user.setEnabled(true);
        user.setRole(role);
        
        users.createUser(user);
        
        return "redirect:/admin";
    }
}
