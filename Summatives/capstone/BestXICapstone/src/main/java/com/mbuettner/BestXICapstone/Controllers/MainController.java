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
import com.mbuettner.BestXICapstone.Services.playerService;
import com.mbuettner.BestXICapstone.Services.playergameService;
import com.mbuettner.BestXICapstone.Services.teamService;
import com.mbuettner.BestXICapstone.Services.teamgameService;
import com.mbuettner.BestXICapstone.Services.userService;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author mbuet
 */
@Controller
public class MainController {
    
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
     
     
    @GetMapping("/")
    public String dashboardPage(Model model){
        List<Player> players = playerService.getAllPlayersByTeam(1);
        Teamgame latestGame = teamgameService.getLatestGameByTeam(1);
        List<String> topScorers = playergameService.topScorerStringList(1);
        model.addAttribute("players", players);
        model.addAttribute("latestGame", latestGame);
        model.addAttribute("topScorers", topScorers);
        return "index";
    }
    
    @GetMapping("/add")
    public String addInfoPage(Model model){
         List<Player> players = playerService.getAllPlayersByTeam(1);
         model.addAttribute("players", players);
         model.addAttribute("teamid", 1);
         return "add";
    }
    
    @PostMapping("addTeamgame")
    public String addTeamgame(Teamgame teamgame){
        teamgameService.saveOrUpdateTeamgame(teamgame);
        return "add";
    }
    
    @PostMapping("addPlayer")
    public String addPlayer(Player player){
        playerService.saveOrUpdatePlayer(player);
        return "add";
    }
    
    @PostMapping("addPlayergame")
    public String addPlayergame(Playergame playergame){
        playergameService.saveOrUpdatePlayergame(playergame);
        return "add";
    }
    
    @GetMapping("/playerinfo")
    public String displayPlayerInfoPage(Integer id, Model model){
        Player player = playerService.getPlayerById(id);
        Playergame seasonStats = playergameService.totalStatsByPlayer(player.getPlayerid());
        
        List<Playergame> games = playergameService.getAllGamesByPlayer(player.getPlayerid());
        
        List<String> dominantfoot = new ArrayList<>();
        dominantfoot.add("Left");
        dominantfoot.add("Right");
        
        List<String> playerpositions = new ArrayList<>();
        playerpositions.add("Forward");
        playerpositions.add("Midfield");
        playerpositions.add("Defense");
        playerpositions.add("Goalkeeper");
        
        model.addAttribute("player", player);
        model.addAttribute("seasonStats", seasonStats);
        model.addAttribute("dominantfoot", dominantfoot);
        model.addAttribute("playerpositions", playerpositions);
        model.addAttribute("games", games);
        
        return "playerinfo";
    }
    
    @PostMapping("editPlayer")
    public String updatePlayer(Player player){
        playerService.saveOrUpdatePlayer(player);
        return "redirect:/";
    }
    
    @GetMapping("/deleteplayer")
    public String deletePlayer(Integer id, Model model){
        Player toDelete = playerService.getPlayerById(id);
        model.addAttribute("player", toDelete);
        
        return "deleteplayer";
    }
    
    @PostMapping("/deleteplayer")
    public String confirmDeletePlayer(Integer id){
        playerService.deletePlayerById(id);
        
        return "redirect:/";
    }
    
    @GetMapping("/teamgameinfo")
    public String displayTeamgameInfoPage(Integer id, Model model){
        Teamgame teamgame = teamgameService.getTeamgameById(id);
        
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
        
        return "/teamgameinfo";
    }
    
    @PostMapping("editTeamgame")
    public String updateTeamgame(Teamgame teamgame){
        teamgameService.saveOrUpdateTeamgame(teamgame);
        
        return "redirect:/";
    }
    
     @GetMapping("/deleteteamgame")
    public String deleteTeamgame(Integer id, Model model){
        Teamgame toDelete = teamgameService.getTeamgameById(id);
        model.addAttribute("teamgame", toDelete);
        
        return "deleteteamgame";
    }
    
    @PostMapping("/deleteteamgame")
    public String confirmDeleteTeamgame(Integer id){
        teamgameService.deleteTeamgame(id);
        
        return "redirect:/";
    }
    
    @GetMapping("/team")
    public String displayTeamPortal(Model model){
        Team team = teamService.getTeamById(1);
        List<Player> players = playerService.getAllPlayersByTeam(1);
        Teamgame latestGame = teamgameService.getLatestGameByTeam(1);
        List<String> topScorers = playergameService.topScorerStringList(1);
        List<Teamgame> allgames = teamgameService.getAllGamesByTeam(1);
        model.addAttribute("team", team);
        model.addAttribute("players", players);
        model.addAttribute("latestGame", latestGame);
        model.addAttribute("topScorers", topScorers);
        model.addAttribute("allgames", allgames);
        return "team";
    }
    
    @GetMapping("/bestxi")
    public String displayBestXI(Integer id, Model model){
        
        
        
        return "bestxi";
    }
}
