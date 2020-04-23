/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.BestXI.Dao;

import com.mbuettner.BestXI.Entities.TeamGame;
import java.util.List;

/**
 *
 * @author mbuet
 */
public interface teamGameDao {

    TeamGame getTeamGameById(int id);

    List<TeamGame> getAllTeamGames();
    
    List<TeamGame> getAllGamesByTeam(int teamId);

    void updateTeamGame(TeamGame teamGame);

    void deleteTeamGame(int id);

    TeamGame createTeamGame(TeamGame teamGame);
}
