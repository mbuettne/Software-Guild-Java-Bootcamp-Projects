/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.BestXI.Controllers;

import com.mbuettner.BestXI.Dao.playerDao;
import com.mbuettner.BestXI.Dao.playerGameDao;
import com.mbuettner.BestXI.Dao.roleDao;
import com.mbuettner.BestXI.Dao.teamDao;
import com.mbuettner.BestXI.Dao.teamGameDao;
import com.mbuettner.BestXI.Dao.userDao;
import com.mbuettner.BestXI.Entities.Player;
import com.mbuettner.BestXI.Entities.PlayerGame;
import com.mbuettner.BestXI.Entities.Role;
import com.mbuettner.BestXI.Entities.Team;
import com.mbuettner.BestXI.Entities.TeamGame;
import com.mbuettner.BestXI.Entities.User;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author mbuet
 */
@Controller
public class AdminController {

    @Autowired
    userDao users;

    @Autowired
    roleDao roles;

    @Autowired
    teamDao teams;

    @Autowired
    playerDao players;

    @Autowired
    teamGameDao teamGames;

    @Autowired
    playerGameDao playerGames;

    @Autowired
    PasswordEncoder encoder;

    @GetMapping("/admin")
    public String displayAdminPage(Model model) {
        model.addAttribute("users", users.getAllUsers());
        return "admin";
    }

    @PostMapping("/addUser")
    public String addUser(String firstName, String lastName, String username, String password) {
        User user = new User();
        Role role = roles.getRoleById(2);
        Team team = teams.getTeamById(1);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setPassword(encoder.encode(password));
        user.setEnabled(true);
        user.setRole(role);
        user.setTeam(team);

        user = users.createUser(user);

        return "redirect:/admin";
    }

    @PostMapping("/deleteUser")
    public String deleteUser(Integer userId) {
        users.deleteUser(userId);
        return "redirect:/admin";
    }

    @GetMapping("/editUser")
    public String editUserDisplayer(Model model, Integer id, Integer error) {
        User user = users.getUserById(id);
        List<Role> roleList = roles.getAllRoles();

        model.addAttribute("user", user);
        model.addAttribute("roles", roleList);

        if (error != null) {
            if (error == 1) {
                model.addAttribute("error", "Passwords did not match, password was not updated. Please try again.");
            }
        }
        return "editUser";
    }

    @PostMapping(value = "/editUser")
    public String editUserAction(Integer roleId, Boolean enabled, Integer id) {
        User user = users.getUserById(id);
        if (enabled != null) {
            user.setEnabled(enabled);
        } else {
            user.setEnabled(false);
        }
        if (roleId != null) {
            user.setRole(roles.getRoleById(roleId));
        }

        users.updateUser(user);

        return "redirect:/admin";
    }

    @PostMapping("editPassword")
    public String editPassword(Integer id, String password, String confirmPassword) {
        User user = users.getUserById(id);

        if (password.equals(confirmPassword)) {
            user.setPassword(encoder.encode(password));
            users.updateUser(user);
            return "redirect:/admin";
        } else {
            return "redirect:/editUser?id=" + id + "&error=1";
        }
    }

    @GetMapping("/player")
    public String displayPlayerTestingPage(Model model, Integer id) {
        Player player = players.getPlayerById(id);
        model.addAttribute("player", player);
        return "playerTesting";
    }

    @PostMapping("addPlayer")
    public String addPlayer(String firstName, String lastName, Integer height, Integer weight, String playerPosition, String dominantFoot) {
        Team team = teams.getTeamById(1);
        Player player = new Player();
        player.setFirstName(firstName);
        player.setLastName(lastName);
        player.setHeight(height);
        player.setWeight(weight);
        player.setPlayerPosition(playerPosition);
        player.setDominantFoot(dominantFoot);
        player.setTeam(team);
        player = players.createPlayer(player);

        return "redirect:/home";

    }

