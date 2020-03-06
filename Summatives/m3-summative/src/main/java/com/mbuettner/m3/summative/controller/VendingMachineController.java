/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.m3.summative.controller;

import com.mbuettner.m3.summative.dao.VendingMachineDao;
import com.mbuettner.m3.summative.dao.VendingMachineDaoException;
import com.mbuettner.m3.summative.dto.User;
import com.mbuettner.m3.summative.dto.VendingItem;
import com.mbuettner.m3.summative.ui.VendingMachineView;

/**
 *
 * @author mbuet
 */
public class VendingMachineController {

    VendingMachineView view;
    VendingMachineDao dao;
    User user = new User(0.00);

    public void run() throws VendingMachineDaoException {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {
                view.displayCurrentMoney(user.getMoney());
                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        addMoney();
                        break;
                    case 2:
                        vendItem();
                        break;
                    case 3:
                        // Get Change
                        break;
                    case 4:
                        keepGoing = false;
                        break;
                    default:
                        displayUnknownCommand();
                }
            }
        } catch (VendingMachineDaoException e) {
            view.displayError(e.getMessage());
        }

        displayExit();
    }

    private void addMoney() {
        double moneyIn = view.getMoney();
        double currentMoney = user.getMoney();
        user.setMoney(moneyIn + currentMoney);
    }

    private void vendItem() throws VendingMachineDaoException {
        int choice = view.printVendingOptionsGetSelection(dao.getAllAvailableItems());
        VendingItem purchasedItem = dao.purchaseItem(choice);
        if(dao.hasMoney(purchasedItem, user.getMoney())){
            user.setMoney(dao.moneyCalculation(purchasedItem, user.getMoney()));
            dao.stockReduce(purchasedItem);
        } else if ( !dao.hasMoney(purchasedItem, user.getMoney())){
            view.displayInsufFunds();
        } 
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void displayExit() {
        view.displayExitMessage();
    }

    private void displayUnknownCommand() {
        view.displayUnrecognized();
    }

    public VendingMachineController(VendingMachineDao dao, VendingMachineView view) {
        this.dao = dao;
        this.view = view;
    }
}
