/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.BestXI.Dao;

import com.mbuettner.BestXI.Entities.Player;
import com.mbuettner.BestXI.Entities.PlayerGame;
import com.mbuettner.BestXI.Entities.Role;
import com.mbuettner.BestXI.Entities.Team;
import com.mbuettner.BestXI.Entities.TeamGame;
import com.mbuettner.BestXI.Entities.User;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author mbuet
 */
@SpringBootTest
public class TeamGameDaoDBTest {

    @Autowired
    userDao userDao;

    @Autowired
    roleDao roleDao;

    @Autowired
    teamDao teamDao;

    @Autowired
    teamGameDao teamGameDao;

    @Autowired
    playerDao playerDao;

    @Autowired
    playerGameDao playerGameDao;

    public TeamGameDaoDBTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {

        List<User> users = userDao.getAllUsers();
        for (User user : users) {
            userDao.deleteUser(user.getUserId());
        }

        List<Role> roles = roleDao.getAllRoles();
        for (Role role : roles) {
            roleDao.deleteRole(role.getRoleId());
        }

        List<TeamGame> teamGames = teamGameDao.getAllTeamGames();
        for (TeamGame teamGame : teamGames) {
            teamGameDao.deleteTeamGame(teamGame.getTeamGameId());
        }

        List<PlayerGame> playerGames = playerGameDao.getAllPlayerGames();
        for (PlayerGame playerGame : playerGames) {
            playerGameDao.deletePlayerGame(playerGame.getPlayerGameId());
        }

        List<Player> players = playerDao.getAllPlayers();
        for (Player player : players) {
            playerDao.deletePlayer(player.getPlayerId());
        }

        List<Team> teams = teamDao.getAllTeams();
        for (Team team : teams) {
            teamDao.deleteTeam(team.getTeamId());
        }
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getTeamGameById method, of class TeamGameDaoDB.
     */
    @Test
    public void testCreateAndGetTeamGameById() {
        Team team = new Team();
        team.setCoachName("Coach");
        team.setTeamName("team");
        team = teamDao.createTeam(team);

        TeamGame tg = new TeamGame();
        tg.setGameDate(LocalDate.now());
        tg.setGameLocation("Home");
        tg.setOpponent("Opponent");
        tg.setTeamScore(2);
        tg.setOpponentScore(1);
        tg.setResult("Win");
        tg.setTeam(team);

        tg = teamGameDao.createTeamGame(tg);

        TeamGame fromDao = teamGameDao.getTeamGameById(tg.getTeamGameId());

        assertEquals(tg, fromDao);
    }

    /**
     * Test of getAllTeamGames method, of class TeamGameDaoDB.
     */
    @Test
    public void testGetAllTeamGamesSize() {
        Team team = new Team();
        team.setCoachName("Coach");
        team.setTeamName("team");
        team = teamDao.createTeam(team);

        TeamGame tg = new TeamGame();
        tg.setGameDate(LocalDate.now());
        tg.setGameLocation("Home");
        tg.setOpponent("Opponent");
        tg.setTeamScore(2);
        tg.setOpponentScore(1);
        tg.setResult("Win");
        tg.setTeam(team);
        tg = teamGameDao.createTeamGame(tg);

        TeamGame tg2 = new TeamGame();
        tg2.setGameDate(LocalDate.now());
        tg2.setGameLocation("Away");
        tg2.setOpponent("Opponent");
        tg2.setTeamScore(2);
        tg2.setOpponentScore(1);
        tg2.setResult("Win");
        tg2.setTeam(team);
        tg2 = teamGameDao.createTeamGame(tg2);

        List<TeamGame> teamGames = teamGameDao.getAllTeamGames();

        assertEquals(2, teamGames.size());
    }

    @Test
    public void testGetAllTeamGamesContains() {
        Team team = new Team();
        team.setCoachName("Coach");
        team.setTeamName("team");
        team = teamDao.createTeam(team);

        TeamGame tg = new TeamGame();
        tg.setGameDate(LocalDate.now());
        tg.setGameLocation("Home");
        tg.setOpponent("Opponent");
        tg.setTeamScore(2);
        tg.setOpponentScore(1);
        tg.setResult("Win");
        tg.setTeam(team);
        tg = teamGameDao.createTeamGame(tg);

        TeamGame tg2 = new TeamGame();
        tg2.setGameDate(LocalDate.now());
        tg2.setGameLocation("Away");
        tg2.setOpponent("Opponent");
        tg2.setTeamScore(2);
        tg2.setOpponentScore(1);
        tg2.setResult("Win");
        tg2.setTeam(team);
        tg2 = teamGameDao.createTeamGame(tg2);

        List<TeamGame> teamGames = teamGameDao.getAllTeamGames();

        assertTrue(teamGames.contains(tg2));
    }

    /**
     * Test of getAllGamesByTeam method, of class TeamGameDaoDB.
     */
    @Test
    public void testGetAllGamesByTeamSize() {
        Team team = new Team();
        team.setCoachName("Coach");
        team.setTeamName("team");
        team = teamDao.createTeam(team);

        TeamGame tg = new TeamGame();
        tg.setGameDate(LocalDate.now());
        tg.setGameLocation("Home");
        tg.setOpponent("Opponent");
        tg.setTeamScore(2);
        tg.setOpponentScore(1);
        tg.setResult("Win");
        tg.setTeam(team);
        tg = teamGameDao.createTeamGame(tg);

        TeamGame tg2 = new TeamGame();
        tg2.setGameDate(LocalDate.now());
        tg2.setGameLocation("Away");
        tg2.setOpponent("Opponent");
        tg2.setTeamScore(2);
        tg2.setOpponentScore(1);
        tg2.setResult("Win");
        tg2.setTeam(team);
        tg2 = teamGameDao.createTeamGame(tg2);

        List<TeamGame> teamGames = teamGameDao.getAllGamesByTeam(team.getTeamId());

        assertEquals(2, teamGames.size());
    }

    @Test
    public void testGetAllGamesByTeamContains() {
        Team team = new Team();
        team.setCoachName("Coach");
        team.setTeamName("team");
        team = teamDao.createTeam(team);

        TeamGame tg = new TeamGame();
        tg.setGameDate(LocalDate.now());
        tg.setGameLocation("Home");
        tg.setOpponent("Opponent");
        tg.setTeamScore(2);
        tg.setOpponentScore(1);
        tg.setResult("Win");
        tg.setTeam(team);
        tg = teamGameDao.createTeamGame(tg);

        TeamGame tg2 = new TeamGame();
        tg2.setGameDate(LocalDate.now());
        tg2.setGameLocation("Away");
        tg2.setOpponent("Opponent");
        tg2.setTeamScore(2);
        tg2.setOpponentScore(1);
        tg2.setResult("Win");
        tg2.setTeam(team);
        tg2 = teamGameDao.createTeamGame(tg2);

        List<TeamGame> teamGames = teamGameDao.getAllGamesByTeam(team.getTeamId());

        assertTrue(teamGames.contains(tg));
    }

    /**
     * Test of updateTeamGame method, of class TeamGameDaoDB.
     */
    @Test
    public void testUpdateTeamGameChanges() {
        Team team = new Team();
        team.setCoachName("Coach");
        team.setTeamName("team");
        team = teamDao.createTeam(team);

        TeamGame tg = new TeamGame();
        tg.setGameDate(LocalDate.now());
        tg.setGameLocation("Home");
        tg.setOpponent("Opponent");
        tg.setTeamScore(2);
        tg.setOpponentScore(1);
        tg.setResult("Win");
        tg.setTeam(team);
        tg = teamGameDao.createTeamGame(tg);

        TeamGame fromDao = teamGameDao.getTeamGameById(tg.getTeamGameId());

        tg.setGameLocation("Neutral");
        teamGameDao.updateTeamGame(tg);

        assertNotEquals(tg, fromDao);
    }

    @Test
    public void testUpdateTeamGameReturns() {
        Team team = new Team();
        team.setCoachName("Coach");
        team.setTeamName("team");
        team = teamDao.createTeam(team);

        TeamGame tg = new TeamGame();
        tg.setGameDate(LocalDate.now());
        tg.setGameLocation("Home");
        tg.setOpponent("Opponent");
        tg.setTeamScore(2);
        tg.setOpponentScore(1);
        tg.setResult("Win");
        tg.setTeam(team);
        tg = teamGameDao.createTeamGame(tg);

        TeamGame fromDao = teamGameDao.getTeamGameById(tg.getTeamGameId());

        tg.setGameLocation("Neutral");
        teamGameDao.updateTeamGame(tg);

        fromDao = teamGameDao.getTeamGameById(tg.getTeamGameId());

        assertEquals(tg, fromDao);
    }

    /**
     * Test of deleteTeamGame method, of class TeamGameDaoDB.
     */
    @Test
    public void testDeleteTeamGame() {
        Team team = new Team();
        team.setCoachName("Coach");
        team.setTeamName("team");
        team = teamDao.createTeam(team);

        TeamGame tg = new TeamGame();
        tg.setGameDate(LocalDate.now());
        tg.setGameLocation("Home");
        tg.setOpponent("Opponent");
        tg.setTeamScore(2);
        tg.setOpponentScore(1);
        tg.setResult("Win");
        tg.setTeam(team);
        tg = teamGameDao.createTeamGame(tg);

        teamGameDao.deleteTeamGame(tg.getTeamGameId());

        TeamGame fromDao = teamGameDao.getTeamGameById(tg.getTeamGameId());

        assertNull(fromDao);
    }

}
