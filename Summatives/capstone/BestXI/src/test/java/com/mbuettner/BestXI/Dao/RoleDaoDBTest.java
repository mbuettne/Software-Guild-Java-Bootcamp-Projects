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
public class RoleDaoDBTest {

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

    public RoleDaoDBTest() {
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
     * Test of getRoleById method, of class RoleDaoDB.
     */
    @Test
    public void testCreateAndGetRoleById() {
        Role role = new Role();
        role.setRoleName("ROLE_TEST");
        role = roleDao.createRole(role);

        Role fromDao = roleDao.getRoleById(role.getRoleId());

        assertEquals(role, fromDao);
    }

    /**
     * Test of getRoleByRole method, of class RoleDaoDB.
     */
    @Test
    public void testGetRoleByRole() {
        Role role = new Role();
        role.setRoleName("ROLE_TEST");
        role = roleDao.createRole(role);

        Role fromDao = roleDao.getRoleByRole(role.getRoleName());

        assertEquals(role, fromDao);
    }

    /**
     * Test of getAllRoles method, of class RoleDaoDB.
     */
    @Test
    public void testGetAllRolesSize() {
        Role role1 = new Role();
        role1.setRoleName("Role1");
        role1 = roleDao.createRole(role1);

        Role role2 = new Role();
        role2.setRoleName("Role2");
        role2 = roleDao.createRole(role2);

        List<Role> roles = roleDao.getAllRoles();

        assertEquals(2, roles.size());
    }

    @Test
    public void testGetAllRolesContains() {
        Role role1 = new Role();
        role1.setRoleName("Role1");
        role1 = roleDao.createRole(role1);

        Role role2 = new Role();
        role2.setRoleName("Role2");
        role2 = roleDao.createRole(role2);

        List<Role> roles = roleDao.getAllRoles();

        assertTrue(roles.contains(role2));
    }

    /**
     * Test of deleteRole method, of class RoleDaoDB.
     */
    @Test
    public void testDeleteRole() {
        Role role = new Role();
        role.setRoleName("ROLE_TEST");
        role = roleDao.createRole(role);

        roleDao.deleteRole(role.getRoleId());

        Role fromDao = roleDao.getRoleByRole(role.getRoleName());

        assertNull(fromDao);
    }

    /**
     * Test of updateRole method, of class RoleDaoDB.
     */
    @Test
    public void testUpdateRoleChanges() {
        Role role = new Role();
        role.setRoleName("ROLE_TEST");
        role = roleDao.createRole(role);

        Role fromDao = roleDao.getRoleByRole(role.getRoleName());
        
        role.setRoleName("ROLE_CHANGED");
        roleDao.updateRole(role);

        assertNotEquals(role, fromDao);
    }
    
        @Test
    public void testUpdateRoleReturns() {
        Role role = new Role();
        role.setRoleName("ROLE_TEST");
        role = roleDao.createRole(role);

        Role fromDao = roleDao.getRoleByRole(role.getRoleName());
        
        role.setRoleName("ROLE_CHANGED");
        roleDao.updateRole(role);
        
        fromDao = roleDao.getRoleById(role.getRoleId());

        assertEquals(role, fromDao);
    }

}
