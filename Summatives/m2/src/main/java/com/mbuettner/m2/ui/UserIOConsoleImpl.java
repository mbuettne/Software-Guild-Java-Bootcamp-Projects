/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.m2.ui;

import java.util.Scanner;

/**
 *
 * @author mbuet
 */
public class UserIOConsoleImpl implements UserIO{
    Scanner input = new Scanner(System.in);

    public void print(String message) {
        System.out.println(message);
    }

    public double readDouble(String prompt) {
        double doubleIn;
        System.out.println(prompt);
        doubleIn = input.nextDouble();
        input.nextLine();
        return doubleIn;
    }

    public double readDouble(String prompt, double min, double max) {
        double doubleIn;
        System.out.println(prompt);
        doubleIn = input.nextDouble();

        while (doubleIn < min || doubleIn > max) {
            System.out.println("Invalid input. Please enter a number between " + min + " and " + max);
            doubleIn = input.nextDouble();
        }

        return doubleIn;

    }

    public float readFloat(String prompt) {
        float floatIn;
        System.out.println(prompt);
        floatIn = input.nextFloat();
        input.nextLine();
        return floatIn;
    }

    public float readFloat(String prompt, float min, float max) {
        float floatIn;
        System.out.println(prompt);
        floatIn = input.nextFloat();

        while (floatIn < min || floatIn > max) {
            System.out.println("Invalid input. Please enter a number between " + min + " and " + max);
            floatIn = input.nextFloat();
        }

        return floatIn;
    }

    public int readInt(String prompt) {
        int intIn;
        System.out.println(prompt);
        intIn = input.nextInt();
        input.nextLine();
        return intIn;
    }

    public int readInt(String prompt, int min, int max) {
        int intIn;
        System.out.println(prompt);
        intIn = input.nextInt();

        while (intIn < min || intIn > max) {
            System.out.println("Invalid input. Please enter a number between " + min + " and " + max);
            intIn = input.nextInt();
        }

        return intIn;
    }

    public long readLong(String prompt) {
        long longIn;
        System.out.println(prompt);
        longIn = input.nextLong();
        input.nextLine();
        return longIn;
    }

    public long readLong(String prompt, long min, long max) {
        long longIn;
        System.out.println(prompt);
        longIn = input.nextLong();

        while (longIn < min || longIn > max) {
            System.out.println("Invalid input. Please enter a number between " + min + " and " + max);
            longIn = input.nextLong();
        }

        return longIn;
    }

    public String readString(String prompt) {
        String stringIn;
        System.out.println(prompt);
        stringIn = input.nextLine();
        return stringIn;
    }
}
