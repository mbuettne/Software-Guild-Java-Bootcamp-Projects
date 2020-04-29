/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.BestXICapstone.Services;

import com.mbuettner.BestXICapstone.Entities.Role;
import com.mbuettner.BestXICapstone.Entities.User;
import com.mbuettner.BestXICapstone.Repositories.roleRepo;
import com.mbuettner.BestXICapstone.Repositories.userRepo;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author mbuet
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    userRepo users;

    @Autowired
    roleRepo roles;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = users.findByUsername(username);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        int roleid = user.getRoleid();
        Role role = roles.findByRoleid(roleid);
        grantedAuthorities.add(new SimpleGrantedAuthority(role.getRolename()));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }

}
