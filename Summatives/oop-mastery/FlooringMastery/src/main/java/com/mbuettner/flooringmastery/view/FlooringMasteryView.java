/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.flooringmastery.view;

import com.mbuettner.flooringmastery.dto.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author mbuet
 */
public class FlooringMasteryView {

    private UserIO io;

    public FlooringMasteryView(UserIO io) {
        this.io = io;
    }

    public int printMenuGetSelection() {
        int choice = 0;

        io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        io.print("*  <<Flooring Program>>");
        io.print("* 1. Display Orders");
        io.print("* 2. Add an Order");
        io.print("* 3. Edit an Order");
        io.print("* 4. Remove an Order");
        io.print("* 5. Save Current Work");
        io.print("* 6. Quit");
        io.print("*");
        io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        choice = io.readInt("Please Select From The Above Menu.", 1, 6);

        return choice;
    }

    public void displayAllOrders(List<Order> orderList) {
        io.print("Not Yet Implemented");
    }

    public String getNameFromUser() {
        String name = io.readString("Enter A Name For The Order: ");
        return name;
    }

    public String getStateFromUser() {
        String state = io.readString("Enter The State The Current Order Is From (Currently Servicing OH, PA, MI, IN): ");
        while (!state.equalsIgnoreCase("OH") && !state.equalsIgnoreCase("PA") && !state.equalsIgnoreCase("MI") && !state.equalsIgnoreCase("IN")) {
            state = io.readString("Entry Does Not Match Our Records. Please Try Again. Enter The State The Current Order Is From (Currently Servicing OH, PA, MI, IN): ");
        }
        return state;
    }

    public String getProductFromUser() {
        String product = io.readString("Enter The Product For The Current Order (Currently Offering Carpet, Laminate, Tile, And Wood): ");
        while (!product.equalsIgnoreCase("Carpet") && !product.equalsIgnoreCase("Laminate") && !product.equalsIgnoreCase("Tile") && !product.equalsIgnoreCase("Wood")) {
            product = io.readString("Entry Does Not Match Our Records. Please Try Again. Enter The Product For The Current Order (Currently Offering Carpet, Laminate, Tile, And Wood): ");
        }
        return product;
    }

    public BigDecimal getAreaFromUser() {
        BigDecimal area = io.readBigDecimal("Enter The Area (In Square Feet) For The Order: ");
        io.readString("");
        return area;
    }

    public void displaySingleOrder(Order order) {
        io.print("Not Yet Implemented");
    }

    public String commit() {
        String choice = io.readString("Would You Like To Commit These Changes? (y/n)");
        while (!choice.equalsIgnoreCase("y") && !choice.equalsIgnoreCase("n")) {
            choice = io.readString("Unknown Entry. Please Try Again. Would You Like To Commit These Changes? (y/n)");
        }
        return choice;
    }

    public String confirmRemoval() {
        String choice = io.readString("Confirm Order Removal? (y/n)");
        while (!choice.equalsIgnoreCase("y") && !choice.equalsIgnoreCase("n")) {
            choice = io.readString("Unknown Entry. Please Try Again. Confirm Order Removal? (y/n)");
        }
        return choice;
    }

    public LocalDate dateToSearch() {
        LocalDate date = io.readDate("Enter The Date For The Order(s) (Must Be In MM/DD/YYY Format): ");
        return date;
    }

    public int orderNumberToSearch() {
        int orderNumber = io.readInt("Please Enter Order Number Of Desired Order: ");
        return orderNumber;
    }

    public Order editOrder(LocalDate date, int OrderNumber) {
        io.print("Not Yet Implemented");
        return new Order("name", "state", "product", new BigDecimal("1.00"));
    }

    public void addSuccess() {
        io.readString("Order Successfully Added! Press Enter To Continue.");
    }

    public void editSuccess() {
        io.readString("Order Successfully Edited! Press Enter To Continue.");
    }
    
    public void saveSuccess(){
        io.readString("Changes Successfully Saved! Press Enter To Continue.");
    }

    public void removeSuccess() {
        io.readString("Order Successfully Removed! Press Enter To Continue.");
    }

    public void removeFail() {
        io.readString("Order Removal Cancelled. Press Enter To Continue.");
    }
    
    public void returnToMenu(){
        io.print("Returning To Main Menu...");
    }

    public void unknownCommand() {
        io.print("Unknown Command Entered. Returning To Main Menu...");
    }
    
    public void exitProgram(){
        io.print("Thank You For Using The Flooring Order Program. Exiting...");
    }
}
