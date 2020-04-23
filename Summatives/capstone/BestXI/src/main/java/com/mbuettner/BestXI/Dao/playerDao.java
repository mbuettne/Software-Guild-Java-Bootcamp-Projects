/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.BestXI.Dao;

import com.mbuettner.BestXI.Entities.Player;
import java.util.List;

/**
 *
 * @author mbuet
 */
public interface playerDao {

    Player getPlayerById(int id);

    Player getPlayerByLastName(String PlayerLastName);
    
    List<Player> getAllPlayers();

    List<Player> getAllPlayersByTeam(int teamId);

    void updatePlayer(Player player);

    void deletePlayer(int id);

    Player createPlayer(Player player);
}
