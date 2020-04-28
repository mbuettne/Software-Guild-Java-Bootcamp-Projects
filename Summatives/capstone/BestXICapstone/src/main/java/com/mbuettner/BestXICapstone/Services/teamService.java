/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.BestXICapstone.Services;

import com.mbuettner.BestXICapstone.Entities.Player;
import com.mbuettner.BestXICapstone.Entities.Team;
import com.mbuettner.BestXICapstone.Entities.Teamgame;
import com.mbuettner.BestXICapstone.Entities.User;
import com.mbuettner.BestXICapstone.Repositories.teamRepo;
import com.mbuettner.BestXICapstone.Repositories.teamgameRepo;
import com.mbuettner.BestXICapstone.Repositories.userRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author mbuet
 */
@Service
public class teamService {

    @Autowired
    teamRepo teamRepo;
    
    @Autowired
    userRepo userRepo;
    
    @Autowired
    teamgameRepo teamgameRepo;
    
    @Autowired
    playerService playerService;

    public Team saveOrUpdateTeam(Team team) {
        return teamRepo.save(team);
    }
    
    public Team getTeamById(int teamid){
        return teamRepo.findByTeamid(teamid);
    }
    
    public void deleteTeam(int teamid){
        Team team = teamRepo.findByTeamid(teamid);
        
        if(team != null){
            List<User> users = userRepo.findAllByTeamid(teamid);
            for(User user : users){
                userRepo.delete(user);
            }
            
            List<Teamgame> teamgames = teamgameRepo.findByTeamid(teamid);
            for(Teamgame teamgame : teamgames){
                teamgameRepo.delete(teamgame);
            }
            
            List<Player> players = playerService.getAllPlayersByTeam(teamid);
            for(Player player : players){
                playerService.deletePlayerById(player.getPlayerid());
            }
            
            teamRepo.delete(team);
        }
    }
}
