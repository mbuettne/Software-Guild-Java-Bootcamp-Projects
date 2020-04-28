/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.BestXICapstone.Repositories;

import com.mbuettner.BestXICapstone.Entities.Teamgame;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mbuet
 */
@Repository
public interface teamgameRepo extends JpaRepository<Teamgame, Integer>{
    
   List<Teamgame> findByTeamid(int teamid);
   
   List<Teamgame> findByTeamidOrderByGamedateDesc(int teamid);
   
   Teamgame findByTeamgameid(int teamgameid);
   
   
}
