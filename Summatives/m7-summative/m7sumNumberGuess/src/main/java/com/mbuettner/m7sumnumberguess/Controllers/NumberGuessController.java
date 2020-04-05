/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.m7sumnumberguess.Controllers;

import com.mbuettner.m7sumnumberguess.DTO.Game;
import com.mbuettner.m7sumnumberguess.DTO.Round;
import com.mbuettner.m7sumnumberguess.Service.NumberGuessService;
import com.mbuettner.m7sumnumberguess.UI.NumberGuessView;
import java.time.LocalDateTime;

/**
 *
 * @author mbuet
 */
public class NumberGuessController {
    NumberGuessView view;
    NumberGuessService service;

    public NumberGuessController(NumberGuessView view, NumberGuessService service) {
        this.view = view;
        this.service = service;
    }
    
     public void run() {

        boolean keepGoing = true;
        boolean playing = true;

        while (playing) {
            keepGoing = true;

           String answer = service.generateNewAnswer();
           Game newGame = service.createNewGame(answer);

            view.printWelcome();
            view.printRules();

            while (keepGoing) {
                
                String guess = view.getGuess();
                Round newRound = new Round(guess, newGame.getGameId());
                newRound.setTimestamp(LocalDateTime.now());
                service.addRound(newRound);

                if (service.checkWin(guess, newGame)) {
                    view.printSuccess();
                    playing = view.playAgain();
                    keepGoing = false;
                } else {
                    String guessResult = service.calculateRoundResult(guess, answer, newRound);
                    view.printGuessResult(guess, guessResult);
                }
            }
        }
        view.printGameOver();
    }
}
