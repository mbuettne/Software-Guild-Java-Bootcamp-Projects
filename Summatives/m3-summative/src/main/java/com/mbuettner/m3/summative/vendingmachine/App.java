/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.m3.summative.vendingmachine;

import com.mbuettner.m3.summative.controller.VendingMachineController;
import com.mbuettner.m3.summative.dao.VendingMachineDao;
import com.mbuettner.m3.summative.dao.VendingMachineDaoException;
import com.mbuettner.m3.summative.dao.VendingMachineDaoFileImpl;
import com.mbuettner.m3.summative.ui.UserIO;
import com.mbuettner.m3.summative.ui.UserIOConsoleImpl;
import com.mbuettner.m3.summative.ui.VendingMachineView;

/**
 *
 * @author mbuet
 */
public class App {

    public static void main(String[] args) throws VendingMachineDaoException {
        UserIO io = new UserIOConsoleImpl();
        VendingMachineView view = new VendingMachineView(io);
        VendingMachineDao dao = new VendingMachineDaoFileImpl();
        VendingMachineController controller = new VendingMachineController(dao, view);

        controller.run();
    }

}
