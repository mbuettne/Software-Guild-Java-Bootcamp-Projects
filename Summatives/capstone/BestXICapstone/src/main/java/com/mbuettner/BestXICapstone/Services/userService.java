/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.BestXICapstone.Services;

import com.mbuettner.BestXICapstone.Entities.User;
import com.mbuettner.BestXICapstone.Repositories.userRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author mbuet
 */
@Service
public class userService {
    
    @Autowired
    userRepo userRepo;
    
    public User saveOrUpdateUser(User user) {
        return userRepo.save(user);
    }
    
    public User getUserByUsername(String username){
        return userRepo.findByUsername(username);
    }
    
    public User getUserById(int userid) {
        User user = userRepo.findByUserid(userid);
        if (user == null) {
            return null;
        }
        return user;
    }
    
    public List<User> getAllUsersByTeam(int teamid) {
        return userRepo.findAllByTeamid(teamid);
    }
    
    public void deleteUser(int userid) {
        User user = userRepo.findByUserid(userid);
        
        if (user != null) {
            userRepo.delete(user);
        }
    }
    
    public List<User> getAllUsers(){
        return userRepo.findAll();
    }
}
