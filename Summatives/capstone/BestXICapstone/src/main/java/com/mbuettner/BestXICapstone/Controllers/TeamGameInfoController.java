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
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author mbuet
 */
@Controller
public class TeamGameInfoController {

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

    @GetMapping("/teamgameinfo")
    public String displayTeamgameInfoPage(Integer id, String username, Model model) {
        Teamgame teamgame = teamgameService.getTeamgameById(id);
        User user = userService.getUserByUsername(username);

        List<String> locations = new ArrayList<>();
        locations.add("Home");
        locations.add("Away");

        List<String> results = new ArrayList<>();
        results.add("Win");
        results.add("Loss");
        results.add("Draw");

        model.addAttribute("teamgame", teamgame);
        model.addAttribute("locations", locations);
        model.addAttribute("results", results);
        model.addAttribute("user", user);
        model.addAttribute("errors", violations);

        return "/teamgameinfo";
    }

    @PostMapping("editTeamgame")
    public String updateTeamgame(Teamgame teamgame, Model model, HttpServletRequest request) {
        String username = request.getParameter("username");
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        teamgameviolations = validate.validate(teamgame);

        if (teamgameviolations.isEmpty()) {
            
            teamgameService.saveOrUpdateTeamgame(teamgame);
            return "redirect:/?username=" + username;
        } else {
            Teamgame teamgame1 = teamgameService.getTeamgameById(teamgame.getTeamgameid());

            List<String> locations = new ArrayList<>();
            locations.add("Home");
            locations.add("Away");

            List<String> results = new ArrayList<>();
            results.add("Win");
            results.add("Loss");
            results.add("Draw");

            model.addAttribute("user", userService.getUserByUsername(username));
            model.addAttribute("teamgame", teamgame1);
            model.addAttribute("locations", locations);
            model.addAttribute("results", results);
            model.addAttribute("errors", teamgameviolations);

            return "/teamgameinfo";
        }
    }

    @GetMapping("/deleteteamgame")
    public String deleteTeamgame(Integer id, String username, Model model) {
        Teamgame toDelete = teamgameService.getTeamgameById(id);
         User user = userService.getUserByUsername(username);
        model.addAttribute("user", user);
        model.addAttribute("teamgame", toDelete);

        return "deleteteamgame";
    }

    @PostMapping("/deleteteamgame")
    public String confirmDeleteTeamgame(Integer id, HttpServletRequest request) {
        String username = request.getParameter("username");
        teamgameService.deleteTeamgame(id);

        return "redirect:/?username=" + username;
    }
}
