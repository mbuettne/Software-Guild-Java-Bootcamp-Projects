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

    public NumberGuessController(NumberGuessService service) {
        this.service = service;
    }

    
    //Get List Of All Games
    @GetMapping
    public List<Game> getAllGames() {
        return service.getAllGames();
    }

    //Get Game By GameId
    @GetMapping("/{gameId}")
    public ResponseEntity<Game> getGameByID(@PathVariable int gameId) {
        List<Game> games = service.getAllGames();
        Game gameToReturn = null;

        for (Game game : games) {
            if (game.getGameId() != gameId) {

            } else if (game.getGameId() == gameId) {
                gameToReturn = game;
                break;
            }
        }
        if (gameToReturn == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        } else {
            gameToReturn = service.getGameById(gameId);
            return ResponseEntity.ok(gameToReturn);
        }

    }

    //Get All Rounds By GameId
    @GetMapping("/rounds/{gameId}")
    public ResponseEntity<List<Round>> getRoundsByGame(@PathVariable int gameId) {
        List<Game> games = service.getAllGames();
        Game gameToReturn = null;

        for (Game game : games) {
            if (game.getGameId() != gameId) {

            } else if (game.getGameId() == gameId) {
                gameToReturn = game;
                break;
            }
        }
        if (gameToReturn == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        } else {
            gameToReturn = service.getGameById(gameId);
            return ResponseEntity.ok(service.getRoundsByGame(gameId));
        }
    }

    //Begin New Game
    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Game> create() {
        String answer = service.generateNewAnswer();
        Game newGame = service.createNewGame(answer);
        Game game = service.getGameById(newGame.getGameId());
        return ResponseEntity.ok(game);
    }

    // Take In Guess, Map To Correct Game
    @PostMapping("/guess/{gameId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Round guess(@RequestBody Round round, @PathVariable int gameId) {
        round.setGameId(gameId);
        round.setTimestamp(LocalDateTime.now());
        Game game = service.getGameByIdNoStars(gameId);
        if (game.getProgress().equals("Finished")) {
            Round gameComplete = round;
            gameComplete.setResult("Game already complete. Try guessing for another game or start a new one!");
            return gameComplete;
        }
        String result = service.calculateRoundResult(round.getGuess(), game.getAnswer(), round);
        round.setResult(result);
        round = service.addRound(round);
        return service.getLatestRound(gameId);
    }

}
