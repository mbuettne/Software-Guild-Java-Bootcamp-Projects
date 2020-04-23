/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.BestXI.Dao;

import com.mbuettner.BestXI.Entities.User;
import java.util.List;

/**
 *
 * @author mbuet
 */
public interface userDao {
    User getUserById(int id);

    User getUserByUsername(String username);

    List<User> getAllUsers();
    
    List<User> getAllUsersByTeam(int teamId);

    void updateUser(User user);

    void deleteUser(int id);

    User createUser(User user);
    
}
