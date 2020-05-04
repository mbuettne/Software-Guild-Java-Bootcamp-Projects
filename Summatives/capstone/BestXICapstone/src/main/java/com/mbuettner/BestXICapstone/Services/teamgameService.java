/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.BestXICapstone.Services;

import com.mbuettner.BestXICapstone.Entities.Teamgame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mbuettner.BestXICapstone.Repositories.teamgameRepo;
import java.util.List;

/**
 *
 * @author mbuet
 */
@Service
public class teamgameService {
    
    @Autowired
    teamgameRepo teamgameRepo;
    
    public Teamgame saveOrUpdateTeamgame(Teamgame teamgame) {
        return teamgameRepo.save(teamgame);
    }
    
    public Teamgame getTeamgameById(int teamgameid) {
        Teamgame tg = teamgameRepo.findByTeamgameid(teamgameid);
        
        if (tg == null) {
            return null;
        }
        
        return tg;
    }
    
    public List<Teamgame> getAllGamesByTeam(int teamid) {
        return teamgameRepo.findByTeamid(teamid);
    }
    
    public List<Teamgame> getAllGamesByTeamSorted(int teamid){
        return teamgameRepo.findByTeamidOrderByGamedateDesc(teamid);
    }
    
    public Teamgame getLatestGameByTeam(int teamid){
        List<Teamgame> gameList = teamgameRepo.findByTeamidOrderByGamedateDesc(teamid);
        Teamgame gameToReturn = gameList.get(0);
        
        return gameToReturn;
    }
    
    public void deleteTeamgame(int teamgameid) {
        Teamgame tg = teamgameRepo.findByTeamgameid(teamgameid);
        
        if (tg != null) {
            teamgameRepo.delete(tg);
        }
    }
    
    public List<Teamgame> getAllTeamgames(){
        return teamgameRepo.findAll();
    }
    
}
