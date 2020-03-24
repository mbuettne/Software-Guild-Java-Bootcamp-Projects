/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.m3.summative.dto;

import com.mbuettner.m3.summative.ui.UserIOConsoleImpl;
import java.math.BigDecimal;

/**
 *
 * @author mbuet
 */
public class Change {
    UserIOConsoleImpl io = new UserIOConsoleImpl();
    private  int quarters = 0;
    private  int dimes = 0;
    private  int nickels = 0;
    private  int pennies = 0;

    public int getQuarters() {
        return quarters;
    }

    public int getDimes() {
        return dimes;
    }

    public int getNickels() {
        return nickels;
    }

    public int getPennies() {
        return pennies;
    }

    public void makeChange(BigDecimal userMoney) {
        BigDecimal hundred = new BigDecimal("100.00");
        BigDecimal zero = new BigDecimal("0.00");
        BigDecimal quarter = new BigDecimal(Coins.QUARTER.label);
        BigDecimal dime = new BigDecimal(Coins.DIME.label);
        BigDecimal nickel = new BigDecimal(Coins.NICKEL.label);
        BigDecimal penny = new BigDecimal(Coins.PENNY.label);
        BigDecimal userPennies = userMoney.multiply(hundred);
        
        do{
            if(userPennies.compareTo(quarter)>=0){
                quarters++;
                userPennies = userPennies.subtract(quarter);
            } else if(userPennies.compareTo(dime)>=0){
                dimes++;
                userPennies = userPennies.subtract(dime);
            } else if(userPennies.compareTo(nickel)>=0){
                nickels++;
                userPennies = userPennies.subtract(nickel);
            } else if(userPennies.compareTo(penny)>=0){
                pennies++;
                userPennies = userPennies.subtract(penny);
            }
        } while(userPennies.compareTo(zero)>0);
        
        io.print("Your Change is $" + userMoney + ", which is given in " + quarters + " quarters, " + dimes + " dimes, " + nickels + " nickels, and " + pennies + " pennies.");
        
        quarters = 0;
        dimes = 0;
        nickels = 0;
        pennies = 0;
    }
    
    
    
    
}
