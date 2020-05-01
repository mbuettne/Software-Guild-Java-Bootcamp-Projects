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
public class playerServiceTest {

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

    public playerServiceTest() {
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
        for (User user : users) {
            userService.deleteUser(user.getUserid());
        }

        List<Role> roles = roleService.returnAllRoles();
        for (Role role : roles) {
            roleService.deleteRole(role.getRoleid());
        }

        List<Teamgame> teamGames = teamgameService.getAllTeamgames();
        for (Teamgame teamGame : teamGames) {
            teamgameService.deleteTeamgame(teamGame.getTeamgameid());
        }

        List<Playergame> playerGames = playergameService.getAllPlayerGames();
        for (Playergame playerGame : playerGames) {
            playergameService.deletePlayergameById(playerGame.getPlayergameid());
        }

        List<Player> players = playerService.getAllPlayers();
        for (Player player : players) {
            playerService.deletePlayerById(player.getPlayerid());
        }

        List<Team> teams = teamService.getAllTeams();
        for (Team team : teams) {
            teamService.deleteTeam(team.getTeamid());
        }

    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of saveOrUpdatePlayer method, of class playerService.
     */
    @Test
    public void testSaveOrUpdatePlayer() {
    }

    /**
     * Test of getPlayerById method, of class playerService.
     */
    @Test
    public void testCreateAndGetPlayerById() {
        Team team = new Team();
        team.setCoachname("Coach");
        team.setTeamname("team");
        team = teamService.saveOrUpdateTeam(team);

        Player player = new Player();
        player.setFirstname("firstName");
        player.setLastname("lastName");
        player.setHeight(70);
        player.setWeight(175);
        player.setPlayerposition("Midfield");
        player.setDominantfoot("Left");
        player.setTeamid(team.getTeamid());

        player = playerService.saveOrUpdatePlayer(player);

        Player fromMemory = playerService.getPlayerById(player.getPlayerid());

        assertEquals(player, fromMemory);
    }

    /**
     * Test of getAllPlayersByTeam method, of class playerService.
     */
    @Test
    public void testGetAllPlayersByTeam() {
        Team team = new Team();
        team.setCoachname("Coach");
        team.setTeamname("team");
        team = teamService.saveOrUpdateTeam(team);

        Player player = new Player();
        player.setFirstname("firstName");
        player.setLastname("lastName");
        player.setHeight(70);
        player.setWeight(175);
        player.setPlayerposition("Midfield");
        player.setDominantfoot("Left");
        player.setTeamid(team.getTeamid());

        player = playerService.saveOrUpdatePlayer(player);

        Player player2 = new Player();
        player2.setFirstname("firstName");
        player2.setLastname("lastName");
        player2.setHeight(70);
        player2.setWeight(175);
        player2.setPlayerposition("Midfield");
        player2.setDominantfoot("Left");
        player2.setTeamid(team.getTeamid());

        player2 = playerService.saveOrUpdatePlayer(player2);

        List<Player> playerList = playerService.getAllPlayersByTeam(team.getTeamid());

        assertEquals(2, playerList.size());
    }

    /**
     * Test of deletePlayerById method, of class playerService.
     */
    @Test
    public void testDeletePlayerById() {
        Team team = new Team();
        team.setCoachname("Coach");
        team.setTeamname("team");
        team = teamService.saveOrUpdateTeam(team);

        Player player = new Player();
        player.setFirstname("firstName");
        player.setLastname("lastName");
        player.setHeight(70);
        player.setWeight(175);
        player.setPlayerposition("Midfield");
        player.setDominantfoot("Left");
        player.setTeamid(team.getTeamid());
        player = playerService.saveOrUpdatePlayer(player);

        playerService.deletePlayerById(player.getPlayerid());

        Player fromMemory = playerService.getPlayerById(player.getPlayerid());

        assertNull(fromMemory);
    }

    @Test
    public void testUpdatePlayerById() {
        Team team = new Team();
        team.setCoachname("Coach");
        team.setTeamname("team");
        team = teamService.saveOrUpdateTeam(team);

        Player player = new Player();
        player.setFirstname("firstName");
        player.setLastname("lastName");
        player.setHeight(70);
        player.setWeight(175);
        player.setPlayerposition("Midfield");
        player.setDominantfoot("Left");
        player.setTeamid(team.getTeamid());
        player = playerService.saveOrUpdatePlayer(player);
        
        Player fromMemory = playerService.getPlayerById(player.getPlayerid());
        
        player.setDominantfoot("Right");
        playerService.saveOrUpdatePlayer(player);

        assertNotEquals(player, fromMemory);
    }

}
