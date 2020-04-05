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
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author mbuet
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class RoundDaoTest {

    @Autowired
    RoundDao roundDao;

    @Autowired
    NumberGuessDao dao;

    public RoundDaoTest() {
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
     * Test of addRound and getLatestRound methods, of class RoundDao.
     */
    @Test
    public void testAddRound() {
        Round round = new Round();
        round.setGameId(1);
        round.setGuess("2345");
        round.setResult("e:0:p:2");
        round.setTimestamp(LocalDateTime.now());
        round = roundDao.addRound(round);

        Round fromDao = roundDao.getLatestRound(1);

        assertEquals(round, fromDao);
    }

    /**
     * Test of getAllRoundsByGame method, of class RoundDao.
     */
    @Test
    public void testGetAllRoundsByGame() {
        Round round1 = new Round();
        round1.setGameId(2);
        round1.setGuess("1111");
        round1.setResult("e:0:p:1");
        round1.setTimestamp(LocalDateTime.now());
        roundDao.addRound(round1);

        Round round2 = new Round();
        round2.setGameId(2);
        round2.setGuess("2222");
        round2.setResult("e:1:p:0");
        round2.setTimestamp(LocalDateTime.now());
        roundDao.addRound(round2);
        
        List<Round> rounds = roundDao.getAllRoundsByGame(2);
        
        assertEquals(2, rounds.size());
        assertTrue(rounds.contains(round1));
        assertTrue(rounds.contains(round2));
    }

}
