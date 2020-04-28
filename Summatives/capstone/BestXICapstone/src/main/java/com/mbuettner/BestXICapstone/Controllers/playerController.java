/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.BestXICapstone.Controllers;

import com.mbuettner.BestXICapstone.Entities.Player;
import com.mbuettner.BestXICapstone.Services.MapValidationService;
import com.mbuettner.BestXICapstone.Services.playerService;
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
@RequestMapping("/bestxi/player")
@CrossOrigin
public class playerController {

    @Autowired
    playerService playerService;

    @Autowired
    MapValidationService mapValidation;

    @PostMapping("")
    public ResponseEntity<?> createNewPlayer(@Valid @RequestBody Player player, BindingResult result) {
        ResponseEntity<?> errorMap = mapValidation.MapValidation(result);
        if (errorMap != null) {
            return errorMap;
        }
        Player player1 = playerService.saveOrUpdatePlayer(player);
        return new ResponseEntity<Player>(player, HttpStatus.CREATED);
    }
    
    @GetMapping("/trial/{teamid}/{playerposition}")
    public ResponseEntity<?> teamAndPosition(@PathVariable int teamid, @PathVariable String playerposition){
        List<Player> playersReturned = playerService.getByTeamAndPosition(teamid, playerposition);
        
        return new ResponseEntity<List<Player>>(playersReturned, HttpStatus.OK);
    }

    @PostMapping("/edit")
    public ResponseEntity<?> editPlayer(@Valid @RequestBody Player player, BindingResult result) {

        ResponseEntity<?> errorMap = mapValidation.MapValidation(result);
        if (errorMap != null) {
            return errorMap;
        }

        playerService.saveOrUpdatePlayer(player);
        return new ResponseEntity<Player>(player, HttpStatus.CREATED);
    }

    @GetMapping("/team/{teamid}")
    public ResponseEntity<?> getAllPlayersByTeam(@PathVariable int teamid) {
        List<Player> playersByTeam = playerService.getAllPlayersByTeam(teamid);
        return new ResponseEntity<List<Player>>(playersByTeam, HttpStatus.OK);
    }

    @GetMapping("/{playerid}")
    public ResponseEntity<?> getPlayerById(@PathVariable int playerid) {
        Player player = playerService.getPlayerById(playerid);
        return new ResponseEntity<Player>(player, HttpStatus.OK);
    }
    
   

    @DeleteMapping("/{playerid}")
    public ResponseEntity<?> deletePlayer(@PathVariable int playerid) {
        Player player = playerService.getPlayerById(playerid);

        if (player != null) {
            playerService.deletePlayerById(playerid);

            return new ResponseEntity<String>(player.getFirstname() + " " + player.getLastname() + " has been deleted.", HttpStatus.OK);
        } else {
             return new ResponseEntity<String>("Player not found.", HttpStatus.OK);
        }

    }
}
