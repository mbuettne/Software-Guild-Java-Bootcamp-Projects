/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.BestXICapstone.Repositories;

import com.mbuettner.BestXICapstone.Entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mbuet
 */
@Repository
public interface teamRepo extends JpaRepository<Team, Integer>{
    
    Team findByTeamname(String teamname);
    
    Team findByTeamid(int teamid);
    
    @Query(value="INSERT INTO team(teamName, coachName) VALUES(?, ?)", nativeQuery=true)
    void createTeam(String teamname, String coachname);
}
