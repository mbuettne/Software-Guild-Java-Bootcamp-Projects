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
        io.print("3. Exit Machine");
        choice = io.readInt("Please Select From The Above Menu.", 1, 3);

        return choice;
    }

    public BigDecimal getMoney() {
        BigDecimal moneyIn = io.readBigDecimal("How Much Money Would You Like To Put In The Vending Machine?");
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
        int choice = io.readInt("Please Make A Selection From The Above Menu", 1, i-1);

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

    public void displayCurrentMoney(BigDecimal money) {
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
