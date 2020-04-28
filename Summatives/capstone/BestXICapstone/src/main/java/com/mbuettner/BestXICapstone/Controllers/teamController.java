/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.BestXICapstone.Controllers;

import com.mbuettner.BestXICapstone.Entities.Team;
import com.mbuettner.BestXICapstone.Entities.Teamgame;
import com.mbuettner.BestXICapstone.Services.MapValidationService;
import com.mbuettner.BestXICapstone.Services.teamService;
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
@RequestMapping("/bestxi/team")
@CrossOrigin
public class teamController {

    @Autowired
    teamService teamService;

    @Autowired
    MapValidationService mapValidation;

    @PostMapping("")
    public ResponseEntity<?> createNewTeam(@Valid @RequestBody Team team, BindingResult result) {
        ResponseEntity<?> errorMap = mapValidation.MapValidation(result);
        if (errorMap != null) {
            return errorMap;
        }
        Team team1 = teamService.saveOrUpdateTeam(team);
        return new ResponseEntity<Team>(team, HttpStatus.CREATED);
    }

    @PostMapping("/edit")
    public ResponseEntity<?> editTeam(@Valid @RequestBody Team team, BindingResult result) {
        ResponseEntity<?> errorMap = mapValidation.MapValidation(result);
        if (errorMap != null) {
            return errorMap;
        }
        teamService.saveOrUpdateTeam(team);
        return new ResponseEntity<Team>(team, HttpStatus.CREATED);
    }
    
    @GetMapping("/{teamid}")
    public ResponseEntity<?> getTeamById(@PathVariable int teamid){
        Team team = teamService.getTeamById(teamid);
        return new ResponseEntity<Team>(team, HttpStatus.OK);
    }
    
    @DeleteMapping("/{teamid}")
    public ResponseEntity<?> deleteTeamById(@PathVariable int teamid){
        Team team = teamService.getTeamById(teamid);
        
        if(team != null){
            teamService.deleteTeam(teamid);
            return new ResponseEntity<String>(team.getTeamname() + " has been deleted.", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Team not found.", HttpStatus.OK);
        }
    }

}
