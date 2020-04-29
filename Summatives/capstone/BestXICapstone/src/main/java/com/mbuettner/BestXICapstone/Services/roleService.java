/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.BestXICapstone.Services;

import com.mbuettner.BestXICapstone.Repositories.roleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.mbuettner.BestXICapstone.Entities.Role;

/**
 *
 * @author mbuet
 */
@Service
public class roleService {
    
    @Autowired
    roleRepo roleRepo;
    
    public List<Role> returnAllRoles(){
        return roleRepo.findAll();
    }
}
