/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.rockpaperscissors;

import java.util.Scanner;
import java.util.Random;

/**
 *
 * @author mbuet
 */
public class rockPaperScissors {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random rdm = new Random();
        boolean isGame = true;

        System.out.println("*** Rock Paper Scissors ***");
        while (isGame) {

            // Get user input for number of rounds, validate
            System.out.println("How many rounds would you like to play? (1-10) ");
            int rounds = input.nextInt();
            while (rounds > 10 || rounds < 1) {
                System.out.println("Number of rounds must be between 1 and 10. Try again: ");
                rounds = input.nextInt();
            }
            System.out.println("");

            int count = 1;
            int ties = 0;
            int wins = 0;
            int losses = 0;

            // Gameplay
            while (count <= rounds) {
                System.out.println("Enter your choice: (1 = Rock / 2 = Paper / 3 = Scissors) ");
                int userChoice = input.nextInt();
                input.nextLine();

                // Validate user's choice
                while (userChoice > 3 || userChoice < 1) {
                    System.out.println("Please enter 1, 2, or 3: ");
                    userChoice = input.nextInt();
                    input.nextLine();
                }

                int compChoice = rdm.nextInt(3) + 1;

                //Compare user's choice to computer's choice
                
                int roundScore = checkRound(userChoice, compChoice);
                switch(roundScore){
                    case 1:
                        ties++;
                        break;
                    case 2:
                        wins++;
                        break;
                    case 3:
                        losses++;
                        break;
                }
                count++;
            }

            //Display game data
            System.out.println("Every round has been played! Here's how it went: ");
            System.out.println("Your Wins: " + wins);
            System.out.println("Your Losses: " + losses);
            System.out.println("Your Ties: " + ties);

            if (wins > losses) {
                System.out.println("You beat the computer! Good job!");
            } else if (wins < losses) {
                System.out.println("Ouch! You lost. Better luck next time!");
            } else {
                System.out.println("That was a very even game!");
            }
            System.out.println("");
            System.out.println("Would you like to play again? (y/n) ");
            String playAgain = input.nextLine();

            isGame = endGame(isGame, playAgain);

            

        }
    }
    
    public static int checkRound(int userChoice, int compChoice){
        int outcome = 0;
        
        if (compChoice == userChoice) {
                    System.out.println("This round is a tie! \n");
                    outcome = 1;
                } else if ((compChoice == 1 && userChoice == 2) || (compChoice == 2 && userChoice == 3) || (compChoice == 3 && userChoice == 1)) {
                    System.out.println("You won this round! \n");
                    outcome = 2;
                } else {
                    System.out.println("You lost this round! \n");
                    outcome = 3;
                }
        
        return outcome;
    }
    
    public static boolean endGame(boolean endGame, String playAgain){
        Scanner input = new Scanner(System.in);
        boolean isGame = true;
        
        while (endGame) {
                if (playAgain.equalsIgnoreCase("n")) {
                    System.out.println("Thanks for playing!");
                    isGame = false;
                    endGame = false;
                } else if (playAgain.equalsIgnoreCase("y")) {
                    endGame = false;
                } else {
                    System.out.println("Unrecognized input. Would you like to play again? (y/n)");
                    playAgain = input.nextLine();
                }
            }
        return isGame;
    }
}
