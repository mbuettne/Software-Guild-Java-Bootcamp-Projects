/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.BestXI.Dao;

import com.mbuettner.BestXI.Dao.TeamDaoDB.TeamMapper;
import com.mbuettner.BestXI.Entities.Team;
import com.mbuettner.BestXI.Entities.TeamGame;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author mbuet
 */
@Repository
public class TeamGameDaoDB implements teamGameDao {
    
    @Autowired
    JdbcTemplate jdbc;
    
    @Override
    public TeamGame getTeamGameById(int id) {
        try {
            final String SELECT_TEAMGAME_BY_ID = "SELECT * FROM teamGame WHERE teamGameId = ?";
            TeamGame teamGame = jdbc.queryForObject(SELECT_TEAMGAME_BY_ID, new TeamGameMapper(), id);
            teamGame.setTeam(getTeamForTeamGame(id));
            return teamGame;
        } catch (DataAccessException ex) {
            return null;
        }
    }
    
    private Team getTeamForTeamGame(int teamGameId) {
        final String SELECT_TEAM_FOR_TEAMGAME = "SELECT team.* FROM team JOIN teamGame ON team.teamId = teamGame.teamId WHERE teamGameId = ?";
        return jdbc.queryForObject(SELECT_TEAM_FOR_TEAMGAME, new TeamMapper(), teamGameId);
    }
    
    @Override
    public List<TeamGame> getAllTeamGames() {
        final String SELECT_ALL_TEAMGAMES = "SELECT * FROM teamGame";
        List<TeamGame> teamGames = jdbc.query(SELECT_ALL_TEAMGAMES, new TeamGameMapper());
        associateTeam(teamGames);
        return teamGames;
    }
    
    @Override
    public List<TeamGame> getAllGamesByTeam(int teamId) {
        final String SELECT_ALL_TEAMGAMES_BY_TEAM = "SELECT * FROM teamGame WHERE teamId = ?";
        List<TeamGame> teamGamesByTeam = jdbc.query(SELECT_ALL_TEAMGAMES_BY_TEAM, new TeamGameMapper(), teamId);
        associateTeam(teamGamesByTeam);
        return teamGamesByTeam;
    }
    
    private void associateTeam(List<TeamGame> teamGames) {
        for (TeamGame teamGame : teamGames) {
            teamGame.setTeam(getTeamForTeamGame(teamGame.getTeamGameId()));
        }
    }
    
    @Override
    public void updateTeamGame(TeamGame teamGame) {
        final String UPDATE_TEAMGAME = "UPDATE teamGame SET gameDate = ?, gameLocation = ?, opponent = ?, teamScore = ?, opponentScore = ?, result = ? WHERE teamGameId = ?";
        jdbc.update(UPDATE_TEAMGAME, teamGame.getGameDate(), teamGame.getGameLocation(), teamGame.getOpponent(), teamGame.getTeamScore(), teamGame.getOpponentScore(), teamGame.getResult(), teamGame.getTeamGameId());
        
    }
    
    @Override
    public void deleteTeamGame(int id) {
        final String DELETE_TEAMGAME = "DELETE FROM teamGame WHERE teamGameId = ?";
        jdbc.update(DELETE_TEAMGAME, id);
    }
    
    @Override
    @Transactional
    public TeamGame createTeamGame(TeamGame teamGame) {
        final String INSERT_TEAMGAME = "INSERT INTO teamGame(gameDate, gameLocation, opponent, teamScore, opponentScore, result, teamId) VALUES(?,?,?,?,?,?,?)";
        jdbc.update(INSERT_TEAMGAME, teamGame.getGameDate(), teamGame.getGameLocation(), teamGame.getOpponent(), teamGame.getTeamScore(), teamGame.getOpponentScore(), teamGame.getResult(), teamGame.getTeam().getTeamId());
        int newId = jdbc.queryForObject("select LAST_INSERT_ID()", Integer.class);
        teamGame.setTeamGameId(newId);
        return teamGame;
    }
    
    public static final class TeamGameMapper implements RowMapper<TeamGame> {
        
        @Override
        public TeamGame mapRow(ResultSet rs, int i) throws SQLException {
            TeamGame teamGame = new TeamGame();
            teamGame.setTeamGameId(rs.getInt("teamGameId"));
            teamGame.setGameDate(LocalDate.parse(rs.getString("gameDate")));
            teamGame.setGameLocation(rs.getString("gameLocation"));
            teamGame.setOpponent(rs.getString("opponent"));
            teamGame.setTeamScore(rs.getInt("teamScore"));
            teamGame.setOpponentScore(rs.getInt("opponentScore"));
            teamGame.setResult(rs.getString("result"));
            
            return teamGame;
        }
    }
    
}
