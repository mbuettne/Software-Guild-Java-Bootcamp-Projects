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
import java.time.LocalDate;
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
public class teamgameServiceTest {

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

    public teamgameServiceTest() {
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
     * Test of saveOrUpdateTeamgame method, of class teamgameService.
     */
    @Test
    public void testSaveAndGetTeamgame() {
        Team team = new Team();
        team.setCoachname("Coach");
        team.setTeamname("team");
        teamService.saveOrUpdateTeam(team);
        Teamgame tg = new Teamgame();
        tg.setGamedate(LocalDate.now());
        tg.setGamelocation("Home");
        tg.setOpponent("Opponent");
        tg.setOpponentscore(2);
        tg.setTeamscore(2);
        tg.setResult("Draw");
        tg.setTeamid(team.getTeamid());
        teamgameService.saveOrUpdateTeamgame(tg);
        
        Teamgame fromMemory = teamgameService.getTeamgameById(tg.getTeamgameid());
        
        assertEquals(tg, fromMemory);
    }


    /**
     * Test of getAllGamesByTeam method, of class teamgameService.
     */
    @Test
    public void testGetAllGamesByTeam() {
         Team team = new Team();
        team.setCoachname("Coach");
        team.setTeamname("team");
        teamService.saveOrUpdateTeam(team);
        
        Teamgame tg = new Teamgame();
        tg.setGamedate(LocalDate.now());
        tg.setGamelocation("Home");
        tg.setOpponent("Opponent");
        tg.setOpponentscore(2);
        tg.setTeamscore(2);
        tg.setResult("Draw");
        tg.setTeamid(team.getTeamid());
        teamgameService.saveOrUpdateTeamgame(tg);
        
        Teamgame tg2 = new Teamgame();
        tg2.setGamedate(LocalDate.now());
        tg2.setGamelocation("Home");
        tg2.setOpponent("Opponent");
        tg2.setOpponentscore(2);
        tg2.setTeamscore(2);
        tg2.setResult("Draw");
        tg2.setTeamid(team.getTeamid());
        teamgameService.saveOrUpdateTeamgame(tg2);
        
        List<Teamgame> allGames = teamgameService.getAllGamesByTeam(team.getTeamid());
        
        assertEquals(2, allGames.size());
    }


    /**
     * Test of getLatestGameByTeam method, of class teamgameService.
     */
    @Test
    public void testGetLatestGameByTeam() {
         Team team = new Team();
        team.setCoachname("Coach");
        team.setTeamname("team");
        teamService.saveOrUpdateTeam(team);
        
        Teamgame tg = new Teamgame();
        tg.setGamedate(LocalDate.parse("2020-03-07"));
        tg.setGamelocation("Home");
        tg.setOpponent("Opponent");
        tg.setOpponentscore(2);
        tg.setTeamscore(2);
        tg.setResult("Draw");
        tg.setTeamid(team.getTeamid());
        teamgameService.saveOrUpdateTeamgame(tg);
        
        Teamgame tg2 = new Teamgame();
        tg2.setGamedate(LocalDate.now());
        tg2.setGamelocation("Home");
        tg2.setOpponent("Opponent");
        tg2.setOpponentscore(2);
        tg2.setTeamscore(2);
        tg2.setResult("Draw");
        tg2.setTeamid(team.getTeamid());
        teamgameService.saveOrUpdateTeamgame(tg2);
        
        Teamgame latest = teamgameService.getLatestGameByTeam(team.getTeamid());
        
        assertEquals(latest, tg2);
    }

    /**
     * Test of deleteTeamgame method, of class teamgameService.
     */
    @Test
    public void testDeleteTeamgame() {
         Team team = new Team();
        team.setCoachname("Coach");
        team.setTeamname("team");
        teamService.saveOrUpdateTeam(team);
        
        Teamgame tg = new Teamgame();
        tg.setGamedate(LocalDate.now());
        tg.setGamelocation("Home");
        tg.setOpponent("Opponent");
        tg.setOpponentscore(2);
        tg.setTeamscore(2);
        tg.setResult("Draw");
        tg.setTeamid(team.getTeamid());
        teamgameService.saveOrUpdateTeamgame(tg);
        
        teamgameService.deleteTeamgame(tg.getTeamgameid());
        
        Teamgame fromMemory = teamgameService.getTeamgameById(tg.getTeamgameid());
        
        assertNull(fromMemory);
    }

    /**
     * Test of getAllTeamgames method, of class teamgameService.
     */
    @Test
    public void testGetAllTeamgames() {
         Team team = new Team();
        team.setCoachname("Coach");
        team.setTeamname("team");
        teamService.saveOrUpdateTeam(team);
        
        Teamgame tg = new Teamgame();
        tg.setGamedate(LocalDate.now());
        tg.setGamelocation("Home");
        tg.setOpponent("Opponent");
        tg.setOpponentscore(2);
        tg.setTeamscore(2);
        tg.setResult("Draw");
        tg.setTeamid(team.getTeamid());
        teamgameService.saveOrUpdateTeamgame(tg);
        
        Team team2 = new Team();
        team2.setCoachname("Coach2");
        team2.setTeamname("team2");
        team2.setLogourl("2");
        teamService.saveOrUpdateTeam(team2);
        
        Teamgame tg2 = new Teamgame();
        tg2.setGamedate(LocalDate.now());
        tg2.setGamelocation("Home");
        tg2.setOpponent("Opponent");
        tg2.setOpponentscore(2);
        tg2.setTeamscore(2);
        tg2.setResult("Draw");
        tg2.setTeamid(team2.getTeamid());
        teamgameService.saveOrUpdateTeamgame(tg2);
        
        List<Teamgame> games = teamgameService.getAllTeamgames();
        
        assertEquals(games.size(), 2);
    }

}