    @PostMapping("editPlayer")
    public String editPlayer(String firstName, String lastName, Integer height, Integer weight, String playerPosition, String dominantFoot, Integer id) {
        Team team = teams.getTeamById(1);
        Player player = players.getPlayerById(id);
        player.setFirstName(firstName);
        player.setLastName(lastName);
        player.setHeight(height);
        player.setWeight(weight);
        player.setPlayerPosition(playerPosition);
        player.setDominantFoot(dominantFoot);
        player.setTeam(team);
        players.updatePlayer(player);

        return "redirect:/home";

    }

    @PostMapping("deletePlayer")
    public String deletePlayer(Integer playerId) {
        players.deletePlayer(playerId);

        return "redirect:/home";
    }

    @PostMapping("addTeamGame")
    public String addTeamGame(LocalDate gameDate, String gameLocation, String opponent, Integer teamScore, Integer opponentScore, String result) {
        Team team = teams.getTeamById(1);
        TeamGame tg = new TeamGame();
        tg.setGameDate(gameDate);
        tg.setGameLocation(gameLocation);
        tg.setOpponent(opponent);
        tg.setTeamScore(teamScore);
        tg.setOpponentScore(opponentScore);
        tg.setResult(result);
        tg.setTeam(team);
        tg = teamGames.createTeamGame(tg);

        return "redirect:/home";
    }

    @PostMapping("editTeamGame")
    public String editTeamGame(Integer id, LocalDate gameDate, String gameLocation, String opponent, Integer teamScore, Integer opponentScore, String result) {
        Team team = teams.getTeamById(1);
        TeamGame tg = teamGames.getTeamGameById(id);
        tg.setGameDate(gameDate);
        tg.setGameLocation(gameLocation);
        tg.setOpponent(opponent);
        tg.setTeamScore(teamScore);
        tg.setOpponentScore(opponentScore);
        tg.setResult(result);
        tg.setTeam(team);
        teamGames.updateTeamGame(tg);

        return "redirect:/home";
    }

    @PostMapping("deleteTeamGame")
    public String deleteTeamGame(Integer teamGameId) {
        teamGames.deleteTeamGame(teamGameId);

        return "redirect:/home";
    }

    @PostMapping("addPlayerGame")
    public String addPlayerGame(Integer shots, Integer goals, Integer assists, Integer dribbles, Integer passes, Integer passPercentage, Integer tackles, Integer interceptions, Integer shotsDefensed, Integer shotsSaved, Integer goalsAllowed, Integer playerId) {
        Player player = players.getPlayerById(playerId);
        PlayerGame pg = new PlayerGame();
        pg.setShots(shots);
        pg.setGoals(goals);
        pg.setAssists(assists);
        pg.setDribbles(dribbles);
        pg.setPasses(passes);
        pg.setPassPercentage(passPercentage);
        pg.setTackles(tackles);
        pg.setInterceptions(interceptions);
        pg.setShotsDefensed(shotsDefensed);
        pg.setShotsSaved(shotsSaved);
        pg.setGoalsAllowed(goalsAllowed);
        pg.setPlayer(player);

        pg = playerGames.createPlayerGame(pg);

        return "redirect:/home";
    }

    @PostMapping("editPlayerGame")
    public String editPlayerGame(Integer playerGameId, Integer shots, Integer goals, Integer assists, Integer dribbles, Integer passes, Integer passPercentage, Integer tackles, Integer interceptions, Integer shotsDefensed, Integer shotsSaved, Integer goalsAllowed, Integer playerId) {
        Player player = players.getPlayerById(playerId);
        PlayerGame pg = playerGames.getPlayerGameById(playerGameId);
        pg.setShots(shots);
        pg.setGoals(goals);
        pg.setAssists(assists);
        pg.setDribbles(dribbles);
        pg.setPasses(passes);
        pg.setPassPercentage(passPercentage);
        pg.setTackles(tackles);
        pg.setInterceptions(interceptions);
        pg.setShotsDefensed(shotsDefensed);
        pg.setShotsSaved(shotsSaved);
        pg.setGoalsAllowed(goalsAllowed);
        pg.setPlayer(player);
        playerGames.updatePlayerGame(pg);

        return "redirect:/home";
    }

    @PostMapping("deletePlayerGame")
    public String deletePlayerGame(Integer playerGameId){
        playerGames.deletePlayerGame(playerGameId);
        
        return "redirect:/home";
    }
}
