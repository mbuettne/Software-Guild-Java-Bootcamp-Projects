/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.flooringmastery.view;

import com.mbuettner.flooringmastery.dto.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Set;

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
        io.print("*");
        io.print("*  <<Flooring Program>>");
        io.print("* 1. Display Orders");
        io.print("* 2. Add an Order");
        io.print("* 3. Edit an Order");
        io.print("* 4. Remove an Order");
        io.print("* 5. Quit");
        io.print("*");
        io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        choice = io.readInt("Please Select From The Above Menu.", 1, 5);

        return choice;
    }

    public void displayAllOrders(HashMap<String, Order> orderMap) {
        Set<String> keys = orderMap.keySet();

        for (String o : keys) {
            io.print("~~~~~~~~~~~~~~~~~~~");
            io.print("Order Number: " + orderMap.get(o).getOrderNumber());
            io.print("Name: " + orderMap.get(o).getName());
            io.print("State: " + orderMap.get(o).getState());
            io.print("Product: " + orderMap.get(o).getProduct());
            io.print("Area: " + orderMap.get(o).getArea());
            io.print("");
        }
        io.readString("Hit Enter To Return To Main Menu.");
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
        
        return state.toUpperCase();
    }

    public String getProductFromUser() {
        String product = io.readString("Enter The Product For The Current Order (Currently Offering Carpet, Laminate, Tile, And Wood): ");
        while (!product.equalsIgnoreCase("Carpet") && !product.equalsIgnoreCase("Laminate") && !product.equalsIgnoreCase("Tile") && !product.equalsIgnoreCase("Wood")) {
            product = io.readString("Entry Does Not Match Our Records. Please Try Again. Enter The Product For The Current Order (Currently Offering Carpet, Laminate, Tile, And Wood): ");
        }
        return product.toUpperCase();
    }

    public BigDecimal getAreaFromUser() {
        BigDecimal area = io.readBigDecimal("Enter The Area (In Square Feet) For The Order: ");
        return area;
    }

    public void displaySingleOrder(Order order) {
        io.print("");
        io.print("Name: " + order.getName());
        io.print("State: " + order.getState());
        io.print("Product: " + order.getProduct());
        io.print("Area: " + order.getArea());
        io.print("");
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
    
       public boolean trainingMode() {
           boolean isTraining = false;
        String choice = io.readString("Open Program In Training Mode? (Unable To Save Changes In Training Mode) (y/n)");
        while (!choice.equalsIgnoreCase("y") && !choice.equalsIgnoreCase("n")) {
            choice = io.readString("Unknown Entry. Please Try Again. Confirm Order Removal? (y/n)");
        }
        if(choice.equalsIgnoreCase("y")){
            isTraining = true;
        }
        return isTraining;
    }

    public LocalDate dateToSearch() {
        LocalDate date = io.readDate("Enter The Date For The Order(s) (Must Be In MM/DD/YYYY Format): ");
        return date;
    }

    public int orderNumberToSearch() {
        int orderNumber = io.readInt("Please Enter Order Number Of Desired Order: ");
        return orderNumber;
    }

    public Order editOrder(Order order) {
        String newName= io.readString("Enter New Name For Order, Or Hit Enter To Keep Current Name. Current Name Is: " + order.getName());
        String newState = io.readString("Enter New State For Order, Or Hit Enter To Keep Current State. Current State Is: " + order.getState());
        while (!newState.equalsIgnoreCase("OH") && !newState.equalsIgnoreCase("PA") && !newState.equalsIgnoreCase("MI") && !newState.equalsIgnoreCase("IN") && !newState.equals("")) {
            newState = io.readString("Entry Does Not Match Our Records. Please Try Again. Enter The State The Current Order Is From (Currently Servicing OH, PA, MI, IN): ");
        }
        String newProduct = io.readString("Enter New Product For Order, Or Hit Enter To Keep Current Product. Current Product Is: " + order.getProduct());
        while (!newProduct.equalsIgnoreCase("Carpet") && !newProduct.equalsIgnoreCase("Laminate") && !newProduct.equalsIgnoreCase("Tile") && !newProduct.equalsIgnoreCase("Wood") && !newProduct.equals("")) {
            newProduct = io.readString("Entry Does Not Match Our Records. Please Try Again. Enter The Product For The Current Order (Currently Offering Carpet, Laminate, Tile, And Wood): ");
        }
        BigDecimal newArea = io.readBigDecimal("Enter New Area For Order, Or Hit Enter To Keep Current Area. Current Area Is: " + order.getArea());
        
        return new Order(newName, newState.toUpperCase(), newProduct.toUpperCase(), newArea);
    }

    public void addSuccess() {
        io.readString("Order Successfully Added! Press Enter To Continue.");
    }

    public void editSuccess() {
        io.readString("Order Successfully Edited! Press Enter To Continue.");
    }

    public void saveSuccess() {
        io.readString("Changes Successfully Saved! Press Enter To Continue.");
    }

    public void removeSuccess() {
        io.readString("Order Successfully Removed! Press Enter To Continue.");
    }

    public void removeFail() {
        io.readString("Order Removal Cancelled. Press Enter To Continue.");
    }
    
    public void infoNotFound(){
        io.readString("The Information Entered Is Not In The System. Please Try Again. Press Enter To Return To Main Menu.");
    }

    public void returnToMenu() {
        io.print("Returning To Main Menu...");
    }

    public void unknownCommand() {
        io.print("Unknown Command Entered. Returning To Main Menu...");
    }
    
    public void trainingModeError(){
        io.print("Information Unable To Save In Training Mode.");
    }

    public void exitProgram() {
        io.print("Thank You For Using The Flooring Order Program. Exiting...");
    }
    
        public void displayError(String errorMsg) {
        io.print("--ERROR--");
        io.print(errorMsg);
    }
}
