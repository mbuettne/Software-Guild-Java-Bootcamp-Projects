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
public class playergameServiceTest {

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

    public playergameServiceTest() {
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
     * Test of saveOrUpdatePlayergame method, of class playergameService.
     */
    @Test
    public void testSaveAndGetPlayergame() {
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

        Playergame pg = new Playergame();
        pg.setPlayerid(player.getPlayerid());
        playergameService.saveOrUpdatePlayergame(pg);

        Playergame fromMemory = playergameService.getPlayergameById(pg.getPlayergameid());

        assertEquals(pg, fromMemory);

    }

    /**
     * Test of getAllGamesByPlayer method, of class playergameService.
     */
    @Test
    public void testGetAllGamesByPlayer() {
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

        Playergame pg = new Playergame();
        pg.setPlayerid(player.getPlayerid());
        playergameService.saveOrUpdatePlayergame(pg);

        Playergame pg2 = new Playergame();
        pg2.setPlayerid(player.getPlayerid());
        playergameService.saveOrUpdatePlayergame(pg2);

        List<Playergame> pgList = playergameService.getAllGamesByPlayer(player.getPlayerid());

        assertEquals(2, pgList.size());
    }

    /**
     * Test of deletePlayergameById method, of class playergameService.
     */
    @Test
    public void testDeletePlayergameById() {
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

        Playergame pg = new Playergame();
        pg.setPlayerid(player.getPlayerid());
        playergameService.saveOrUpdatePlayergame(pg);

        playergameService.deletePlayergameById(pg.getPlayergameid());

        Playergame fromMemory = playergameService.getPlayergameById(pg.getPlayergameid());

        assertNotEquals(pg, fromMemory);
    }

}
