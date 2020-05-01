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
public class userServiceTest {

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

    public userServiceTest() {
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
     * Test of saveOrUpdateUser method, of class userService.
     */
    @Test
    public void testSaveAndGetUser() {
        Team team = new Team();
        team.setCoachname("coach");
        team.setTeamname("team");
        teamService.saveOrUpdateTeam(team);

        Role role = new Role();
        role.setRolename("role");
        roleService.saveAndUpdateRole(role);

        User user = new User();
        user.setFirstname("name");
        user.setLastname("name");
        user.setEnabled(true);
        user.setPassword("password");
        user.setRoleid(role.getRoleid());
        user.setTeamid(team.getTeamid());
        user.setUsername("username");
        userService.saveOrUpdateUser(user);

        User fromMemory = userService.getUserById(user.getUserid());

        assertEquals(user, fromMemory);

    }

    /**
     * Test of getAllUsersByTeam method, of class userService.
     */
    @Test
    public void testGetAllUsersByTeam() {
        Team team = new Team();
        team.setCoachname("coach");
        team.setTeamname("team");
        teamService.saveOrUpdateTeam(team);
        
            Team team2 = new Team();
        team2.setCoachname("coach");
        team2.setTeamname("team");
        teamService.saveOrUpdateTeam(team2);

        Role role = new Role();
        role.setRolename("role");
        roleService.saveAndUpdateRole(role);

        User user = new User();
        user.setFirstname("name");
        user.setLastname("name");
        user.setEnabled(true);
        user.setPassword("password");
        user.setRoleid(role.getRoleid());
        user.setTeamid(team.getTeamid());
        user.setUsername("username1");
        userService.saveOrUpdateUser(user);

        User user2 = new User();
        user2.setFirstname("name");
        user2.setLastname("name");
        user2.setEnabled(true);
        user2.setPassword("password");
        user2.setRoleid(role.getRoleid());
        user2.setTeamid(team.getTeamid());
        user2.setUsername("username2");
        userService.saveOrUpdateUser(user2);
        
         User user3 = new User();
        user3.setFirstname("name");
        user3.setLastname("name");
        user3.setEnabled(true);
        user3.setPassword("password");
        user3.setRoleid(role.getRoleid());
        user3.setTeamid(team2.getTeamid());
        user3.setUsername("username3");
        userService.saveOrUpdateUser(user3);


        List<User> users = userService.getAllUsersByTeam(team.getTeamid());
        
        assertEquals(2, users.size());
    }

    /**
     * Test of deleteUser method, of class userService.
     */
    @Test
    public void testDeleteUser() {
                Team team = new Team();
        team.setCoachname("coach");
        team.setTeamname("team");
        teamService.saveOrUpdateTeam(team);

        Role role = new Role();
        role.setRolename("role");
        roleService.saveAndUpdateRole(role);

        User user = new User();
        user.setFirstname("name");
        user.setLastname("name");
        user.setEnabled(true);
        user.setPassword("password");
        user.setRoleid(role.getRoleid());
        user.setTeamid(team.getTeamid());
        user.setUsername("username");
        userService.saveOrUpdateUser(user);
        
        userService.deleteUser(user.getUserid());
        
        User fromMemory = userService.getUserById(user.getUserid());
        
        assertNull(fromMemory);
    }

    /**
     * Test of getAllUsers method, of class userService.
     */
    @Test
    public void testGetAllUsers() {
                Team team = new Team();
        team.setCoachname("coach");
        team.setTeamname("team");
        teamService.saveOrUpdateTeam(team);
        
            Team team2 = new Team();
        team2.setCoachname("coach");
        team2.setTeamname("team");
        teamService.saveOrUpdateTeam(team2);

        Role role = new Role();
        role.setRolename("role");
        roleService.saveAndUpdateRole(role);

        User user = new User();
        user.setFirstname("name");
        user.setLastname("name");
        user.setEnabled(true);
        user.setPassword("password");
        user.setRoleid(role.getRoleid());
        user.setTeamid(team.getTeamid());
        user.setUsername("username1");
        userService.saveOrUpdateUser(user);

        User user2 = new User();
        user2.setFirstname("name");
        user2.setLastname("name");
        user2.setEnabled(true);
        user2.setPassword("password");
        user2.setRoleid(role.getRoleid());
        user2.setTeamid(team.getTeamid());
        user2.setUsername("username2");
        userService.saveOrUpdateUser(user2);
        
         User user3 = new User();
        user3.setFirstname("name");
        user3.setLastname("name");
        user3.setEnabled(true);
        user3.setPassword("password");
        user3.setRoleid(role.getRoleid());
        user3.setTeamid(team.getTeamid());
        user3.setUsername("username3");
        userService.saveOrUpdateUser(user3);


        List<User> users = userService.getAllUsers();
        
        assertEquals(3, users.size());
    }

}
