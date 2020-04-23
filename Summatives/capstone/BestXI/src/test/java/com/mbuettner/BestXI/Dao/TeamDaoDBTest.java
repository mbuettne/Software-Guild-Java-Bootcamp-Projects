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
public class TeamDaoDBTest {

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

    public TeamDaoDBTest() {
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
     * Test of getTeamById method, of class TeamDaoDB.
     */
    @Test
    public void testCreateAndGetTeamById() {
        Team team = new Team();
        team.setCoachName("Coach");
        team.setTeamName("Team");
        team.setLogoUrl("https://en.wikipedia.org/wiki/Borussia_Dortmund#/media/File:Borussia_Dortmund_logo.svg");
        team = teamDao.createTeam(team);

        Team fromDao = teamDao.getTeamById(team.getTeamId());

        assertEquals(team, fromDao);
    }

    /**
     * Test of getTeamByTeamName method, of class TeamDaoDB.
     */
    @Test
    public void testGetTeamByTeamName() {
        Team team = new Team();
        team.setCoachName("Coach");
        team.setTeamName("Team");
        team.setLogoUrl("https://en.wikipedia.org/wiki/Borussia_Dortmund#/media/File:Borussia_Dortmund_logo.svg");
        team = teamDao.createTeam(team);

        Team fromDao = teamDao.getTeamByTeamName(team.getTeamName());

        assertEquals(team, fromDao);
    }

    /**
     * Test of getAllTeams method, of class TeamDaoDB.
     */
    @Test
    public void testGetAllTeamsSize() {
        Team team = new Team();
        team.setCoachName("Coach");
        team.setTeamName("Team");
        team.setLogoUrl("https://en.wikipedia.org/wiki/Borussia_Dortmund#/media/File:Borussia_Dortmund_logo.svg");
        team = teamDao.createTeam(team);

        Team team2 = new Team();
        team2.setCoachName("Coach");
        team2.setTeamName("Team");
        team2.setLogoUrl("https://en.wikipedia.org/wiki/Borussia_Dortmund#/media/File:Borussia_Dortmund_logo.svg");
        team2 = teamDao.createTeam(team2);

        List<Team> teams = teamDao.getAllTeams();

        assertEquals(2, teams.size());
    }

    @Test
    public void testGetAllTeamsContains() {
        Team team = new Team();
        team.setCoachName("Coach");
        team.setTeamName("Team");
        team.setLogoUrl("https://en.wikipedia.org/wiki/Borussia_Dortmund#/media/File:Borussia_Dortmund_logo.svg");
        team = teamDao.createTeam(team);

        Team team2 = new Team();
        team2.setCoachName("Coach");
        team2.setTeamName("Team");
        team2.setLogoUrl("https://en.wikipedia.org/wiki/Borussia_Dortmund#/media/File:Borussia_Dortmund_logo.svg");
        team2 = teamDao.createTeam(team2);

        List<Team> teams = teamDao.getAllTeams();

        assertTrue(teams.contains(team2));
    }

    /**
     * Test of updateTeam method, of class TeamDaoDB.
     */
    @Test
    public void testUpdateTeamChanges() {
        Team team = new Team();
        team.setCoachName("Coach");
        team.setTeamName("Team");
        team.setLogoUrl("https://en.wikipedia.org/wiki/Borussia_Dortmund#/media/File:Borussia_Dortmund_logo.svg");
        team = teamDao.createTeam(team);

        Team fromDao = teamDao.getTeamById(team.getTeamId());

        team.setCoachName("Manager");
        teamDao.updateTeam(team);

        assertNotEquals(team, fromDao);
    }

    @Test
    public void testUpdateTeamReturns() {
        Team team = new Team();
        team.setCoachName("Coach");
        team.setTeamName("Team");
        team.setLogoUrl("https://en.wikipedia.org/wiki/Borussia_Dortmund#/media/File:Borussia_Dortmund_logo.svg");
        team = teamDao.createTeam(team);

        Team fromDao = teamDao.getTeamById(team.getTeamId());

        team.setCoachName("Manager");
        teamDao.updateTeam(team);

        fromDao = teamDao.getTeamById(team.getTeamId());

        assertEquals(team, fromDao);
    }

    /**
     * Test of deleteTeam method, of class TeamDaoDB.
     */
    @Test
    public void testDeleteTeam() {
        Team team = new Team();
        team.setCoachName("Coach");
        team.setTeamName("Team");
        team.setLogoUrl("https://en.wikipedia.org/wiki/Borussia_Dortmund#/media/File:Borussia_Dortmund_logo.svg");
        team = teamDao.createTeam(team);
        
        teamDao.deleteTeam(team.getTeamId());
        
        Team fromDao = teamDao.getTeamById(team.getTeamId());
        
        assertNull(fromDao);

    }

}
