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
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author mbuet
 */
@SpringBootTest
public class UserDaoDBTest {

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

    public UserDaoDBTest() {
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
     * Test of getUserById method, of class UserDaoDB.
     */
    @Test
    public void testCreateAndGetUserById() {
        Role role = new Role();
        role.setRoleName("TEST_ROLE");
        role = roleDao.createRole(role);

        Team team = new Team();
        team.setCoachName("Coach");
        team.setTeamName("Team");
        team = teamDao.createTeam(team);

        User user = new User();
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        user.setUsername("Username");
        user.setEnabled(true);
        user.setPassword("Password");
        user.setRole(role);
        user.setTeam(team);

        user = userDao.createUser(user);

        User fromDao = userDao.getUserById(user.getUserId());

        assertEquals(user, fromDao);
    }

    /**
     * Test of getUserByUsername method, of class UserDaoDB.
     */
    @Test
    public void testGetUserByUsername() {
        Role role = new Role();
        role.setRoleName("TEST_ROLE");
        role = roleDao.createRole(role);

        Team team = new Team();
        team.setCoachName("Coach");
        team.setTeamName("Team");
        team = teamDao.createTeam(team);

        User user = new User();
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        user.setUsername("Username");
        user.setEnabled(true);
        user.setPassword("Password");
        user.setRole(role);
        user.setTeam(team);

        user = userDao.createUser(user);

        User fromDao = userDao.getUserByUsername(user.getUsername());

        assertEquals(user, fromDao);
    }

    /**
     * Test of getAllUsers method, of class UserDaoDB.
     */
    @Test
    public void testGetAllUsersSize() {
        Role role = new Role();
        role.setRoleName("TEST_ROLE");
        role = roleDao.createRole(role);

        Team team = new Team();
        team.setCoachName("Coach");
        team.setTeamName("Team");
        team = teamDao.createTeam(team);

        User user = new User();
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        user.setUsername("Username");
        user.setEnabled(true);
        user.setPassword("Password");
        user.setRole(role);
        user.setTeam(team);
        user = userDao.createUser(user);

        User user2 = new User();
        user2.setFirstName("FirstName2");
        user2.setLastName("LastName2");
        user2.setUsername("Username2");
        user2.setEnabled(true);
        user2.setPassword("Password2");
        user2.setRole(role);
        user2.setTeam(team);
        user2 = userDao.createUser(user2);

        List<User> users = userDao.getAllUsers();

        assertEquals(2, users.size());

    }

    @Test
    public void testGetAllUsersContains() {
        Role role = new Role();
        role.setRoleName("TEST_ROLE");
        role = roleDao.createRole(role);

        Team team = new Team();
        team.setCoachName("Coach");
        team.setTeamName("Team");
        team = teamDao.createTeam(team);

        User user = new User();
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        user.setUsername("Username");
        user.setEnabled(true);
        user.setPassword("Password");
        user.setRole(role);
        user.setTeam(team);
        user = userDao.createUser(user);

        User user2 = new User();
        user2.setFirstName("FirstName2");
        user2.setLastName("LastName2");
        user2.setUsername("Username2");
        user2.setEnabled(true);
        user2.setPassword("Password2");
        user2.setRole(role);
        user2.setTeam(team);
        user2 = userDao.createUser(user2);

        List<User> users = userDao.getAllUsers();

        assertTrue(users.contains(user));

    }

    /**
     * Test of getAllUsersByTeam method, of class UserDaoDB.
     */
    @Test
    public void testGetAllUsersByTeamSize() {
        Role role = new Role();
        role.setRoleName("TEST_ROLE");
        role = roleDao.createRole(role);

        Team team = new Team();
        team.setCoachName("Coach");
        team.setTeamName("Team");
        team = teamDao.createTeam(team);

        User user = new User();
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        user.setUsername("Username");
        user.setEnabled(true);
        user.setPassword("Password");
        user.setRole(role);
        user.setTeam(team);
        user = userDao.createUser(user);

        User user2 = new User();
        user2.setFirstName("FirstName2");
        user2.setLastName("LastName2");
        user2.setUsername("Username2");
        user2.setEnabled(true);
        user2.setPassword("Password2");
        user2.setRole(role);
        user2.setTeam(team);
        user2 = userDao.createUser(user2);

        List<User> users = userDao.getAllUsersByTeam(team.getTeamId());

        assertEquals(2, users.size());

    }

    @Test
    public void testGetAllUsersByTeamContains() {
        Role role = new Role();
        role.setRoleName("TEST_ROLE");
        role = roleDao.createRole(role);

        Team team = new Team();
        team.setCoachName("Coach");
        team.setTeamName("Team");
        team = teamDao.createTeam(team);

        User user = new User();
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        user.setUsername("Username");
        user.setEnabled(true);
        user.setPassword("Password");
        user.setRole(role);
        user.setTeam(team);
        user = userDao.createUser(user);

        User user2 = new User();
        user2.setFirstName("FirstName2");
        user2.setLastName("LastName2");
        user2.setUsername("Username2");
        user2.setEnabled(true);
        user2.setPassword("Password2");
        user2.setRole(role);
        user2.setTeam(team);
        user2 = userDao.createUser(user2);

        List<User> users = userDao.getAllUsersByTeam(team.getTeamId());

        assertTrue(users.contains(user));

    }

    /**
     * Test of updateUser method, of class UserDaoDB.
     */
    @Test
    public void testUpdateUserChanges() {
        Role role = new Role();
        role.setRoleName("TEST_ROLE");
        role = roleDao.createRole(role);

        Team team = new Team();
        team.setCoachName("Coach");
        team.setTeamName("Team");
        team = teamDao.createTeam(team);

        User user = new User();
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        user.setUsername("Username");
        user.setEnabled(true);
        user.setPassword("Password");
        user.setRole(role);
        user.setTeam(team);
        user = userDao.createUser(user);

        User fromDao = userDao.getUserById(user.getUserId());

        user.setFirstName("Test New First");
        userDao.updateUser(user);

        assertNotEquals(user, fromDao);

    }

    @Test
    public void testUpdateUserReturns() {
        Role role = new Role();
        role.setRoleName("TEST_ROLE");
        role = roleDao.createRole(role);

        Team team = new Team();
        team.setCoachName("Coach");
        team.setTeamName("Team");
        team = teamDao.createTeam(team);

        User user = new User();
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        user.setUsername("Username");
        user.setEnabled(true);
        user.setPassword("Password");
        user.setRole(role);
        user.setTeam(team);
        user = userDao.createUser(user);

        User fromDao = userDao.getUserById(user.getUserId());

        user.setFirstName("Test New First");
        userDao.updateUser(user);

        fromDao = userDao.getUserById(user.getUserId());

        assertEquals(user, fromDao);

    }

    /**
     * Test of deleteUser method, of class UserDaoDB.
     */
    @Test
    public void testDeleteUser() {
        Role role = new Role();
        role.setRoleName("TEST_ROLE");
        role = roleDao.createRole(role);

        Team team = new Team();
        team.setCoachName("Coach");
        team.setTeamName("Team");
        team = teamDao.createTeam(team);

        User user = new User();
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        user.setUsername("Username");
        user.setEnabled(true);
        user.setPassword("Password");
        user.setRole(role);
        user.setTeam(team);
        user = userDao.createUser(user);

        userDao.deleteUser(user.getUserId());

        User fromDao = userDao.getUserById(user.getUserId());

        assertNull(fromDao);

    }

}
