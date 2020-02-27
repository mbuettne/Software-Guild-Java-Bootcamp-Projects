/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.classroster;

import com.mbuettner.classroster.controller.ClassRosterController;
import com.mbuettner.classroster.dao.ClassRosterDao;
import com.mbuettner.classroster.dao.ClassRosterDaoFileImpl;
import com.mbuettner.classroster.ui.ClassRosterView;
import com.mbuettner.classroster.ui.UserIO;
import com.mbuettner.classroster.ui.UserIOConsoleImpl;

/**
 *
 * @author mbuet
 */
public class App {
    public static void main(String[] args) {
        UserIO myIo = new UserIOConsoleImpl();
        ClassRosterView myView = new ClassRosterView(myIo);
        ClassRosterDao myDao = new ClassRosterDaoFileImpl();
        ClassRosterController controller = new ClassRosterController(myDao, myView);
        
        controller.run();
    }
}
