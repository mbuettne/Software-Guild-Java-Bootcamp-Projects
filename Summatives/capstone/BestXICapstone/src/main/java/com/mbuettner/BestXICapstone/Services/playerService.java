/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.BestXICapstone.Services;

import com.mbuettner.BestXICapstone.Entities.Player;
import com.mbuettner.BestXICapstone.Entities.Playergame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mbuettner.BestXICapstone.Repositories.playerRepo;
import com.mbuettner.BestXICapstone.Repositories.playergameRepo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author mbuet
 */
@Service
public class playerService {

    @Autowired
    private playerRepo playerRepo;

    @Autowired
    private playergameRepo playergameRepo;

    @Autowired
    private playergameService playergameService;

    public Player saveOrUpdatePlayer(Player player) {
        return playerRepo.save(player);
    }

    public Player getPlayerById(int playerid) {
        Player player = playerRepo.findByPlayerid(playerid);

        if (player == null) {
            return null;
        }
        return player;
    }

    public List<Player> getAllPlayersByTeam(int teamid) {
        return playerRepo.findByTeamid(teamid);
    }
    
        public List<Player> getAllPlayersByTeamSorted(int teamid) {
        return playerRepo.findByTeamidOrderByPlayerpositionAsc(teamid);
    }

    public void deletePlayerById(int playerid) {
        Player player = playerRepo.findByPlayerid(playerid);

        if (player != null) {
            List<Playergame> playergames = playergameRepo.findByPlayerid(playerid);
            for (Playergame pg : playergames) {
                playergameRepo.delete(pg);
            }
            playerRepo.delete(player);
        }

    }

    public List<Player> getByTeamAndPosition(int teamid, String playerposition) {
        return playerRepo.findByTeamidAndPlayerposition(teamid, playerposition);
    }

    public List<Player> findBestForwards(int teamid) {
        List<Player> allForwards = getByTeamAndPosition(teamid, "Forward");
        int comparison = 200;
        List<Player> topForwards = new ArrayList<>();
        if (allForwards.size() >= 3) {
            while (comparison > 0 && topForwards.size() < 3) {
                for (Player player : allForwards) {
                    Playergame totalstats = playergameService.totalStatsByPlayer(player.getPlayerid());

                    if ((totalstats.getGoals() + totalstats.getAssists()) == comparison) {
                        topForwards.add(player);
                    }
                }
                comparison--;
            }
        }

        return topForwards;
    }

    public List<Player> findBestMidfielders(int teamid) {
        List<Player> allMids = getByTeamAndPosition(teamid, "Midfield");
        int comparison = 100;
        List<Player> topMids = new ArrayList<>();
        if (allMids.size() >= 3) {
            while (comparison > 0 && topMids.size() < 3) {
                for (Player player : allMids) {
                    Playergame totalstats = playergameService.totalStatsByPlayer(player.getPlayerid());

                    if ((totalstats.getTackles() + totalstats.getAssists()) == comparison) {
                        topMids.add(player);
                    }
                }
                comparison--;
            }
        }

        return topMids;
    }

    public List<Player> findBestDefenders(int teamid) {
        List<Player> allDef = getByTeamAndPosition(teamid, "Defender");
        int comparison = 200;
        List<Player> topDef = new ArrayList<>();
        if (allDef.size() >= 4) {
            while (comparison > 0 && topDef.size() < 4) {
                for (Player player : allDef) {
                    Playergame totalstats = playergameService.totalStatsByPlayer(player.getPlayerid());

                    if ((totalstats.getTackles() + totalstats.getInterceptions()) == comparison) {
                        topDef.add(player);
                    }
                }
                comparison--;
            }
        }

        return topDef;
    }
    
    public Player findBestGoalkeeper(int teamid) {
        List<Player> allGK = getByTeamAndPosition(teamid, "Goalkeeper");
        int comparison = 100;
        if (allGK.size() >= 1) {
            while (comparison > 0) {
                for (Player player : allGK) {
                    Playergame totalstats = playergameService.totalStatsByPlayer(player.getPlayerid());

                    if (totalstats.getShotssaved() == comparison) {
                        return player;
                    }
                }
                comparison--;
            }
        }

        return null;
    }
}
