/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticTacToe;

import java.util.Scanner;

/**
 *
 * @author mbuet
 */
public class ticTacToe {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String[][] wrapper = {
            {"00", "01", "02"},
            {"10", "11", "12"},
            {"20", "21", "22"}
        };
        
        for(int i = 0; i < wrapper.length; i++){
            for(int j = 0; j < wrapper[i].length; j++){
                System.out.print(wrapper[i][j] + " ");
            }
            System.out.println();
        }

    }
}
