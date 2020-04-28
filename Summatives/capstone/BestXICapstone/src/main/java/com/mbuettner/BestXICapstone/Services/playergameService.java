/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.BestXICapstone.Services;

import com.mbuettner.BestXICapstone.Entities.Player;
import com.mbuettner.BestXICapstone.Entities.Playergame;
import com.mbuettner.BestXICapstone.Repositories.playerRepo;
import com.mbuettner.BestXICapstone.Repositories.playergameRepo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author mbuet
 */
@Service
public class playergameService {

    @Autowired
    playergameRepo playergameRepo;

    @Autowired
    playerRepo playerRepo;

    public Playergame saveOrUpdatePlayergame(Playergame playergame) {
        return playergameRepo.save(playergame);
    }

    public Playergame getPlayergameById(int playergameid) {
        Playergame pg = playergameRepo.findByPlayergameid(playergameid);

        if (pg == null) {
            return null;
        }
        return pg;
    }

    public List<Playergame> getAllGamesByPlayer(int playerid) {
        return playergameRepo.findByPlayerid(playerid);
    }

    public void deletePlayergameById(int playergameid) {
        Playergame pg = playergameRepo.findByPlayergameid(playergameid);

        if (pg != null) {
            playergameRepo.delete(pg);
        }
    }
    
    public Map<Integer,Player> findTopGoalScorers(int teamid) {
        List<Player> players = playerRepo.findByTeamid(teamid);
        int comparison = 100;
        int mapkey = 1;
        Map<Integer, Player> topScorers = new HashMap<Integer, Player>();
        while (comparison > 0 && topScorers.size() <3) {
            for (Player player : players) {
                Playergame totalstats = totalStatsByPlayer(player.getPlayerid());
                
                if(totalstats.getGoals()==comparison){
                    topScorers.put(mapkey,player);
                    mapkey ++;
                }
            }
            comparison--;
        }
        return topScorers;
    }
    
    public List<String> topScorerStringList(int teamid){
         Map topScorersMap = findTopGoalScorers(teamid);
        Map<Integer, Player> topScorersTree = new TreeMap<Integer, Player>(topScorersMap);
        List<String> scorerStringList = new ArrayList<>();

        Player top = topScorersTree.get(1);
        Playergame pg1 = totalStatsByPlayer(top.getPlayerid());
        int topGoals = pg1.getGoals();
        String topString = top.getFirstname() + " " + top.getLastname() + " - " + topGoals + " Goals";
        scorerStringList.add(topString);

        Player second = topScorersTree.get(2);
        Playergame pg2 =totalStatsByPlayer(second.getPlayerid());
        int secondGoals = pg2.getGoals();
        String secondString = second.getFirstname() + " " + second.getLastname() + " - " + secondGoals + " Goals";
        scorerStringList.add(secondString);

        Player third = topScorersTree.get(3);
        Playergame pg3 = totalStatsByPlayer(third.getPlayerid());
        int thirdGoals = pg3.getGoals();
        String thirdString = third.getFirstname() + " " + third.getLastname() + " - " + thirdGoals + " Goals";
        scorerStringList.add(thirdString);
        
        return scorerStringList;
    }

    public Playergame totalStatsByPlayer(int playerid) {

        int totalShots = 0;
        int totalGoals = 0;
        int totalAssists = 0;
        int totalDribbles = 0;
        int totalPasses = 0;
        int avePassPercentage = 0;
        int totalTackles = 0;
        int totalInterceptions = 0;
        int totalShotsDefensed = 0;
        int totalShotsSaved = 0;
        int totalGoalsAllowed = 0;

        List<Playergame> playergames = playergameRepo.findByPlayerid(playerid);

        for (Playergame pg : playergames) {
            totalShots += pg.getShots();
            totalGoals += pg.getGoals();
            totalAssists += pg.getAssists();
            totalDribbles += pg.getDribbles();
            totalPasses += pg.getPasses();
            avePassPercentage += pg.getPasspercentage();
            totalTackles += pg.getTackles();
            totalInterceptions += pg.getInterceptions();
            totalShotsDefensed += pg.getShotsdefensed();
            totalShotsSaved += pg.getShotssaved();
            totalGoalsAllowed += pg.getGoalsallowed();
        }

        Playergame totalStats = new Playergame();

        totalStats.setShots(totalShots);
        totalStats.setGoals(totalGoals);
        totalStats.setAssists(totalAssists);
        totalStats.setDribbles(totalDribbles);
        totalStats.setPasses(totalPasses);
        if(playergames.size() !=0){
             totalStats.setPasspercentage((avePassPercentage / playergames.size()));
        } else {
            totalStats.setPasspercentage(0);
        }
       
        totalStats.setTackles(totalTackles);
        totalStats.setInterceptions(totalInterceptions);
        totalStats.setShotsdefensed(totalShotsDefensed);
        totalStats.setShotssaved(totalShotsSaved);
        totalStats.setGoalsallowed(totalGoalsAllowed);
        totalStats.setPlayerid(playerid);

        return totalStats;

    }

}
