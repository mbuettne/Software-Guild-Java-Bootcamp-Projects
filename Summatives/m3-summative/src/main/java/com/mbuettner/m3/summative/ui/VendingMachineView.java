/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.m3.summative.ui;

import com.mbuettner.m3.summative.dto.Coins;
import com.mbuettner.m3.summative.dto.User;
import com.mbuettner.m3.summative.dto.VendingItem;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author mbuet
 */
public class VendingMachineView {

    private UserIO io;
    User user = new User();

    public VendingMachineView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        int choice = 0;

        io.print("---VENDING MACHINE---");
        io.print("");
        io.print("1. Add Money");
        io.print("2. Vend Item");
        io.print("3. Get Change");
        io.print("4. Exit Machine");
        choice = io.readInt("Please Select From The Above Menu.", 1, 4);

        return choice;
    }

    public double getMoney() {
        double moneyIn = io.readDouble("How Much Money Would You Like To Put In The Vending Machine?");
        return moneyIn;
    }
    
    public void printVendingOptions(List<VendingItem> items){
        for (VendingItem currentItem : items) {

            io.print("\n----------------\nItem: " + currentItem.getName() +  "\nPrice: $" + currentItem.getPrice() + "\nQuantity Remaining: "
                    + currentItem.getQuantity());
        }
        io.print("");
    }

    public int printVendingOptionsGetSelection(List<VendingItem> items) {
        int i = 1;

        for (VendingItem currentItem : items) {

            io.print("\n----------------\n" + i + ") Item: " + currentItem.getName() +  "\nPrice: $" + currentItem.getPrice() + "\nQuantity Remaining: "
                    + currentItem.getQuantity());
            i++;
        }
        io.print("");
        int choice = io.readInt("Please Make A Selection From The Above Menu", 1, i);

        return choice;
    }

    public void displayItemVended() {
        io.print("Item Successfully Vended. Enjoy!");
    }

    public void displayInsufFunds() {
        io.print("Insufficient Funds Available. Try Adding More Money Or Make Another Selection.");
    }
    
    public void displayOutOfStock(){
        io.print("This Item Is Out Of Stock.");
    }

    public void displayUnrecognized() {
        io.print("Unrecognized Input. Please Try Again.");
    }

    public void displayChange(double userMoney) {
        int quarters = 0;
        int dimes = 0;
        int nickels = 0;
        int pennies = 0;
        double doubleInPens = userMoney * 100; //convert to pennies for calculations
        String stringInPens = Double.toString(doubleInPens); //convert to string 
        BigDecimal moneyBD = new BigDecimal(stringInPens); // convert to bigdecimal
        BigDecimal zero = new BigDecimal("0");
        BigDecimal quarterBD = new BigDecimal(Coins.QUARTER.label);
        BigDecimal dimeBD = new BigDecimal(Coins.DIME.label);
        BigDecimal nickelBD = new BigDecimal(Coins.NICKEL.label);
        BigDecimal pennyBD = new BigDecimal(Coins.PENNY.label);

        while (moneyBD.compareTo(zero) > 0) {
            if (moneyBD.compareTo(quarterBD) > 0) {
                moneyBD.subtract(quarterBD);
                quarters++;
            } else if(moneyBD.compareTo(dimeBD) > 0){
                moneyBD.subtract(dimeBD);
                dimes++;
            } else if(moneyBD.compareTo(nickelBD) > 0){
                moneyBD.subtract(nickelBD);
                nickels++;
            } else if(moneyBD.compareTo(pennyBD) > 0){
                moneyBD.subtract(pennyBD);
                pennies++;
            }
        }
        io.print("Your Change Is $" + userMoney + ", Which Is Given In " + quarters + " Quarters, " + dimes +" Dimes, " + nickels + " Nickels, And " + pennies + " Pennies.");
    }

    public void displayCurrentMoney(double money) {
        io.print("You Currently Have $" + money + " Available.");
        io.print("");
    }

    public void displayExitMessage() {
        io.print("Thank You For Using The Vending Machine!");
    }

    public void displayError(String errorMessage){
        io.print(errorMessage);
    }
}
