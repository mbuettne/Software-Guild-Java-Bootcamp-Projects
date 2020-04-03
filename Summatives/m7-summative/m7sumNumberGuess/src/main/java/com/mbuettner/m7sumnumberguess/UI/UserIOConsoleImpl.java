/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.m7sumnumberguess.UI;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;
import java.util.regex.*;

/**
 *
 * @author mbuet
 */
public class UserIOConsoleImpl implements UserIO {

    Scanner input = new Scanner(System.in);

    public void print(String message) {
        System.out.println(message);
    }

    public String readGuess(String prompt) {
        String numString = "";
        do {
            System.out.println(prompt);
            numString = input.next();
            if (!Pattern.matches("[0-9]{4}", numString)) {
                System.out.println("Guess must be four number characters.");
            }
        } while (!Pattern.matches("[0-9]{4}", numString));
        return numString;
    }

    public double readDouble(String prompt) {
        double numDouble = 0;
        do {
            try {
                System.out.println(prompt);
                String numString = input.next();
                numDouble = Double.parseDouble(numString);
            } catch (NumberFormatException e) {
                System.out.println("Unknown Input. Please Try Again.");
            }
        } while (numDouble == 0);
        return numDouble;
    }

    public double readDouble(String prompt, double min, double max) {
        double numDouble = 0;

        do {
            try {
                System.out.println(prompt);
                String numString = input.next();
                numDouble = Double.parseDouble(numString);
                if (numDouble > max || numDouble < min) {
                    System.out.println("Input must be between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Unknown Input. Please Try Again.");
            }
        } while (numDouble < min || numDouble > max);
        return numDouble;

    }

    public float readFloat(String prompt) {
        float numFloat = 0;
        do {
            try {
                System.out.println(prompt);
                String numString = input.next();
                numFloat = Float.parseFloat(numString);
            } catch (NumberFormatException e) {
                System.out.println("Unknown Input. Please Try Again.");
            }
        } while (numFloat == 0);
        return numFloat;
    }

    public float readFloat(String prompt, float min, float max) {
        float numFloat = 0f;

        do {
            try {
                System.out.println(prompt);
                String numString = input.next();
                numFloat = Float.parseFloat(numString);
                if (numFloat > max || numFloat < min) {
                    System.out.println("Input must be between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Unknown Input. Please Try Again.");
            }
        } while (numFloat < min || numFloat > max);
        return numFloat;
    }

    public int readInt(String prompt) {
        int numInt = 0;
        do {
            try {
                System.out.println(prompt);
                String numString = input.next();
                numInt = Integer.parseInt(numString);
            } catch (NumberFormatException e) {
                System.out.println("Unknown Input. Please Try Again.");
            }
        } while (numInt == 0);
        return numInt;
    }

    public int readInt(String prompt, int min, int max) {
        int numInt = 0;

        do {
            try {
                System.out.println(prompt);
                String numString = input.next();
                numInt = Integer.parseInt(numString);
                if (numInt > max || numInt < min) {
                    System.out.println("Input must be between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Unknown Input. Please Try Again.");
            }
        } while (numInt < min || numInt > max);
        return numInt;
    }

    public long readLong(String prompt) {
        long numLong = 0;
        do {
            try {
                System.out.println(prompt);
                String numString = input.next();
                numLong = Long.parseLong(numString);
            } catch (NumberFormatException e) {
                System.out.println("Unknown Input. Please Try Again.");
            }
        } while (numLong == 0);
        return numLong;
    }

    public long readLong(String prompt, long min, long max) {
        long numLong = 0;

        do {
            try {
                System.out.println(prompt);
                String numString = input.next();
                numLong = Long.parseLong(numString);
                if (numLong > max || numLong < min) {
                    System.out.println("Input must be between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Unknown Input. Please Try Again.");
            }
        } while (numLong < min || numLong > max);
        return numLong;
    }

    public String readString(String prompt) {
        String stringIn;
        System.out.println(prompt);
        stringIn = input.nextLine();
        return stringIn;
    }

    @Override
    public BigDecimal readBigDecimal(String prompt) {
        BigDecimal numberBD = new BigDecimal("0.00");
        do {
            try {
                System.out.println(prompt);
                String stringIn = input.next();
                numberBD = new BigDecimal(stringIn);
                numberBD.setScale(2, RoundingMode.HALF_UP);
            } catch (NumberFormatException e) {
                System.out.println("Unknown Input. Please Try Again.");
            }
        } while (numberBD.compareTo(new BigDecimal("0.00")) == 0);

        return numberBD.abs();
    }
}
