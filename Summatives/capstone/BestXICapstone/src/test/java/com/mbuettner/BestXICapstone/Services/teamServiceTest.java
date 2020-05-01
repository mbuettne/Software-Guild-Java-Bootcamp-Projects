/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.BestXICapstone.Services;

import com.mbuettner.BestXICapstone.Entities.Player;
import com.mbuettner.BestXICapstone.Entities.Playergame;
import com.mbuettner.BestXICapstone.Entities.Role;
import com.mbuettner.BestXICapstone.Entities.Team;
import com.mbuettner.BestXICapstone.Entities.Teamgame;
import com.mbuettner.BestXICapstone.Entities.User;
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
public class teamServiceTest {

    @Autowired
    playerService playerService;

    @Autowired
    playergameService playergameService;

    @Autowired
    roleService roleService;

    @Autowired
    teamService teamService;

    @Autowired
    teamgameService teamgameService;

    @Autowired
    userService userService;

    public teamServiceTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        List<User> users = userService.getAllUsers();
        if (users.size() != 0) {
            for (User user : users) {
                userService.deleteUser(user.getUserid());
            }
        }

        List<Role> roles = roleService.returnAllRoles();
        if (roles.size() != 0) {
            for (Role role : roles) {
                roleService.deleteRole(role.getRoleid());
            }
        }

        List<Teamgame> teamGames = teamgameService.getAllTeamgames();
        if (teamGames.size() != 0) {
            for (Teamgame teamGame : teamGames) {
                teamgameService.deleteTeamgame(teamGame.getTeamgameid());
            }
        }

        List<Playergame> playerGames = playergameService.getAllPlayerGames();
        if (playerGames.size() != 0) {
            for (Playergame playerGame : playerGames) {
                playergameService.deletePlayergameById(playerGame.getPlayergameid());
            }
        }

        List<Player> players = playerService.getAllPlayers();
        if (players.size() != 0) {
            for (Player player : players) {
                playerService.deletePlayerById(player.getPlayerid());
            }
        }

        List<Team> teams = teamService.getAllTeams();
        if (teams.size() != 0) {
            for (Team team : teams) {
                teamService.deleteTeam(team.getTeamid());
            }
        }

    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of saveOrUpdateTeam method, of class teamService.
     */
    @Test
    public void testSaveAndGetTeam() {
        Team team = new Team();
        team.setCoachname("Coach");
        team.setTeamname("Team");
        team.setLogourl("");
        team = teamService.saveOrUpdateTeam(team);

        Team fromMemory = teamService.getTeamById(team.getTeamid());

        assertEquals(team, fromMemory);
    }

    /**
     * Test of getAllTeams method, of class teamService.
     */
    @Test
    public void testGetAllTeams() {
        Team team = new Team();
        team.setCoachname("Coach");
        team.setTeamname("Team");
        team.setLogourl("");
        team = teamService.saveOrUpdateTeam(team);

        Team team2 = new Team();
        team2.setCoachname("Coach2");
        team2.setTeamname("Team2");
        team2.setLogourl("2");
        team2 = teamService.saveOrUpdateTeam(team2);

        List<Team> teams = teamService.getAllTeams();

        assertEquals(2, teams.size());
    }

    /**
     * Test of getTeamById method, of class teamService.
     */
    @Test
    public void testGetTeamById() {
        Team team = new Team();
        team.setCoachname("Coach");
        team.setTeamname("Team");
        team.setLogourl("");
        team = teamService.saveOrUpdateTeam(team);

        Team team2 = new Team();
        team2.setCoachname("Coach2");
        team2.setTeamname("Team2");
        team2.setLogourl("2");
        team2 = teamService.saveOrUpdateTeam(team2);

        Team fromMemory = teamService.getTeamById(team.getTeamid());

        assertEquals(team, fromMemory);
    }

    /**
     * Test of deleteTeam method, of class teamService.
     */
    @Test
    public void testDeleteTeam() {
        Team team = new Team();
        team.setCoachname("Coach");
        team.setTeamname("Team");
        team.setLogourl("");
        team = teamService.saveOrUpdateTeam(team);

        teamService.deleteTeam(team.getTeamid());

        Team fromMemory = teamService.getTeamById(team.getTeamid());

        assertNull(fromMemory);

    }

}
