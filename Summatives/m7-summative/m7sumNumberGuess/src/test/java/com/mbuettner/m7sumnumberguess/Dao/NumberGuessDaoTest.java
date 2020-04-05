/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.m7sumnumberguess.Dao;

import com.mbuettner.m7sumnumberguess.DTO.Game;
import com.mbuettner.m7sumnumberguess.TestApplicationConfiguration;
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
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author mbuet
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class NumberGuessDaoTest {

    @Autowired
    RoundDao roundDao;

    @Autowired
    NumberGuessDao dao;

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

}
