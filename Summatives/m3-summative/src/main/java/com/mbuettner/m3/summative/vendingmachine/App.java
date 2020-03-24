/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.m3.summative.vendingmachine;

import com.mbuettner.m3.summative.controller.VendingMachineController;
import com.mbuettner.m3.summative.dao.VendingMachineDaoException;
import com.mbuettner.m3.summative.service.insufficientFundsException;
import com.mbuettner.m3.summative.service.noItemInventoryException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author mbuet
 */
public class App {

    public static void main(String[] args) throws VendingMachineDaoException, insufficientFundsException, noItemInventoryException {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        VendingMachineController controller = ctx.getBean("controller", VendingMachineController.class);
        controller.run();
    }

}
