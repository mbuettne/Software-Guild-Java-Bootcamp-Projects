/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.m7sumnumberguess.Service;

import java.util.Random;

/**
 *
 * @author mbuet
 */
public class NumberGuessService {

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

    public String calculateRoundResult(String guess, String answer) {
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

        return "e:" + full + ":p: " + partial;
    }
    
    public boolean checkWin(String guess, String answer){
        boolean hasWon = false;
        
        if(guess.equals(answer)){
            hasWon = true;
        }
        return hasWon;
    }
}
