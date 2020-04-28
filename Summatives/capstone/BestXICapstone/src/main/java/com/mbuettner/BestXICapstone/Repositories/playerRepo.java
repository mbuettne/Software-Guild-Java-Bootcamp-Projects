/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.BestXICapstone.Repositories;

import com.mbuettner.BestXICapstone.Entities.Player;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mbuet
 */
@Repository
public interface playerRepo extends JpaRepository<Player, Integer>{
    
    List<Player> findByTeamid(int teamid);
    
    List<Player> findByTeamidAndPlayerposition(int teamid, String playerposition);
    
    Player findByLastname(String lastname);
    
    Player findByPlayerid(int playerid);
    
    
    
}
