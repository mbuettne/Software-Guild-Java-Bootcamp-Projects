/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.m7sumnumberguess.Dao;

import com.mbuettner.m7sumnumberguess.DTO.Round;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author mbuet
 */
@Repository
public class RoundDaoImpl implements RoundDao {

    @Autowired
    JdbcTemplate jdbc;

    public static final class roundMapper implements RowMapper<Round> {

        @Override
        public Round mapRow(ResultSet rs, int index) throws SQLException {
            Round round = new Round();
            round.setRoundsId(rs.getInt("roundsId"));
            round.setGuess(rs.getString("guess"));
            round.setTimestamp(rs.getString("time"));
            round.setResult(rs.getString("result"));

            return round;
        }

    }

    @Override
    @Transactional
    public Round addRound(Round round) {
        final String INSERT_ROUND = "INSERT INTO Rounds(gameId, guess, time, result) VALUES(?, ?, ?, ?);";
        jdbc.update(INSERT_ROUND, round.getGameId(), round.getGuess(), round.getTimestamp(), round.getResult());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        round.setRoundsId(newId);
        return round;
    }

    @Override
    public List<Round> getAllRoundsByGame(int gameId) {
        final String GET_ALL_ROUNDS_BY_GAME = "SELECT r.guess Guess, r.time Time, r.result Result FROM "
                + " Game g INNER JOIN Rounds r ON g.gameId = r.gameId WHERE g.gameId = ? ORDER BY Time DESC;";
        List<Round> rounds = jdbc.query(GET_ALL_ROUNDS_BY_GAME, new roundMapper(), gameId);
        return rounds;
    }

    @Override
    public Round getLatestRound(int gameId) {
        final String GET_LATEST_ROUND = "SELECT r.guess Guess, r.time Time, r.result Result FROM Game g "
                + "INNER JOIN Rounds r ON g.gameId = r.gameId WHERE g.gameId = ? ORDER BY r.time DESC LIMIT 0, 1;";
        Round latest = jdbc.queryForObject(GET_LATEST_ROUND, new roundMapper(), gameId);
        return latest;
    }

}
