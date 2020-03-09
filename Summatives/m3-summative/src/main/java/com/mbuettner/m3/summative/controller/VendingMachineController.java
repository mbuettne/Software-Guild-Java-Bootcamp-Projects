/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.m3.summative.controller;

import com.mbuettner.m3.summative.dao.VendingMachineDao;
import com.mbuettner.m3.summative.dao.VendingMachineDaoException;
import com.mbuettner.m3.summative.dto.Change;
import com.mbuettner.m3.summative.dto.User;
import com.mbuettner.m3.summative.dto.VendingItem;
import com.mbuettner.m3.summative.service.VendingMachineServiceLayer;
import com.mbuettner.m3.summative.service.insufficientFundsException;
import com.mbuettner.m3.summative.service.noItemInventoryException;
import com.mbuettner.m3.summative.ui.VendingMachineView;
import java.math.BigDecimal;

/**
 *
 * @author mbuet
 */
public class VendingMachineController {

    Change change = new Change();
    VendingMachineServiceLayer service;
    VendingMachineView view;
    VendingMachineDao dao;
    User user = new User(new BigDecimal("0.00"));

    public void run() throws VendingMachineDaoException, insufficientFundsException, noItemInventoryException {
        view.printVendingOptions(dao.getAllAvailableItems());
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
                        displayChange();
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
        BigDecimal moneyIn = view.getMoney();
        BigDecimal currentMoney = user.getMoney();
        user.setMoney(moneyIn.add(currentMoney));
    }

    private void vendItem() throws VendingMachineDaoException, insufficientFundsException, noItemInventoryException {
        int choice = view.printVendingOptionsGetSelection(dao.getAllAvailableItems());
        VendingItem purchasedItem = dao.purchaseItem(choice);
        if (service.hasMoney(purchasedItem, user.getMoney())) {
            user.setMoney(service.moneyCalculation(purchasedItem, user.getMoney()));
            dao.stockReduce(purchasedItem);
            view.displayItemVended();
            displayChange();
        } else if (!service.hasMoney(purchasedItem, user.getMoney())) {
            view.displayInsufFunds();
        }
    }

    private void displayChange() {
        change.makeChange(user.getMoney());
        user.setMoney(new BigDecimal("0.00"));
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

    public VendingMachineController(VendingMachineDao dao, VendingMachineView view, VendingMachineServiceLayer service) {
        this.dao = dao;
        this.view = view;
        this.service = service;
    }
}
