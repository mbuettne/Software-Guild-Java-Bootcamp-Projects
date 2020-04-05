/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.m7sumnumberguess.Controllers;

import com.mbuettner.m7sumnumberguess.DTO.Game;
import com.mbuettner.m7sumnumberguess.DTO.Round;
import com.mbuettner.m7sumnumberguess.Service.NumberGuessService;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mbuet
 */
@RestController
@RequestMapping("/game")
public class NumberGuessController {

    
    NumberGuessService service;
    
    public NumberGuessController(NumberGuessService service){
        this.service = service;
    }

    @GetMapping
    public List<Game> getAllGames() {
        return service.getAllGames();
    }
    
    @GetMapping("/{gameId}")
    public ResponseEntity<Game> getGameByID(@PathVariable int gameId){
        Game game = service.getGameById(gameId);
        if(game == null){
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(game);
    }
    
    @GetMapping("/rounds/{gameId}")
    public ResponseEntity<List<Round>> getRoundsByGame(@PathVariable int gameId){
        Game game = service.getGameById(gameId);
        if(game == null){
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(service.getRoundsByGame(gameId));
    }

    // Still shows answer
    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Game> create() {
        String answer = service.generateNewAnswer();
        Game newGame = service.createNewGame(answer);
        Game game = service.getGameById(newGame.getGameId());
        return ResponseEntity.ok(game);
    }

    // Take in guess, map to correct game
    @PostMapping("/guess/{gameId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Round guess(@RequestBody Round round, @PathVariable int gameId) {
      //  Round newRound  = round;
        round.setGameId(gameId);
        round.setTimestamp(LocalDateTime.now());
    //    newRound.setWon(false);
        Game game = service.getGameByIdNoStars(gameId);
        String result = service.calculateRoundResult(round.getGuess(), game.getAnswer(), round);
        round.setResult(result);
        round = service.addRound(round);
        return service.getLatestRound(gameId);
    }
    
//    @PostMapping("/guess")
//    public Game checkWin(int gameId, String guess){
//        Game game = service.getGameById(gameId);
//        if(service.checkWin(guess, game)){
//            return game;
//        } else {
//            return null;
//        }
//    }
   
}
