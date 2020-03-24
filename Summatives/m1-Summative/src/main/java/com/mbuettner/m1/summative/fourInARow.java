/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.m1.summative;

import java.util.Scanner;
import java.util.Random;

/**
 *
 * @author mbuet
 */
public class fourInARow {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean isGame = true;
        boolean playerTurn = firstPlayer();
        int numOfTurns = 0;
        String playAgain = "";

        //Player set up
        System.out.print("Player #1, enter your name: ");
        String player1 = input.nextLine();
        System.out.println("Hello, " + player1);
        System.out.println("");
        System.out.print("Player #2, enter your name: ");
        String player2 = input.nextLine();
        System.out.println("Hello, " + player2);
        System.out.println("");

        System.out.println("Randomizing playing order....");
        if (playerTurn) {
            System.out.println("It's " + player1 + "'s turn.");
        } else {
            System.out.println("It's " + player2 + "'s turn.");
        }
        System.out.println("");
        System.out.println("");

        char[][] gameBoard = new char[6][7];
        createBoard(gameBoard);
        showBoard(gameBoard);

        while (isGame) {
            System.out.println(playerTurn(playerTurn, player1, player2));
            playChip(gameBoard, playerTurn);
            showBoard(gameBoard);
            numOfTurns++;

            if (checkWin(gameBoard, playerTurn)) {
                if (playerTurn) {
                    System.out.println(player1 + ", you win!");
                    System.out.println("");
                    System.out.println("Would you like to play again? (y/n)");
                    playAgain = input.nextLine();
                } else {
                    System.out.println(player2 + ", you win!");
                    System.out.println("");
                    System.out.println("Would you like to play again? (y/n)");
                    playAgain = input.nextLine();
                }

                isGame = endGame(playAgain);
                if(isGame){
                    createBoard(gameBoard);
                    showBoard(gameBoard);
                }

               
            } else {
                playerTurn = turnChange(playerTurn);
            }

            if (numOfTurns >= 35) {
                System.out.println("Looks like this game ends in a draw!");
            }

        }

    }
    //Game Board Methods

    public static void createBoard(char[][] gameBoard) {
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                gameBoard[i][j] = '-';
            }
        }
    }

    public static void showBoard(char[][] gameBoard) {
        System.out.println("1 2 3 4 5 6 7 ");
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                System.out.print(gameBoard[i][j] + " ");
            }
            System.out.println();
        }
    }

    //Player Turn Methods
    public static boolean firstPlayer() {
        Random rdm = new Random();
        boolean player = true;

        int playerNum = rdm.nextInt(2);

        if (playerNum == 1) {
            player = false;
        }

        return player;
    }

    public static String playerTurn(boolean currentTurn, String player1, String player2) {
        String play = "";

        if (currentTurn) {
            play = player1 + ", choose a column: ";
            System.out.println("");
        } else {
            play = player2 + ", choose a column: ";
            System.out.println("");
        }

        return play;
    }

    public static boolean turnChange(boolean currentTurn) {
        boolean nextTurn = true;

        if (currentTurn) {
            nextTurn = false;
        }

        return nextTurn;
    }

    //Gameplay Methods
    public static void playChip(char[][] gameBoard, boolean playerTurn) {
        Scanner input = new Scanner(System.in);
        int col = input.nextInt() - 1;
        int rowCounter = 1;
        char chip = '-';
        if (playerTurn) {
            chip = 'X';
        } else {
            chip = 'O';
        }

        while (true) {
            if (col < 0 || col >= 7) {
                System.out.println("That is not a valid column. Please try again: ");
                col = input.nextInt() - 1;
            } else if (gameBoard[5][col] == '-') {
                gameBoard[5][col] = chip;
                break;
            } else if (gameBoard[5][col] != '-') {
                if (gameBoard[5 - rowCounter][col] == '-') {
                    gameBoard[5 - rowCounter][col] = chip;
                    break;
                }
            }
            rowCounter++;

            if (rowCounter > 5) {
                System.out.println("That column is full. Please pick another: ");
                col = input.nextInt() - 1;
            }
        }
    }

    public static boolean checkHoriz(char[][] gameBoard, boolean playerTurn) {
        boolean hasWon = false;
        char chip = '-';
        if (playerTurn) {
            chip = 'X';
        } else {
            chip = 'O';
        }

        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length - 3; j++) {
                if (gameBoard[i][j] == chip && gameBoard[i][j + 1] == chip && gameBoard[i][j + 2] == chip && gameBoard[i][j + 3] == chip) {
                    hasWon = true;
                }
            }

        }

        return hasWon;
    }

    public static boolean checkVert(char[][] gameBoard, boolean playerTurn) {
        boolean hasWon = false;
        char chip = '-';
        if (playerTurn) {
            chip = 'X';
        } else {
            chip = 'O';
        }

        for (int i = 0; i < gameBoard.length - 3; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                if (gameBoard[i][j] == chip && gameBoard[i + 1][j] == chip && gameBoard[i + 2][j] == chip && gameBoard[i + 3][j] == chip) {
                    hasWon = true;
                }
            }
        }

        return hasWon;
    }

    public static boolean checkDiagonal(char[][] gameBoard, boolean playerTurn) {
        boolean hasWon = false;
        char chip = '-';
        if (playerTurn) {
            chip = 'X';
        } else {
            chip = 'O';
        }

        for (int i = 0; i < gameBoard.length - 3; i++) {
            for (int j = 0; j < gameBoard[i].length - 3; j++) {
                if (gameBoard[i][j] == chip && gameBoard[i + 1][j + 1] == chip && gameBoard[i + 2][j + 2] == chip && gameBoard[i + 3][j + 3] == chip) {
                    hasWon = true;
                }
            }
        }

        for (int i = 5; i < gameBoard.length - 3; i--) {
            for (int j = 0; j < gameBoard[0].length - 3; j++) {
                if (gameBoard[i][j] == chip && gameBoard[i - 1][j + 1] == chip && gameBoard[i - 2][j + 2] == chip && gameBoard[i - 3][j + 3] == chip) {
                    hasWon = true;
                }
            }
        }

        return hasWon;
    }

    public static boolean checkWin(char[][] gameBoard, boolean playerTurn) {
        boolean hasWon = false;
        if (checkHoriz(gameBoard, playerTurn) || checkVert(gameBoard, playerTurn) || checkDiagonal(gameBoard, playerTurn)) {
            hasWon = true;
        }

        return hasWon;
    }

    public static boolean endGame(String playAgain) {
        Scanner input = new Scanner(System.in);
        boolean startOver = true;
        boolean isGame = true;

        while (startOver) {
            if (playAgain.equalsIgnoreCase("n")) {
                System.out.println("Thanks for playing!");
                isGame = false;
                startOver = false;
            } else if (playAgain.equalsIgnoreCase("y")) {
                startOver = false;
                
            } else {
                System.out.println("Unrecognized input. Would you like to play again? (y/n)");
                playAgain = input.nextLine();
            }
        }
        return isGame;
    }

}
