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
public class roleServiceTest {

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

    public roleServiceTest() {
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
     * Test of returnAllRoles method, of class roleService.
     */
    @Test
    public void testCreateAndReturnAllRoles() {
        Role role1 = new Role();
        role1.setRolename("RoleName1");
        roleService.saveAndUpdateRole(role1);
        
        Role role2 = new Role();
        role2.setRolename("RoleName2");
        roleService.saveAndUpdateRole(role2);
        
        List<Role> roles = roleService.returnAllRoles();
        
        assertEquals(2, roles.size());
    }

    /**
     * Test of deleteRole method, of class roleService.
     */
    @Test
    public void testDeleteRole() {
        Role role1 = new Role();
        role1.setRolename("RoleName1");
        roleService.saveAndUpdateRole(role1);
        
        roleService.deleteRole(role1.getRoleid());
        
        Role fromMemory = roleService.getRoleById(role1.getRoleid());
        
        assertNotEquals(fromMemory, role1);
    }

}
