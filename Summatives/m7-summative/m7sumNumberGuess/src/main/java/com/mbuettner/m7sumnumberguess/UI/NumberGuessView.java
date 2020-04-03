/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.m7sumnumberguess.UI;

/**
 *
 * @author mbuet
 */
public class NumberGuessView {

    private UserIO io;

    public NumberGuessView(UserIO io) {
        this.io = io;
    }

    public void printWelcome() {
        io.print("--- Welcome To The Number Guessing Game ---");
        io.print("");
    }

    public void printRules() {
        io.print("The computer has generated a random 4 digit number.");
        io.print("Your task is to guess the number in the fewest amount of guesses possible!");
        io.print("");
        io.print("HINT: Digits will never be repeated!");
         io.print("");
        io.print("We will tell you how you did after each guess. If you receive partial credit, that means one of your numbers was correct, but in the wrong place!");
        io.print("If you receive full credit, that means that one of your numbers is correct and in the right place!");
        io.print("");
    }

    public String getGuess() {
        String guess = io.readGuess("Enter your guess: ");
        io.readString("");

        return guess;
    }

    public void printGuessResult(String guess, String guessResult, int guesses) {
        io.print("Your guess was " + guess + " and the result is: " + "\n" + guessResult);
        if (guesses == 1) {
            io.print("You have used " + guesses + " guess.");
            io.print("");
        } else {
            io.print("You have used " + guesses + " guesses.");
            io.print("");
        }
    }

    public void printSuccess(int guesses) {
        io.print("Success! You have successfully guessed the number! Great job!");
        io.print("");
        if (guesses == 1) {
            io.print("It took you " + guesses + " guess! That's going to be hard to beat!");
            io.print("");
        } else {
            io.print("It took you " + guesses + " guesses. Play again to see if you can beat that!");
            io.print("");
        }
    }

    public boolean playAgain() {
        boolean playAgain = false;
        String playerResponse = io.readString("Would you like to play again? (y/n)");

        do {
            if (playerResponse.equalsIgnoreCase("y")) {
                playAgain = true;
            } else if (!playerResponse.equalsIgnoreCase("n")) {
                playerResponse = io.readString("Input not recognized. Would you like to play again? (y/n) ");
            }
        } while (!playerResponse.equalsIgnoreCase("y") && !playerResponse.equalsIgnoreCase("n"));

        return playAgain;
    }
}
