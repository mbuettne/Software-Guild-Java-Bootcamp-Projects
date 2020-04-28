/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.BestXICapstone.Controllers;

import com.mbuettner.BestXICapstone.Entities.Player;
import com.mbuettner.BestXICapstone.Entities.Playergame;
import com.mbuettner.BestXICapstone.Services.MapValidationService;
import com.mbuettner.BestXICapstone.Services.playergameService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mbuet
 */
@RestController
@RequestMapping("/bestxi/playergame")
@CrossOrigin
public class playergameController {

    @Autowired
    playergameService playergameService;

    @Autowired
    MapValidationService mapValidation;

    @PostMapping("")
    public ResponseEntity<?> createNewPlayergame(@Valid @RequestBody Playergame playergame, BindingResult result) {
        ResponseEntity<?> errorMap = mapValidation.MapValidation(result);
        if (errorMap != null) {
            return errorMap;
        }
        Playergame playergame1 = playergameService.saveOrUpdatePlayergame(playergame);
        return new ResponseEntity<Playergame>(playergame, HttpStatus.CREATED);
    }

    @PostMapping("/edit")
    public ResponseEntity<?> editTeamGame(@Valid @RequestBody Playergame playergame, BindingResult result) {
        ResponseEntity<?> errorMap = mapValidation.MapValidation(result);
        if (errorMap != null) {
            return errorMap;
        } else {
            playergameService.saveOrUpdatePlayergame(playergame);
            return new ResponseEntity<Playergame>(playergame, HttpStatus.CREATED);
        }

    }

    @GetMapping("/topscorers/{teamid}")
    public ResponseEntity<?> findTopGoalScorers(@PathVariable int teamid) {
        Map topScorersMap = playergameService.findTopGoalScorers(teamid);
        Map<Integer, Player> topScorersTree = new TreeMap<Integer, Player>(topScorersMap);
        List<String> scorerStringList = new ArrayList<>();

        Player top = topScorersTree.get(1);
        Playergame pg1 = playergameService.totalStatsByPlayer(top.getPlayerid());
        int topGoals = pg1.getGoals();
        String topString = top.getFirstname() + " " + top.getLastname() + " - " + topGoals + " Goals";
        scorerStringList.add(topString);

        Player second = topScorersTree.get(2);
        Playergame pg2 = playergameService.totalStatsByPlayer(second.getPlayerid());
        int secondGoals = pg2.getGoals();
        String secondString = second.getFirstname() + " " + second.getLastname() + " - " + secondGoals + " Goals";
        scorerStringList.add(secondString);

        Player third = topScorersTree.get(3);
        Playergame pg3 = playergameService.totalStatsByPlayer(third.getPlayerid());
        int thirdGoals = pg3.getGoals();
        String thirdString = third.getFirstname() + " " + third.getLastname() + " - " + thirdGoals + " Goals";
        scorerStringList.add(thirdString);
        
        return new ResponseEntity<List<String>>(scorerStringList, HttpStatus.OK);

    }

    @GetMapping("/player/{playerid}")
    public ResponseEntity<?> getAllGamesByPlayer(@PathVariable int playerid) {
        List<Playergame> playergames = playergameService.getAllGamesByPlayer(playerid);
        return new ResponseEntity<List<Playergame>>(playergames, HttpStatus.OK);
    }

    @GetMapping("/player/all/{playerid}")
    public ResponseEntity<?> getTotalStatsByPlayer(@PathVariable int playerid) {
        Playergame totalStats = playergameService.totalStatsByPlayer(playerid);
        return new ResponseEntity<Playergame>(totalStats, HttpStatus.OK);
    }

    @GetMapping("/{playergameid}")
    public ResponseEntity<?> getPlayergameById(@PathVariable int playergameid) {
        Playergame pg = playergameService.getPlayergameById(playergameid);
        return new ResponseEntity<Playergame>(pg, HttpStatus.OK);
    }

    @DeleteMapping("/{playergameid}")
    public ResponseEntity<?> deletePlayerGame(@PathVariable int playergameid) {
        Playergame pg = playergameService.getPlayergameById(playergameid);

        if (pg != null) {
            playergameService.deletePlayergameById(playergameid);
            return new ResponseEntity<String>("Player game deleted.", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Player game not found.", HttpStatus.OK);
        }
    }

}
