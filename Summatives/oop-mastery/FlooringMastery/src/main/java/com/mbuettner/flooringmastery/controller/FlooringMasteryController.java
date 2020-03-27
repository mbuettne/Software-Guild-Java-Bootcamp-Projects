/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.flooringmastery.controller;

import com.mbuettner.flooringmastery.dao.FlooringMasteryDaoException;
import com.mbuettner.flooringmastery.dto.Order;
import com.mbuettner.flooringmastery.service.FlooringMasteryServiceLayer;
import com.mbuettner.flooringmastery.view.FlooringMasteryView;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;

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

    public void run() throws FlooringMasteryDaoException, IOException {
        boolean keepGoing = true;
        boolean isTraining = false;
        int menuSelection = 0;
        isTraining = view.trainingMode();
        try {
            while (keepGoing) {

                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        listAllOrders();
                        break;
                    case 2:
                        if (isTraining) {
                            addOrderTrain();
                        } else {
                            addOrder();
                        }
                        break;
                    case 3:
                        if (isTraining) {
                            editOrderTrain();
                        } else {
                            editOrder();
                        }
                        break;
                    case 4:
                        if (isTraining) {
                            removeOrderTrain();
                        } else {
                            removeOrder();
                        }
                        break;
                    case 5:
                        keepGoing = false;
                        break;
                    default:
                        displayUnknownCommand();
                }
            }
        } catch (FlooringMasteryDaoException e) {
            view.displayError(e.getMessage());
        }

        displayExit();
    }

    private int getMenuSelection() {
        return view.printMenuGetSelection();
    }

    private void listAllOrders() throws FlooringMasteryDaoException {
        LocalDate date = view.dateToSearch();
        if (service.checkDateExists(date)) {
            HashMap<String, Order> orderMap = service.listAllOrders(date);
            view.displayAllOrders(orderMap);
        } else {
            view.infoNotFound();
            view.returnToMenu();
        }

    }

    private void addOrder() throws FlooringMasteryDaoException, IOException {

        service.createNewFile(LocalDate.now());
        String name = view.getNameFromUser();
        String state = view.getStateFromUser();
        String product = view.getProductFromUser();
        BigDecimal area = view.getAreaFromUser();
        view.displaySingleOrder(new Order(name, state, product, area));
        String choice = view.commit();
        if (choice.equalsIgnoreCase("y")) {
            Order newOrder = service.createOrder(name, state, product, area);
            service.saveWork(LocalDate.now(), newOrder);
            view.addSuccess();
        } else {
            view.returnToMenu();
        }
    }

    private void addOrderTrain() throws FlooringMasteryDaoException, IOException {
        String name = view.getNameFromUser();
        String state = view.getStateFromUser();
        String product = view.getProductFromUser();
        BigDecimal area = view.getAreaFromUser();
        view.displaySingleOrder(new Order(name, state, product, area));
        view.trainingModeError();
        view.returnToMenu();

    }

    private void editOrder() throws FlooringMasteryDaoException {
        LocalDate date = view.dateToSearch();
        int orderNumber = view.orderNumberToSearch();
        if (service.checkDateExists(date) && service.checkOrderExists(date, orderNumber)) {
            Order original = service.findOrder(date, orderNumber);
            view.displaySingleOrder(original);
            Order edited = view.editOrder(original);
            service.editOrder(date, orderNumber, edited);
            service.saveEdits(date);
            view.editSuccess();
        } else {
            view.infoNotFound();
            view.returnToMenu();
        }
    }

    private void editOrderTrain() throws FlooringMasteryDaoException {
        LocalDate date = view.dateToSearch();
        int orderNumber = view.orderNumberToSearch();
        if (service.checkDateExists(date) && service.checkOrderExists(date, orderNumber)) {
            Order original = service.findOrder(date, orderNumber);
            view.displaySingleOrder(original);
            Order edited = view.editOrder(original);
            service.editOrder(date, orderNumber, edited);
            view.trainingModeError();
        } else {
            view.infoNotFound();
            view.returnToMenu();
        }
    }

    private void removeOrder() throws FlooringMasteryDaoException {
        LocalDate date = view.dateToSearch();
        int orderNumber = view.orderNumberToSearch();
        if (service.checkDateExists(date) && service.checkOrderExists(date, orderNumber)) {
            Order orderToRemove = service.findOrder(date, orderNumber);
            view.displaySingleOrder(orderToRemove);
            String choice = view.confirmRemoval();
            if (choice.equalsIgnoreCase("n")) {
                view.removeFail();
                view.returnToMenu();
            } else if (choice.equalsIgnoreCase("y")) {
                service.removeOrder(date, orderNumber);
                view.removeSuccess();
            }
        } else {
            view.infoNotFound();
            view.returnToMenu();
        }
    }

    private void removeOrderTrain() throws FlooringMasteryDaoException {
        LocalDate date = view.dateToSearch();
        int orderNumber = view.orderNumberToSearch();
        if (service.checkDateExists(date) && service.checkOrderExists(date, orderNumber)) {
            Order orderToRemove = service.findOrder(date, orderNumber);
            view.displaySingleOrder(orderToRemove);
            String choice = view.confirmRemoval();
            if (choice.equalsIgnoreCase("n")) {
                view.removeFail();
                view.returnToMenu();
            } else if (choice.equalsIgnoreCase("y")) {
                view.trainingModeError();
            }
        } else {
            view.infoNotFound();
            view.returnToMenu();
        }
    }

    private void displayUnknownCommand() {
        view.unknownCommand();
    }

    private void displayExit() {
        view.exitProgram();
    }
}
