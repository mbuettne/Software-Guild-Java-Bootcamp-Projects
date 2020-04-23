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
public class PlayerGameDaoDBTest {

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

    public PlayerGameDaoDBTest() {
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
     * Test of getPlayerGameById method, of class PlayerGameDaoDB.
     */
    @Test
    public void testCreateAndGetPlayerGameById() {
        Team team = new Team();
        team.setCoachName("Coach");
        team.setTeamName("Team");
        team = teamDao.createTeam(team);

        Player player = new Player();
        player.setFirstName("first");
        player.setLastName("last");
        player.setHeight(65);
        player.setWeight(134);
        player.setPlayerPosition("Forward");
        player.setDominantFoot("Right");
        player.setTeam(team);
        player = playerDao.createPlayer(player);

        PlayerGame pg = new PlayerGame();
        pg.setShots(1);
        pg.setGoals(1);
        pg.setAssists(1);
        pg.setDribbles(1);
        pg.setPasses(1);
        pg.setPassPercentage(1);
        pg.setTackles(2);
        pg.setInterceptions(1);
        pg.setShotsDefensed(0);
        pg.setShotsSaved(0);
        pg.setGoalsAllowed(0);
        pg.setPlayer(player);

        pg = playerGameDao.createPlayerGame(pg);

        PlayerGame fromDao = playerGameDao.getPlayerGameById(pg.getPlayerGameId());

        assertEquals(pg, fromDao);

    }

    /**
     * Test of getAllPlayerGames method, of class PlayerGameDaoDB.
     */
    @Test
    public void testGetAllPlayerGamesSize() {
        Team team = new Team();
        team.setCoachName("Coach");
        team.setTeamName("Team");
        team = teamDao.createTeam(team);

        Player player = new Player();
        player.setFirstName("first");
        player.setLastName("last");
        player.setHeight(65);
        player.setWeight(134);
        player.setPlayerPosition("Forward");
        player.setDominantFoot("Right");
        player.setTeam(team);
        player = playerDao.createPlayer(player);

        PlayerGame pg = new PlayerGame();
        pg.setShots(1);
        pg.setGoals(1);
        pg.setAssists(1);
        pg.setDribbles(1);
        pg.setPasses(1);
        pg.setPassPercentage(1);
        pg.setTackles(2);
        pg.setInterceptions(1);
        pg.setShotsDefensed(0);
        pg.setShotsSaved(0);
        pg.setGoalsAllowed(0);
        pg.setPlayer(player);
        pg = playerGameDao.createPlayerGame(pg);

        PlayerGame pg2 = new PlayerGame();
        pg2.setShots(1);
        pg2.setGoals(1);
        pg2.setAssists(1);
        pg2.setDribbles(1);
        pg2.setPasses(1);
        pg2.setPassPercentage(1);
        pg2.setTackles(2);
        pg2.setInterceptions(1);
        pg2.setShotsDefensed(0);
        pg2.setShotsSaved(0);
        pg2.setGoalsAllowed(0);
        pg2.setPlayer(player);
        pg2 = playerGameDao.createPlayerGame(pg2);

        List<PlayerGame> playerGames = playerGameDao.getAllPlayerGames();

        assertEquals(2, playerGames.size());
    }

    @Test
    public void testGetAllPlayerGamesContains() {
        Team team = new Team();
        team.setCoachName("Coach");
        team.setTeamName("Team");
        team = teamDao.createTeam(team);

        Player player = new Player();
        player.setFirstName("first");
        player.setLastName("last");
        player.setHeight(65);
        player.setWeight(134);
        player.setPlayerPosition("Forward");
        player.setDominantFoot("Right");
        player.setTeam(team);
        player = playerDao.createPlayer(player);

        PlayerGame pg = new PlayerGame();
        pg.setShots(1);
        pg.setGoals(1);
        pg.setAssists(1);
        pg.setDribbles(1);
        pg.setPasses(1);
        pg.setPassPercentage(1);
        pg.setTackles(2);
        pg.setInterceptions(1);
        pg.setShotsDefensed(0);
        pg.setShotsSaved(0);
        pg.setGoalsAllowed(0);
        pg.setPlayer(player);
        pg = playerGameDao.createPlayerGame(pg);

        PlayerGame pg2 = new PlayerGame();
        pg2.setShots(1);
        pg2.setGoals(1);
        pg2.setAssists(1);
        pg2.setDribbles(1);
        pg2.setPasses(1);
        pg2.setPassPercentage(1);
        pg2.setTackles(2);
        pg2.setInterceptions(1);
        pg2.setShotsDefensed(0);
        pg2.setShotsSaved(0);
        pg2.setGoalsAllowed(0);
        pg2.setPlayer(player);
        pg2 = playerGameDao.createPlayerGame(pg2);

        List<PlayerGame> playerGames = playerGameDao.getAllPlayerGames();

        assertTrue(playerGames.contains(pg2));
    }

    /**
     * Test of getAllPlayerGamesByPlayer method, of class PlayerGameDaoDB.
     */
    @Test
    public void testGetAllPlayerGamesByPlayerSize() {
        Team team = new Team();
        team.setCoachName("Coach");
        team.setTeamName("Team");
        team = teamDao.createTeam(team);

        Player player = new Player();
        player.setFirstName("first");
        player.setLastName("last");
        player.setHeight(65);
        player.setWeight(134);
        player.setPlayerPosition("Forward");
        player.setDominantFoot("Right");
        player.setTeam(team);
        player = playerDao.createPlayer(player);

        PlayerGame pg = new PlayerGame();
        pg.setShots(1);
        pg.setGoals(1);
        pg.setAssists(1);
        pg.setDribbles(1);
        pg.setPasses(1);
        pg.setPassPercentage(1);
        pg.setTackles(2);
        pg.setInterceptions(1);
        pg.setShotsDefensed(0);
        pg.setShotsSaved(0);
        pg.setGoalsAllowed(0);
        pg.setPlayer(player);
        pg = playerGameDao.createPlayerGame(pg);

        PlayerGame pg2 = new PlayerGame();
        pg2.setShots(1);
        pg2.setGoals(1);
        pg2.setAssists(1);
        pg2.setDribbles(1);
        pg2.setPasses(1);
        pg2.setPassPercentage(1);
        pg2.setTackles(2);
        pg2.setInterceptions(1);
        pg2.setShotsDefensed(0);
        pg2.setShotsSaved(0);
        pg2.setGoalsAllowed(0);
        pg2.setPlayer(player);
        pg2 = playerGameDao.createPlayerGame(pg2);

        List<PlayerGame> playerGames = playerGameDao.getAllPlayerGamesByPlayer(player.getPlayerId());

        assertEquals(2, playerGames.size());
    }

    @Test
    public void testGetAllPlayerGamesByPlayerContains() {
        Team team = new Team();
        team.setCoachName("Coach");
        team.setTeamName("Team");
        team = teamDao.createTeam(team);

        Player player = new Player();
        player.setFirstName("first");
        player.setLastName("last");
        player.setHeight(65);
        player.setWeight(134);
        player.setPlayerPosition("Forward");
        player.setDominantFoot("Right");
        player.setTeam(team);
        player = playerDao.createPlayer(player);

        PlayerGame pg = new PlayerGame();
        pg.setShots(1);
        pg.setGoals(1);
        pg.setAssists(1);
        pg.setDribbles(1);
        pg.setPasses(1);
        pg.setPassPercentage(1);
        pg.setTackles(2);
        pg.setInterceptions(1);
        pg.setShotsDefensed(0);
        pg.setShotsSaved(0);
        pg.setGoalsAllowed(0);
        pg.setPlayer(player);
        pg = playerGameDao.createPlayerGame(pg);

        PlayerGame pg2 = new PlayerGame();
        pg2.setShots(1);
        pg2.setGoals(1);
        pg2.setAssists(1);
        pg2.setDribbles(1);
        pg2.setPasses(1);
        pg2.setPassPercentage(1);
        pg2.setTackles(2);
        pg2.setInterceptions(1);
        pg2.setShotsDefensed(0);
        pg2.setShotsSaved(0);
        pg2.setGoalsAllowed(0);
        pg2.setPlayer(player);
        pg2 = playerGameDao.createPlayerGame(pg2);

        List<PlayerGame> playerGames = playerGameDao.getAllPlayerGamesByPlayer(player.getPlayerId());

        assertTrue(playerGames.contains(pg2));
    }

    /**
     * Test of updatePlayerGame method, of class PlayerGameDaoDB.
     */
    @Test
    public void testUpdatePlayerGameChanges() {
        Team team = new Team();
        team.setCoachName("Coach");
        team.setTeamName("Team");
        team = teamDao.createTeam(team);

        Player player = new Player();
        player.setFirstName("first");
        player.setLastName("last");
        player.setHeight(65);
        player.setWeight(134);
        player.setPlayerPosition("Forward");
        player.setDominantFoot("Right");
        player.setTeam(team);
        player = playerDao.createPlayer(player);

        PlayerGame pg = new PlayerGame();
        pg.setShots(1);
        pg.setGoals(1);
        pg.setAssists(1);
        pg.setDribbles(1);
        pg.setPasses(1);
        pg.setPassPercentage(1);
        pg.setTackles(2);
        pg.setInterceptions(1);
        pg.setShotsDefensed(0);
        pg.setShotsSaved(0);
        pg.setGoalsAllowed(0);
        pg.setPlayer(player);
        pg = playerGameDao.createPlayerGame(pg);

        PlayerGame fromDao = playerGameDao.getPlayerGameById(pg.getPlayerGameId());

        pg.setGoals(25);
        playerGameDao.updatePlayerGame(pg);

        assertNotEquals(pg, fromDao);
    }

    @Test
    public void testUpdatePlayerGameReturns() {
        Team team = new Team();
        team.setCoachName("Coach");
        team.setTeamName("Team");
        team = teamDao.createTeam(team);

        Player player = new Player();
        player.setFirstName("first");
        player.setLastName("last");
        player.setHeight(65);
        player.setWeight(134);
        player.setPlayerPosition("Forward");
        player.setDominantFoot("Right");
        player.setTeam(team);
        player = playerDao.createPlayer(player);

        PlayerGame pg = new PlayerGame();
        pg.setShots(1);
        pg.setGoals(1);
        pg.setAssists(1);
        pg.setDribbles(1);
        pg.setPasses(1);
        pg.setPassPercentage(1);
        pg.setTackles(2);
        pg.setInterceptions(1);
        pg.setShotsDefensed(0);
        pg.setShotsSaved(0);
        pg.setGoalsAllowed(0);
        pg.setPlayer(player);
        pg = playerGameDao.createPlayerGame(pg);

        PlayerGame fromDao = playerGameDao.getPlayerGameById(pg.getPlayerGameId());

        pg.setGoals(25);
        playerGameDao.updatePlayerGame(pg);

        fromDao = playerGameDao.getPlayerGameById(pg.getPlayerGameId());

        assertEquals(pg, fromDao);
    }

    /**
     * Test of deletePlayerGame method, of class PlayerGameDaoDB.
     */
    @Test
    public void testDeletePlayerGame() {
        Team team = new Team();
        team.setCoachName("Coach");
        team.setTeamName("Team");
        team = teamDao.createTeam(team);

        Player player = new Player();
        player.setFirstName("first");
        player.setLastName("last");
        player.setHeight(65);
        player.setWeight(134);
        player.setPlayerPosition("Forward");
        player.setDominantFoot("Right");
        player.setTeam(team);
        player = playerDao.createPlayer(player);
        
        PlayerGame pg = new PlayerGame();
        pg.setShots(1);
        pg.setGoals(1);
        pg.setAssists(1);
        pg.setDribbles(1);
        pg.setPasses(1);
        pg.setPassPercentage(1);
        pg.setTackles(2);
        pg.setInterceptions(1);
        pg.setShotsDefensed(0);
        pg.setShotsSaved(0);
        pg.setGoalsAllowed(0);
        pg.setPlayer(player);
        pg = playerGameDao.createPlayerGame(pg);

        playerGameDao.deletePlayerGame(pg.getPlayerGameId());

        PlayerGame fromDao = playerGameDao.getPlayerGameById(pg.getPlayerGameId());

        assertNull(fromDao);
    }

}
