/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.flooringmastery;

import com.mbuettner.flooringmastery.controller.FlooringMasteryController;
import com.mbuettner.flooringmastery.dao.FlooringMasteryDaoException;
import java.io.IOException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author mbuet
 */
public class App {

    public static void main(String[] args) throws FlooringMasteryDaoException, IOException {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        FlooringMasteryController controller = ctx.getBean("controller", FlooringMasteryController.class);

        controller.run();
    }
}
