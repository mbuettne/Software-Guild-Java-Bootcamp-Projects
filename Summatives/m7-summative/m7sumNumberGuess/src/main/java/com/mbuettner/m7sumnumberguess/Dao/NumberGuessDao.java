/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.m7sumnumberguess.Dao;

import com.mbuettner.m7sumnumberguess.DTO.Game;
import com.mbuettner.m7sumnumberguess.DTO.Round;
import java.util.List;

/**
 *
 * @author mbuet
 */
public interface NumberGuessDao {

    public Game createGame(String answer);

    public List<Game> getAllGames();

    public Game getGameById(int id);

    public void updateGame(Game game);

    public Round addRound(Round round);
    
    public void updateRound(Round round);

    public List<Round> getAllRoundsByGame(int gameId);

    public Round getLatestRound(int gameId);
    
    public void deleteRound(int gameId);
    
    public void deleteGame(int gameId);
}
