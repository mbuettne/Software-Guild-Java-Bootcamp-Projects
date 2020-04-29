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
public class PlayerInfoController {

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

    @GetMapping("/playerinfo")
    public String displayPlayerInfoPage(Integer id, String username, Model model) {
         User user = userService.getUserByUsername(username);
        Player player = playerService.getPlayerById(id);
        Playergame seasonStats = playergameService.totalStatsByPlayer(player.getPlayerid());

        List<Playergame> games = playergameService.getAllGamesByPlayer(player.getPlayerid());

        List<String> dominantfoot = new ArrayList<>();
        dominantfoot.add("Left");
        dominantfoot.add("Right");

        List<String> playerpositions = new ArrayList<>();
        playerpositions.add("Forward");
        playerpositions.add("Midfield");
        playerpositions.add("Defender");
        playerpositions.add("Goalkeeper");

        model.addAttribute("player", player);
        model.addAttribute("user", user);
        model.addAttribute("seasonStats", seasonStats);
        model.addAttribute("dominantfoot", dominantfoot);
        model.addAttribute("playerpositions", playerpositions);
        model.addAttribute("games", games);
        model.addAttribute("errors", violations);

        return "playerinfo";
    }

    @PostMapping("editPlayer")
    public String updatePlayer(Player player, Model model, HttpServletRequest request) {
        String username = request.getParameter("username");
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        playerviolations = validate.validate(player);
        if (playerviolations.isEmpty()) {
            playerService.saveOrUpdatePlayer(player);
            return "redirect:/?username=" + username;
        } else {
            Player sameplayer = playerService.getPlayerById(player.getPlayerid());
            Playergame seasonStats = playergameService.totalStatsByPlayer(sameplayer.getPlayerid());

            List<Playergame> games = playergameService.getAllGamesByPlayer(sameplayer.getPlayerid());

            List<String> dominantfoot = new ArrayList<>();
            dominantfoot.add("Left");
            dominantfoot.add("Right");

            List<String> playerpositions = new ArrayList<>();
            playerpositions.add("Forward");
            playerpositions.add("Midfield");
            playerpositions.add("Defender");
            playerpositions.add("Goalkeeper");

            model.addAttribute("player", sameplayer);
            model.addAttribute("seasonStats", seasonStats);
            model.addAttribute("dominantfoot", dominantfoot);
            model.addAttribute("playerpositions", playerpositions);
            model.addAttribute("games", games);
            model.addAttribute("errors", playerviolations);
            return "playerinfo";
        }
    }

    @GetMapping("/deleteplayer")
    public String deletePlayer(Integer id, String username, Model model) {
        Player toDelete = playerService.getPlayerById(id);
        User user = userService.getUserByUsername(username);
        model.addAttribute("user", user);
        model.addAttribute("player", toDelete);

        return "deleteplayer";
    }

    @PostMapping("/deleteplayer")
    public String confirmDeletePlayer(Integer id, HttpServletRequest request) {
        String username = request.getParameter("username");
        playerService.deletePlayerById(id);

        return "redirect:/?username=" + username;
    }

}
