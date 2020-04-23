/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.BestXI.Dao;

import com.mbuettner.BestXI.Dao.PlayerDaoDB.PlayerMapper;
import com.mbuettner.BestXI.Entities.Player;
import com.mbuettner.BestXI.Entities.PlayerGame;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author mbuet
 */
@Repository
public class PlayerGameDaoDB implements playerGameDao {

    @Autowired
    JdbcTemplate jdbc;

    @Autowired
    playerDao playerDao;

    @Override
    public PlayerGame getPlayerGameById(int id) {
        try {
            final String SELECT_PLAYERGAME_BY_ID = "SELECT * FROM playerGame WHERE playerGameId = ?";
            PlayerGame pg = jdbc.queryForObject(SELECT_PLAYERGAME_BY_ID, new PlayerGameMapper(), id);
            pg.setPlayer(getPlayerForPlayerGame(id));
            return pg;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    private Player getPlayerForPlayerGame(int playerGameId) {
        final String SELECT_PLAYER_FOR_PLAYERGAME = "SELECT player.* FROM player JOIN playerGame ON player.playerId = playerGame.playerId WHERE playerGameId = ?";
        Player player = jdbc.queryForObject(SELECT_PLAYER_FOR_PLAYERGAME, new PlayerMapper(), playerGameId);
        return playerDao.getPlayerById(player.getPlayerId());
    }

    @Override
    public List<PlayerGame> getAllPlayerGames() {
        final String SELECT_ALL_PLAYERGAMES = "SELECT * FROM playerGame";
        List<PlayerGame> playerGames = jdbc.query(SELECT_ALL_PLAYERGAMES, new PlayerGameMapper());
        associatePlayers(playerGames);
        return playerGames;
    }

    @Override
    public List<PlayerGame> getAllPlayerGamesByPlayer(int playerId) {
        final String SELECT_ALL_PLAYERGAMES_BY_PLAYER = "SELECT * FROM playerGame WHERE playerId = ?";
        List<PlayerGame> playerGames = jdbc.query(SELECT_ALL_PLAYERGAMES_BY_PLAYER, new PlayerGameMapper(), playerId);
        associatePlayers(playerGames);
        return playerGames;
    }

    private void associatePlayers(List<PlayerGame> playerGames) {
        for (PlayerGame playerGame : playerGames) {
            playerGame.setPlayer(getPlayerForPlayerGame(playerGame.getPlayerGameId()));
        }
    }

    @Override
    public void updatePlayerGame(PlayerGame pg) {
        final String UPDATE_PLAYERGAME = "UPDATE playerGame SET shots = ?, goals = ?, assists = ?, dribbles = ?, passes = ?, passPercentage = ?, tackles = ?, interceptions = ?, shotsDefensed = ?, shotsSaved = ?, goalsAllowed = ? WHERE playerGameId = ?";
        jdbc.update(UPDATE_PLAYERGAME, pg.getShots(), pg.getGoals(), pg.getAssists(), pg.getDribbles(), pg.getPasses(), pg.getPassPercentage(), pg.getTackles(), pg.getInterceptions(), pg.getShotsDefensed(), pg.getShotsSaved(), pg.getGoalsAllowed(), pg.getPlayerGameId());
    }

    @Override
    public void deletePlayerGame(int id) {
        final String DELETE_PLAYERGAME = "DELETE FROM playerGame WHERE playerGameId = ?";
        jdbc.update(DELETE_PLAYERGAME, id);
    }

    @Override
    @Transactional
    public PlayerGame createPlayerGame(PlayerGame pg) {
        final String INSERT_PLAYERGAME = "insert into playerGame(shots, goals, assists, dribbles, passes, passPercentage, tackles, interceptions, shotsDefensed, shotsSaved, goalsAllowed, playerId) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        jdbc.update(INSERT_PLAYERGAME, pg.getShots(), pg.getGoals(), pg.getAssists(), pg.getDribbles(), pg.getPasses(), pg.getPassPercentage(), pg.getTackles(), pg.getInterceptions(), pg.getShotsDefensed(), pg.getShotsSaved(), pg.getGoalsAllowed(), pg.getPlayer().getPlayerId());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        pg.setPlayerGameId(newId);

        return pg;
    }

    public static final class PlayerGameMapper implements RowMapper<PlayerGame> {

        @Override
        public PlayerGame mapRow(ResultSet rs, int i) throws SQLException {
            PlayerGame pg = new PlayerGame();
            pg.setPlayerGameId(rs.getInt("playerGameId"));
            pg.setShots(rs.getInt("shots"));
            pg.setGoals(rs.getInt("goals"));
            pg.setAssists(rs.getInt("assists"));
            pg.setDribbles(rs.getInt("dribbles"));
            pg.setPasses(rs.getInt("passes"));
            pg.setPassPercentage(rs.getInt("passPercentage"));
            pg.setTackles(rs.getInt("tackles"));
            pg.setInterceptions(rs.getInt("interceptions"));
            pg.setShotsDefensed(rs.getInt("shotsDefensed"));
            pg.setShotsSaved(rs.getInt("shotsSaved"));
            pg.setGoalsAllowed(rs.getInt("goalsAllowed"));

            return pg;
        }
    }

}
