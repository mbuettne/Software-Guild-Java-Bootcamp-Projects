/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.m7sumnumberguess.Dao;

import com.mbuettner.m7sumnumberguess.DTO.Game;
import com.mbuettner.m7sumnumberguess.DTO.Round;
import com.mbuettner.m7sumnumberguess.TestApplicationConfiguration;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author mbuet
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class NumberGuessDaoTest {

    @Autowired
    NumberGuessDao dao;

    JdbcTemplate jdbc;

    public NumberGuessDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        List<Game> games = dao.getAllGames();
        for (Game game : games) {
            List<Round> rounds = dao.getAllRoundsByGame(game.getGameId());
            for (Round round : rounds) {
                dao.deleteRound(round.getGameId());
            }
            dao.deleteGame(game.getGameId());
        }
    }

    @After
    public void tearDown() {

    }

    /**
     * Test of createGame and getGameById methods, of class NumberGuessDao.
     */
    @Test
    public void testCreateGetGame() {
        Game game = dao.createGame("2938");

        Game fromDao = dao.getGameById(game.getGameId());

        assertEquals(game, fromDao);
    }

    /**
     * Test of getAllGames method, of class NumberGuessDao.
     */
    @Test
    public void testGetAllGames() {
        Game game1 = new Game();
        game1 = dao.createGame("1111");

        Game game2 = new Game();
        game2 = dao.createGame("2222");

        List<Game> games = dao.getAllGames();

        assertEquals(2, games.size());
        assertTrue(games.contains(game1));
        assertTrue(games.contains(game2));
    }

    /**
     * Test of updateGame method, of class NumberGuessDao.
     */
    @Test
    public void testUpdateGame() {
        Game game = new Game();
        game = dao.createGame("2048");

        Game fromDao = dao.getGameById(game.getGameId());
        assertEquals(game, fromDao);

        game.setProgress("Finished");
        dao.updateGame(game);
        assertNotEquals(game, fromDao);

        fromDao = dao.getGameById(game.getGameId());
        assertEquals(game, fromDao);

    }

    @Test
    public void testAddRound() {
        Game game = new Game();
        game = dao.createGame("1867");

        Round round = new Round();
        round.setGameId(game.getGameId());
        round.setGuess("1345");
        round.setResult("e:1:p:0");
        round.setTimestamp(LocalDateTime.now());
        round = dao.addRound(round);

        Round fromDao = dao.getLatestRound(game.getGameId());

        assertEquals(round, fromDao);
    }

    /**
     * Test of getAllRoundsByGame method, of class RoundDao.
     */
    @Test
    public void testGetAllRoundsByGame() {

        Game game = new Game();
        game = dao.createGame("1267");

        Round round1 = new Round();
        round1.setGameId(game.getGameId());
        round1.setGuess("1111");
        round1.setResult("e:1:p:0");
        round1.setTimestamp(LocalDateTime.now());
        dao.addRound(round1);

        Round round2 = new Round();
        round2.setGameId(game.getGameId());
        round2.setGuess("2222");
        round2.setResult("e:1:p:0");
        round2.setTimestamp(LocalDateTime.now());
        dao.addRound(round2);

        List<Round> rounds = dao.getAllRoundsByGame(game.getGameId());

        assertEquals(2, rounds.size());
        assertTrue(rounds.contains(round1));
        assertTrue(rounds.contains(round2));
    }

}
