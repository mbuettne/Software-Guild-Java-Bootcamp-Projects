/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.m7sumnumberguess.Dao;

import com.mbuettner.m7sumnumberguess.DTO.Game;
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
public class NumberGuessDaoImpl implements NumberGuessDao {

    @Autowired
    JdbcTemplate jdbc;

    public static final class gameMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet rs, int index) throws SQLException {
            Game game = new Game();
            game.setGameId(rs.getInt("gameId"));
            game.setProgress(rs.getString("progress"));
            game.setAnswer(rs.getString("answer"));
            return game;
        }
    }

    @Override
    public List<Game> getAllGames() {
        final String GET_ALL_GAMES = "SELECT gameId, progress FROM Game;";
        return jdbc.query(GET_ALL_GAMES, new gameMapper());
    }

    @Override
    public Game getGameById(int id) {
        try {
            final String GET_GAME_BY_ID = "SELECT gameId, progress, answer FROM Game WHERE gameId = ?;";
            Game game = jdbc.queryForObject(GET_GAME_BY_ID, new gameMapper(), id);
            return game;
        } catch (DataAccessException ex) {
            return null;
        }
        
    }

    @Override
    @Transactional
    public Game createGame(String answer) {

        Game newGame = new Game(answer);
        newGame.setProgress("In Progress");
        
        final String INSERT_GAME = "INSERT INTO Game(progress, answer) VALUES(?,?)";
        jdbc.update(INSERT_GAME, newGame.getProgress(), newGame.getAnswer());
        
        int newGameId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        newGame.setGameId(newGameId);

        return newGame;
    }
    
    @Override
    public void updateGame(Game game){
        final String UPDATE_GAME = "UPDATE game SET progress = ?, answer = ?, WHERE gameId = ?";
        jdbc.update(UPDATE_GAME, game.getProgress(), game.getAnswer(), game.getGameId());
    }

}
