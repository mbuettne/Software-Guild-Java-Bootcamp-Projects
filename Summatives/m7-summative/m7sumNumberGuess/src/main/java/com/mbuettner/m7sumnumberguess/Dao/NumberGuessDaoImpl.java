/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.m7sumnumberguess.Dao;

import com.mbuettner.m7sumnumberguess.DTO.Game;
import com.mbuettner.m7sumnumberguess.DTO.Round;
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

    private final JdbcTemplate jdbc;

    @Autowired
    public NumberGuessDaoImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

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
        final String GET_ALL_GAMES = "SELECT * FROM Game;";
        return jdbc.query(GET_ALL_GAMES, new gameMapper());
    }

    @Override
    public Game getGameById(int id) {
        try {
            final String GET_GAME_BY_ID = "SELECT * FROM Game WHERE gameId = ?;";
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
    public void updateGame(Game game) {
        final String UPDATE_GAME = "UPDATE game SET progress = ?, answer = ? WHERE gameId = ?";
        jdbc.update(UPDATE_GAME, game.getProgress(), game.getAnswer(), game.getGameId());
    }

    public static final class roundMapper implements RowMapper<Round> {

        @Override
        public Round mapRow(ResultSet rs, int index) throws SQLException {
            Round round = new Round();
            round.setRoundsId(rs.getInt("roundsId"));
            round.setGameId(rs.getInt("gameId"));
            round.setGuess(rs.getString("guess"));
            round.setTimestamp(rs.getString("time"));
            round.setResult(rs.getString("result"));

            return round;
        }

    }

    @Override
    @Transactional
    public Round addRound(Round round) {
        final String INSERT_ROUND = "INSERT INTO Rounds(gameId, guess, time, result) VALUES(?, ?, ?, ?)";
        jdbc.update(INSERT_ROUND, round.getGameId(), round.getGuess(), round.getTimestamp(), round.getResult());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        round.setRoundsId(newId);

        return round;
    }
    
      @Override
    public void updateRound(Round round) {
        final String UPDATE_ROUND = "UPDATE Rounds SET gameId = ?, guess = ?, time = ?, result = ? WHERE roundsId = ?";
        jdbc.update(UPDATE_ROUND, round.getGameId(), round.getGuess(), round.getTimestamp(), round.getResult(), round.getRoundsId());
    }

    @Override
    public List<Round> getAllRoundsByGame(int gameId) {
        final String GET_ALL_ROUNDS_BY_GAME = "SELECT * FROM Rounds "
                + "INNER JOIN Game ON Rounds.gameId = Game.gameId WHERE Rounds.gameId = ? ORDER BY Rounds.roundsId DESC;";
        List<Round> rounds = jdbc.query(GET_ALL_ROUNDS_BY_GAME, new roundMapper(), gameId);
        return rounds;
    }

    @Override
    public Round getLatestRound(int gameId) {
        final String GET_LATEST_ROUND = "SELECT * FROM Rounds "
                + "INNER JOIN Game ON Rounds.gameId = Game.gameId WHERE Rounds.gameId = ? ORDER BY Rounds.roundsId DESC LIMIT 0, 1;";
        Round latest = jdbc.queryForObject(GET_LATEST_ROUND, new roundMapper(), gameId);
        return latest;
    }

    @Override
    @Transactional
    public void deleteRound(int gameId) {
        final String DELETE_ROUND = "DELETE FROM Rounds "
                + "WHERE Rounds.gameId = ?";
        jdbc.update(DELETE_ROUND, gameId);
    }

    @Override
    @Transactional
    public void deleteGame(int gameId) {
     final String DELETE_GAME = "DELETE FROM Game "
                + "WHERE Game.gameId = ?";
        jdbc.update(DELETE_GAME, gameId);
    }
}
