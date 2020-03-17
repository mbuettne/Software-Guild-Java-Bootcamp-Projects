/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.flooringmastery.controller;

import com.mbuettner.flooringmastery.dto.Order;
import com.mbuettner.flooringmastery.service.FlooringMasteryServiceLayer;
import com.mbuettner.flooringmastery.view.FlooringMasteryView;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author mbuet
 */
public class FlooringMasteryController {

    FlooringMasteryServiceLayer service;
    FlooringMasteryView view;
    
        public FlooringMasteryController(FlooringMasteryView view, FlooringMasteryServiceLayer service) {
        this.view = view;
        this.service = service;
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
   //     try {
            while (keepGoing) {
                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        listAllOrders();
                        break;
                    case 2:
                        addOrder();
                        break;
                    case 3:
                        editOrder();
                        break;
                    case 4:
                        removeOrder();
                        break;
                    case 5:
                        saveWork();
                        break;
                    case 6:
                        keepGoing = false;
                        break;
                    default:
                        displayUnknownCommand();
                }
            }
    // } catch () {
       //     view.displayError(e.getMessage());
      //  }

        displayExit();
    }

    private int getMenuSelection() {
        return view.printMenuGetSelection();
    }
    
    private void listAllOrders(){
        LocalDate date = view.dateToSearch();
        List<Order> orderList = service.listAllOrders(date);
        view.displayAllOrders(orderList);
    }
    
    private void addOrder(){
        String name = view.getNameFromUser();
        String state = view.getStateFromUser();
        String product = view.getProductFromUser();
        BigDecimal area = view.getAreaFromUser();
        view.displaySingleOrder(new Order(name, state, product, area));
        String choice = view.commit();
        if(choice.equalsIgnoreCase("y")){
            service.createOrder(name, state, product, area);
            view.addSuccess();
        } else {
            view.returnToMenu();
        }
    }
    
    private void editOrder(){
        LocalDate date = view.dateToSearch();
        int orderNumber = view.orderNumberToSearch();
        Order edited = view.editOrder(date, orderNumber);
        service.editOrder(date, orderNumber, edited);
        view.editSuccess();
    }
    
    private void removeOrder(){
        LocalDate date = view.dateToSearch();
        int orderNumber = view.orderNumberToSearch();
        Order orderToRemove = service.findOrder(date, orderNumber);
        view.displaySingleOrder(orderToRemove);
        String choice = view.confirmRemoval();
        if(choice.equalsIgnoreCase("n")){
            view.removeFail();
            view.returnToMenu();
        } else if(choice.equalsIgnoreCase("y")){
            service.removeOrder(date, orderNumber);
            view.removeSuccess();
        }
    }
    
    private void saveWork(){
        service.saveWork();
        view.saveSuccess();
    }
    
    private void displayUnknownCommand(){
        view.unknownCommand();
    }
    
    private void displayExit(){
        view.exitProgram();
    }
}
