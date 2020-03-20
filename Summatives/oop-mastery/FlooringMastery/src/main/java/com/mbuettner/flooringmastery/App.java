/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.flooringmastery;

import com.mbuettner.flooringmastery.controller.FlooringMasteryController;
import com.mbuettner.flooringmastery.dao.FlooringMasteryDao;
import com.mbuettner.flooringmastery.dao.FlooringMasteryDaoException;
import com.mbuettner.flooringmastery.dao.FlooringMasteryDaoFileImpl;
import com.mbuettner.flooringmastery.service.FlooringMasteryServiceLayer;
import com.mbuettner.flooringmastery.service.FlooringMasteryServiceLayerImpl;
import com.mbuettner.flooringmastery.view.FlooringMasteryView;
import com.mbuettner.flooringmastery.view.UserIOConsoleImpl;
import java.io.IOException;

/**
 *
 * @author mbuet
 */
public class App {

    public static void main(String[] args) throws FlooringMasteryDaoException, IOException {
        FlooringMasteryDao dao = new FlooringMasteryDaoFileImpl();
        FlooringMasteryServiceLayer service = new FlooringMasteryServiceLayerImpl(dao);
        UserIOConsoleImpl io = new UserIOConsoleImpl();
        FlooringMasteryView view = new FlooringMasteryView(io);
        FlooringMasteryController controller = new FlooringMasteryController(view, service);

        controller.run();
    }
}
