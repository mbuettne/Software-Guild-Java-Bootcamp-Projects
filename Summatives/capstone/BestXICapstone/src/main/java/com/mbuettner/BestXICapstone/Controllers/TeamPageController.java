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
import javax.validation.ConstraintViolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author mbuet
 */
@Controller
public class TeamPageController {

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

    @GetMapping("/team")
    public String displayTeamPortal(String username, Model model) {
        User user = userService.getUserByUsername(username);
        Team team = teamService.getTeamById(user.getTeamid());
        List<Player> players = playerService.getAllPlayersByTeamSorted(user.getTeamid());
        Teamgame latestGame = teamgameService.getLatestGameByTeam(user.getTeamid());
        List<String> topScorers = playergameService.topScorerStringList(user.getTeamid());
        List<Teamgame> allgames = teamgameService.getAllGamesByTeamSorted(user.getTeamid());
        model.addAttribute("team", team);
        model.addAttribute("players", players);
        model.addAttribute("latestGame", latestGame);
        model.addAttribute("topScorers", topScorers);
        model.addAttribute("allgames", allgames);
        return "team";
    }

    @GetMapping("/bestxi")
    public String displayBestXI(Integer id, Model model) {
        List<Player> forwards = playerService.findBestForwards(id);
        List<Player> mids = playerService.findBestMidfielders(id);
        List<Player> defs = playerService.findBestDefenders(id);
        Player gk = playerService.findBestGoalkeeper(id);

        model.addAttribute("forwards", forwards);
        model.addAttribute("mids", mids);
        model.addAttribute("defs", defs);
        model.addAttribute("gk", gk);

        return "bestxi";
    }
}
