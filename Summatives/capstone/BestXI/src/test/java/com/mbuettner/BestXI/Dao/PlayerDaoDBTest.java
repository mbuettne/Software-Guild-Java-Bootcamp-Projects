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
public class PlayerDaoDBTest {

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

    public PlayerDaoDBTest() {
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
     * Test of getPlayerById method, of class PlayerDaoDB.
     */
    @Test
    public void testCreateAndGetPlayerById() {
        Team team = new Team();
        team.setCoachName("Coach");
        team.setTeamName("team");
        team = teamDao.createTeam(team);

        Player player = new Player();
        player.setFirstName("firstName");
        player.setLastName("lastName");
        player.setHeight(70);
        player.setWeight(175);
        player.setPlayerPosition("Midfield");
        player.setDominantFoot("Left");
        player.setTeam(teamDao.getTeamById(team.getTeamId()));

        player = playerDao.createPlayer(player);

        Player fromDao = playerDao.getPlayerById(player.getPlayerId());

        assertEquals(player, fromDao);
    }

    /**
     * Test of getPlayerByLastName method, of class PlayerDaoDB.
     */
    @Test
    public void testGetPlayerByLastName() {
        Team team = new Team();
        team.setCoachName("Coach");
        team.setTeamName("team");
        team = teamDao.createTeam(team);

        Player player = new Player();
        player.setFirstName("firstName");
        player.setLastName("lastName");
        player.setHeight(70);
        player.setWeight(175);
        player.setPlayerPosition("Midfield");
        player.setDominantFoot("Left");
        player.setTeam(teamDao.getTeamById(team.getTeamId()));

        player = playerDao.createPlayer(player);

        Player fromDao = playerDao.getPlayerByLastName(player.getLastName());

        assertEquals(player, fromDao);
    }

    /**
     * Test of getAllPlayers method, of class PlayerDaoDB.
     */
    @Test
    public void testGetAllPlayersSize() {
        Team team = new Team();
        team.setCoachName("Coach");
        team.setTeamName("team");
        team = teamDao.createTeam(team);

        Team team2 = new Team();
        team2.setCoachName("Coach2");
        team2.setTeamName("team2");
        team2 = teamDao.createTeam(team2);

        Player player = new Player();
        player.setFirstName("firstName");
        player.setLastName("lastName");
        player.setHeight(70);
        player.setWeight(175);
        player.setPlayerPosition("Midfield");
        player.setDominantFoot("Left");
        player.setTeam(teamDao.getTeamById(team.getTeamId()));
        player = playerDao.createPlayer(player);

        Player player2 = new Player();
        player2.setFirstName("firstName2");
        player2.setLastName("lastName2");
        player2.setHeight(70);
        player2.setWeight(175);
        player2.setPlayerPosition("Defense");
        player2.setDominantFoot("Right");
        player2.setTeam(teamDao.getTeamById(team2.getTeamId()));
        player2 = playerDao.createPlayer(player2);

        List<Player> players = playerDao.getAllPlayers();

        assertEquals(2, players.size());
    }

    @Test
    public void testGetAllPlayersContains() {
        Team team = new Team();
        team.setCoachName("Coach");
        team.setTeamName("team");
        team = teamDao.createTeam(team);

        Team team2 = new Team();
        team2.setCoachName("Coach2");
        team2.setTeamName("team2");
        team2 = teamDao.createTeam(team2);

        Player player = new Player();
        player.setFirstName("firstName");
        player.setLastName("lastName");
        player.setHeight(70);
        player.setWeight(175);
        player.setPlayerPosition("Midfield");
        player.setDominantFoot("Left");
        player.setTeam(teamDao.getTeamById(team.getTeamId()));
        player = playerDao.createPlayer(player);

        Player player2 = new Player();
        player2.setFirstName("firstName2");
        player2.setLastName("lastName2");
        player2.setHeight(70);
        player2.setWeight(175);
        player2.setPlayerPosition("Defense");
        player2.setDominantFoot("Right");
        player2.setTeam(teamDao.getTeamById(team2.getTeamId()));
        player2 = playerDao.createPlayer(player2);

        List<Player> players = playerDao.getAllPlayers();

        assertTrue(players.contains(player2));
    }

    /**
     * Test of getAllPlayersByTeam method, of class PlayerDaoDB.
     */
    @Test
    public void testGetAllPlayersByTeamSize() {
        Team team = new Team();
        team.setCoachName("Coach");
        team.setTeamName("team");
        team = teamDao.createTeam(team);

        Team team2 = new Team();
        team2.setCoachName("Coach2");
        team2.setTeamName("team2");
        team2 = teamDao.createTeam(team2);

        Player player = new Player();
        player.setFirstName("firstName");
        player.setLastName("lastName");
        player.setHeight(70);
        player.setWeight(175);
        player.setPlayerPosition("Midfield");
        player.setDominantFoot("Left");
        player.setTeam(teamDao.getTeamById(team.getTeamId()));
        player = playerDao.createPlayer(player);

        Player player2 = new Player();
        player2.setFirstName("firstName2");
        player2.setLastName("lastName2");
        player2.setHeight(70);
        player2.setWeight(175);
        player2.setPlayerPosition("Defense");
        player2.setDominantFoot("Right");
        player2.setTeam(teamDao.getTeamById(team2.getTeamId()));
        player2 = playerDao.createPlayer(player2);

        List<Player> players = playerDao.getAllPlayersByTeam(team.getTeamId());

        assertEquals(1, players.size());
    }

    @Test
    public void testGetAllPlayersByTeamContains() {
        Team team = new Team();
        team.setCoachName("Coach");
        team.setTeamName("team");
        team = teamDao.createTeam(team);

        Team team2 = new Team();
        team2.setCoachName("Coach2");
        team2.setTeamName("team2");
        team2 = teamDao.createTeam(team2);

        Player player = new Player();
        player.setFirstName("firstName");
        player.setLastName("lastName");
        player.setHeight(70);
        player.setWeight(175);
        player.setPlayerPosition("Midfield");
        player.setDominantFoot("Left");
        player.setTeam(teamDao.getTeamById(team.getTeamId()));
        player = playerDao.createPlayer(player);

        Player player2 = new Player();
        player2.setFirstName("firstName2");
        player2.setLastName("lastName2");
        player2.setHeight(70);
        player2.setWeight(175);
        player2.setPlayerPosition("Defense");
        player2.setDominantFoot("Right");
        player2.setTeam(teamDao.getTeamById(team2.getTeamId()));
        player2 = playerDao.createPlayer(player2);

        List<Player> players = playerDao.getAllPlayersByTeam(team.getTeamId());

        assertTrue(players.contains(player));
    }

    /**
     * Test of updatePlayer method, of class PlayerDaoDB.
     */
    @Test
    public void testUpdatePlayerChanges() {
        Team team = new Team();
        team.setCoachName("Coach");
        team.setTeamName("team");
        team = teamDao.createTeam(team);

        Player player = new Player();
        player.setFirstName("firstName");
        player.setLastName("lastName");
        player.setHeight(70);
        player.setWeight(175);
        player.setPlayerPosition("Midfield");
        player.setDominantFoot("Left");
        player.setTeam(teamDao.getTeamById(team.getTeamId()));
        player = playerDao.createPlayer(player);

        Player fromDao = playerDao.getPlayerById(player.getPlayerId());

        player.setFirstName("TEST FIRST NAME");
        playerDao.updatePlayer(player);

        assertNotEquals(player, fromDao);
    }

    @Test
    public void testUpdatePlayerReturns() {
        Team team = new Team();
        team.setCoachName("Coach");
        team.setTeamName("team");
        team = teamDao.createTeam(team);

        Player player = new Player();
        player.setFirstName("firstName");
        player.setLastName("lastName");
        player.setHeight(70);
        player.setWeight(175);
        player.setPlayerPosition("Midfield");
        player.setDominantFoot("Left");
        player.setTeam(teamDao.getTeamById(team.getTeamId()));
        player = playerDao.createPlayer(player);

        Player fromDao = playerDao.getPlayerById(player.getPlayerId());

        player.setFirstName("TEST FIRST NAME");
        playerDao.updatePlayer(player);

        fromDao = playerDao.getPlayerById(player.getPlayerId());

        assertEquals(player, fromDao);
    }

    /**
     * Test of deletePlayer method, of class PlayerDaoDB.
     */
    @Test
    public void testDeletePlayer() {
        Team team = new Team();
        team.setCoachName("Coach");
        team.setTeamName("team");
        team = teamDao.createTeam(team);

        Player player = new Player();
        player.setFirstName("firstName");
        player.setLastName("lastName");
        player.setHeight(70);
        player.setWeight(175);
        player.setPlayerPosition("Midfield");
        player.setDominantFoot("Left");
        player.setTeam(teamDao.getTeamById(team.getTeamId()));
        player = playerDao.createPlayer(player);
        
        playerDao.deletePlayer(player.getPlayerId());

        Player fromDao = playerDao.getPlayerById(player.getPlayerId());
        
        assertNull(fromDao);
    }

}
