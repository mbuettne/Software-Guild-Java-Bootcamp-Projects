/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.m2;

import com.mbuettner.m2.controller.DVDLibraryController;
import com.mbuettner.m2.dao.m2Dao;
import com.mbuettner.m2.dao.m2DaoFileImpl;
import com.mbuettner.m2.ui.DVDLibraryView;
import com.mbuettner.m2.ui.UserIO;
import com.mbuettner.m2.ui.UserIOConsoleImpl;

/**
 *
 * @author mbuet
 */
public class App {

    public static void main(String[] args) {
        UserIO io = new UserIOConsoleImpl();
        DVDLibraryView view = new DVDLibraryView(io);
        m2Dao dao = new m2DaoFileImpl();
        DVDLibraryController controller = new DVDLibraryController(dao, view);

        controller.run();
    }
}
