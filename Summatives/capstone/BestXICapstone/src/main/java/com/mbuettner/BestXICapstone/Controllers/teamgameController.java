/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.BestXICapstone.Controllers;

import com.mbuettner.BestXICapstone.Entities.Player;
import com.mbuettner.BestXICapstone.Entities.Teamgame;
import com.mbuettner.BestXICapstone.Services.MapValidationService;
import com.mbuettner.BestXICapstone.Services.teamgameService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@RequestMapping("/bestxi/teamgame")
@CrossOrigin
public class teamgameController {

    @Autowired
    teamgameService teamgameService;

    @Autowired
    MapValidationService mapValidation;

    @PostMapping("")
    public ResponseEntity<?> createNewTeamgame(@Valid @RequestBody Teamgame teamgame, BindingResult result) {
        ResponseEntity<?> errorMap = mapValidation.MapValidation(result);
        if (errorMap != null) {
            return errorMap;
        }
        Teamgame teamgame1 = teamgameService.saveOrUpdateTeamgame(teamgame);
        return new ResponseEntity<Teamgame>(teamgame, HttpStatus.CREATED);
    }

    @PostMapping("/edit")
    public ResponseEntity<?> editTeamGame(@Valid @RequestBody Teamgame teamgame, BindingResult result) {
        ResponseEntity<?> errorMap = mapValidation.MapValidation(result);
        if (errorMap != null) {
            return errorMap;
        }
        teamgameService.saveOrUpdateTeamgame(teamgame);
        return new ResponseEntity<Teamgame>(teamgame, HttpStatus.CREATED);
    }
    
    @GetMapping("/team/{teamid}")
    public ResponseEntity<?> getAllGamesByTeam(@PathVariable int teamid){
        List<Teamgame> teamgames = teamgameService.getAllGamesByTeam(teamid);
        
        return new ResponseEntity<List<Teamgame>>(teamgames, HttpStatus.OK);
    }
    
        @GetMapping("/team/latest/{teamid}")
    public ResponseEntity<?> getLatestgamesByTeam(@PathVariable int teamid){
        Teamgame teamgame = teamgameService.getLatestGameByTeam(teamid);
        
        return new ResponseEntity<Teamgame>(teamgame, HttpStatus.OK);
    }
    
    @GetMapping("/{teamgameid}")
    public ResponseEntity<?> getTeamgameById(@PathVariable int teamgameid){
        Teamgame tg = teamgameService.getTeamgameById(teamgameid);
        return new ResponseEntity<Teamgame>(tg, HttpStatus.OK);
    }
    
    @DeleteMapping("/{teamgameid}")
    public ResponseEntity<?> deleteTeamgameById(@PathVariable int teamgameid){
        Teamgame tg = teamgameService.getTeamgameById(teamgameid);
        
        if(tg != null){
            teamgameService.deleteTeamgame(teamgameid);
            return new ResponseEntity<String>("Game against " + tg.getOpponent() + " on " + tg.getGamedate() + " has been deleted.", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Game not found.", HttpStatus.OK);
        }
    }

}
