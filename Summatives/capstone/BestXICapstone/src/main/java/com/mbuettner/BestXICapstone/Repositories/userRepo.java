/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.BestXICapstone.Repositories;

import com.mbuettner.BestXICapstone.Entities.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mbuet
 */
@Repository
public interface userRepo extends JpaRepository<User, Integer>{
    
    List<User> findAllByTeamid(int teamid);
    
    User findByUsername(String username);
    
    User findByUserid(int userid);
}
