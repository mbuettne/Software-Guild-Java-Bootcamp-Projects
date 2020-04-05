/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.m7sumnumberguess.Service;

import com.mbuettner.m7sumnumberguess.DTO.Game;
import com.mbuettner.m7sumnumberguess.DTO.Round;
import com.mbuettner.m7sumnumberguess.Dao.NumberGuessDao;
import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 *
 * @author mbuet
 */
@Repository
public class NumberGuessService {

    NumberGuessDao dao;

    public NumberGuessService(NumberGuessDao dao) {
        this.dao = dao;
    }

    public String generateNewAnswer() {
        Random rand = new Random();
        int num1 = rand.nextInt(10);
        int num2 = rand.nextInt(10);
        int num3 = rand.nextInt(10);
        int num4 = rand.nextInt(10);

        while (num1 == num2 || num1 == num3 || num1 == num4 || num2 == num3 || num2 == num4 || num3 == num4) {
            if (num1 == num2 || num2 == num3 || num2 == num4) {
                num2 = rand.nextInt(10);
            } else if (num1 == num3 || num3 == num4) {
                num3 = rand.nextInt(10);
            } else if (num1 == num4) {
                num4 = rand.nextInt(10);
            }
        }

        String newAnswer = Integer.toString(num1) + Integer.toString(num2) + Integer.toString(num3) + Integer.toString(num4);

        return newAnswer;

    }

    public String calculateRoundResult(String guess, String answer, Round round) {
        int partial = 0;
        int full = 0;

        if (guess.charAt(0) == answer.charAt(0)) {
            full++;
        }
        if (guess.charAt(1) == answer.charAt(1)) {
            full++;
        }
        if (guess.charAt(2) == answer.charAt(2)) {
            full++;
        }
        if (guess.charAt(3) == answer.charAt(3)) {
            full++;
        }
        if (guess.charAt(0) == answer.charAt(1)) {
            partial++;
        }
        if (guess.charAt(0) == answer.charAt(2)) {
            partial++;
        }
        if (guess.charAt(0) == answer.charAt(3)) {
            partial++;
        }
        if (guess.charAt(1) == answer.charAt(0)) {
            partial++;
        }
        if (guess.charAt(1) == answer.charAt(2)) {
            partial++;
        }
        if (guess.charAt(1) == answer.charAt(3)) {
            partial++;
        }
        if (guess.charAt(2) == answer.charAt(0)) {
            partial++;
        }
        if (guess.charAt(2) == answer.charAt(1)) {
            partial++;
        }
        if (guess.charAt(2) == answer.charAt(3)) {
            partial++;
        }
        if (guess.charAt(3) == answer.charAt(0)) {
            partial++;
        }
        if (guess.charAt(3) == answer.charAt(1)) {
            partial++;
        }
        if (guess.charAt(3) == answer.charAt(2)) {
            partial++;
        }
        String roundResult = "e:" + full + ":p:" + partial;
        if (full == 4) {
            Game game = dao.getGameById(round.getGameId());
            game.setProgress("Finished");
            dao.updateGame(game);
        }
        return roundResult;
    }

//    public boolean checkWin(String guess, Game game){
//        boolean hasWon = false;
//        
//        if(guess.equals(game.getAnswer())){
//            hasWon = true;
//            game.setProgress("Finished");
//            dao.updateGame(game);
//        }
//        return hasWon;
//    }
    public Game createNewGame(String answer) {
        Game newGame = dao.createGame(answer);
        return newGame;
    }

    public Round addRound(Round round) {
        return dao.addRound(round);
    }

    public void updateRound(Round round) {
        dao.updateRound(round);
    }

    public Round getLatestRound(int gameId) {
        return dao.getLatestRound(gameId);
    }

    public List<Round> getRoundsByGame(int gameId) {
        return dao.getAllRoundsByGame(gameId);
    }

    public List<Game> getAllGames() {
        List<Game> games = dao.getAllGames();
        for (Game game : games) {
            if (game.getProgress().equals("In Progress")) {
                game.setAnswer("****");
            }
        }
        return games;
    }

    public Game getGameById(int id) {
        Game game = dao.getGameById(id);
        if (game.getProgress().equals("In Progress")) {
            game.setAnswer("****");
        }
        return game;
    }

    public Game getGameByIdNoStars(int id) {
        Game game = dao.getGameById(id);
        return game;
    }
}
