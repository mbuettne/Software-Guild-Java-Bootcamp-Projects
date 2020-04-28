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
import java.util.List;

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
}
