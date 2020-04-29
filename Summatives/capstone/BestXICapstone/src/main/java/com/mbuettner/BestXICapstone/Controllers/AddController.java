/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.BestXICapstone.Controllers;

import com.mbuettner.BestXICapstone.Entities.Player;
import com.mbuettner.BestXICapstone.Entities.Playergame;
import com.mbuettner.BestXICapstone.Entities.Team;
import com.mbuettner.BestXICapstone.Entities.Teamgame;
import com.mbuettner.BestXICapstone.Entities.User;
import com.mbuettner.BestXICapstone.Services.playerService;
import com.mbuettner.BestXICapstone.Services.playergameService;
import com.mbuettner.BestXICapstone.Services.teamService;
import com.mbuettner.BestXICapstone.Services.teamgameService;
import com.mbuettner.BestXICapstone.Services.userService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author mbuet
 */
@Controller
public class AddController {

    @Autowired
    playerService playerService;

    @Autowired
    playergameService playergameService;

    @Autowired
    teamService teamService;

    @Autowired
    teamgameService teamgameService;

    @Autowired
    userService userService;

    Set<ConstraintViolation<Team>> violations = new HashSet<>();
    Set<ConstraintViolation<Player>> playerviolations = new HashSet<>();
    Set<ConstraintViolation<Playergame>> playergameviolations = new HashSet<>();
    Set<ConstraintViolation<Teamgame>> teamgameviolations = new HashSet<>();

    @GetMapping("/add")
    public String addInfoPage(String username, Model model) {
        User user = userService.getUserByUsername(username);
        List<Player> players = playerService.getAllPlayersByTeam(user.getTeamid());
        model.addAttribute("user", user);
        model.addAttribute("players", players);
        model.addAttribute("teamid", user.getTeamid());
        model.addAttribute("errors", violations);

        return "add";
    }

    @PostMapping("addTeamgame")
    public String addTeamgame(Teamgame teamgame, Model model, HttpServletRequest request) {
        String username = request.getParameter("username");
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        teamgameviolations = validate.validate(teamgame);

        if (teamgameviolations.isEmpty()) {
            teamgameService.saveOrUpdateTeamgame(teamgame);
            return "redirect:/add?username=" + username;
        } else {
            User user = userService.getUserByUsername(username);
            model.addAttribute("user", user);
            List<Player> players = playerService.getAllPlayersByTeam(user.getTeamid());
            model.addAttribute("players", players);
            model.addAttribute("teamid", user.getTeamid());
            model.addAttribute("errors", teamgameviolations);
            return "add";
        }
    }

    @PostMapping("addPlayer")
    public String addPlayer(Player player, Model model, HttpServletRequest request) {
        String username = request.getParameter("username");
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        playerviolations = validate.validate(player);
        if (playerviolations.isEmpty()) {
            playerService.saveOrUpdatePlayer(player);
            return "redirect:/add?username=" + username;
        } else {
            User user = userService.getUserByUsername(username);
            model.addAttribute("user", user);
            List<Player> players = playerService.getAllPlayersByTeam(user.getTeamid());
            model.addAttribute("players", players);
            model.addAttribute("teamid", user.getTeamid());
            model.addAttribute("errors", playerviolations);
            return "add";
        }
    }

    @PostMapping("addPlayergame")
    public String addPlayergame(Playergame playergame, Model model, HttpServletRequest request) {
         String username = request.getParameter("username");
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        playergameviolations = validate.validate(playergame);
        if (playergameviolations.isEmpty()) {
            playergameService.saveOrUpdatePlayergame(playergame);
            return "redirect:/add?username=" + username;
        } else {
           User user = userService.getUserByUsername(username);
            model.addAttribute("user", user);
            List<Player> players = playerService.getAllPlayersByTeam(user.getTeamid());
            model.addAttribute("players", players);
            model.addAttribute("teamid", user.getTeamid());
            model.addAttribute("errors", playergameviolations);
            return "add";
        }
    }

}
