/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.BestXI.Dao;

import com.mbuettner.BestXI.Entities.PlayerGame;
import java.util.List;

/**
 *
 * @author mbuet
 */
public interface playerGameDao {

    PlayerGame getPlayerGameById(int id);
    
    List<PlayerGame> getAllPlayerGames();

    List<PlayerGame> getAllPlayerGamesByPlayer(int playerId);

    void updatePlayerGame(PlayerGame playerGame);

    void deletePlayerGame(int id);

    PlayerGame createPlayerGame(PlayerGame playerGame);
}
