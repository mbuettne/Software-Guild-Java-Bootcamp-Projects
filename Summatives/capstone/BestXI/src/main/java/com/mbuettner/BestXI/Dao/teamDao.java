/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.BestXI.Dao;

import com.mbuettner.BestXI.Entities.Team;
import java.util.List;

/**
 *
 * @author mbuet
 */
public interface teamDao {

    Team getTeamById(int id);

    Team getTeamByTeamName(String teamName);

    List<Team> getAllTeams();

    void updateTeam(Team team);

    void deleteTeam(int id);

    Team createTeam(Team team);
}
