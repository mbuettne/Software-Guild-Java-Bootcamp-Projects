/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.BestXI.Dao;

import com.mbuettner.BestXI.Dao.TeamDaoDB.TeamMapper;
import com.mbuettner.BestXI.Entities.Player;
import com.mbuettner.BestXI.Entities.Team;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class PlayerDaoDB implements playerDao {

    @Autowired
    TeamDaoDB teamDao;

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Player getPlayerById(int id) {
        try {
            final String SELECT_PLAYER_BY_ID = "SELECT * FROM player WHERE playerId = ?";
            Player player = jdbc.queryForObject(SELECT_PLAYER_BY_ID, new PlayerMapper(), id);
            player.setTeam(getTeamForPlayer(id));
            return player;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public Player getPlayerByLastName(String PlayerLastName) {
        try {
            final String SELECT_PLAYER_BY_LASTNAME = "SELECT * FROM player WHERE lastName = ?";
            Player player = jdbc.queryForObject(SELECT_PLAYER_BY_LASTNAME, new PlayerMapper(), PlayerLastName);
            player.setTeam(getTeamForPlayer(player.getPlayerId()));
            return player;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    private Team getTeamForPlayer(int playerId) {
        final String SELECT_TEAM_FOR_PLAYER = "SELECT team.* FROM team JOIN player ON team.teamId = player.teamId WHERE playerId = ?";
        return jdbc.queryForObject(SELECT_TEAM_FOR_PLAYER, new TeamMapper(), playerId);
    }

    @Override
    public List<Player> getAllPlayers() {
        final String SELECT_ALL_PLAYERS = "SELECT * FROM player";
        List<Player> players = jdbc.query(SELECT_ALL_PLAYERS, new PlayerMapper());
        associateTeam(players);
        return players;
    }

    @Override
    public List<Player> getAllPlayersByTeam(int teamId) {
        final String SELECT_ALL_PLAYERS_BY_TEAM = "SELECT * FROM player WHERE teamId = ?";
        List<Player> players = jdbc.query(SELECT_ALL_PLAYERS_BY_TEAM, new PlayerMapper(), teamId);
        associateTeam(players);
        return players;
    }

    private void associateTeam(List<Player> players) {
        for (Player player : players) {
            player.setTeam(getTeamForPlayer(player.getPlayerId()));
        }
    }

    @Override
    public void updatePlayer(Player player) {
        final String UPDATE_PLAYER = "UPDATE player SET firstName = ?, lastName = ?, height = ?, weight = ?, playerPosition = ?, dominantFoot = ? WHERE playerId = ?";
        jdbc.update(UPDATE_PLAYER, player.getFirstName(), player.getLastName(), player.getHeight(), player.getWeight(), player.getPlayerPosition(), player.getDominantFoot(), player.getPlayerId());
    }

    @Override
    public void deletePlayer(int id) {
        final String DELETE_PLAYER = "DELETE FROM player WHERE playerId = ?";
        jdbc.update(DELETE_PLAYER, id);
    }

    @Override
    @Transactional
    public Player createPlayer(Player player) {
        final String INSERT_PLAYER = "INSERT INTO player(firstName, lastName, height, weight, playerPosition, dominantFoot, teamId) VALUES(?, ?, ?, ?, ?, ?, ?)";
        jdbc.update(INSERT_PLAYER, player.getFirstName(), player.getLastName(), player.getHeight(), player.getWeight(), player.getPlayerPosition(), player.getDominantFoot(), player.getTeam().getTeamId());
        int newId = jdbc.queryForObject("select LAST_INSERT_ID()", Integer.class);
        player.setPlayerId(newId);

        return player;
    }

    public static final class PlayerMapper implements RowMapper<Player> {

        @Override
        public Player mapRow(ResultSet rs, int i) throws SQLException {
            Player player = new Player();
            player.setPlayerId(rs.getInt("playerId"));
            player.setFirstName(rs.getString("firstName"));
            player.setLastName(rs.getString("lastName"));
            player.setHeight(rs.getInt("height"));
            player.setWeight(rs.getInt("weight"));
            player.setPlayerPosition(rs.getString("playerPosition"));
            player.setDominantFoot(rs.getString("dominantFoot"));

            return player;
        }
    }

}
