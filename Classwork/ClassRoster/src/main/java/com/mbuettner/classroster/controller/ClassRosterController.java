/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.classroster.controller;

import com.mbuettner.classroster.ui.ClassRosterView;
import com.mbuettner.classroster.ui.UserIO;
import com.mbuettner.classroster.ui.UserIOConsoleImpl;

/**
 *
 * @author mbuet
 */
public class ClassRosterController {

    private UserIO io = new UserIOConsoleImpl();
    ClassRosterView classRosterView = new ClassRosterView();

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;

        while (keepGoing) {

            menuSelection = getMenuSelection();

            switch (menuSelection) {
                case 1:
                    io.print("LIST STUDENTS");
                    break;
                case 2:
                    io.print("CREATE STUDENT");
                    break;
                case 3:
                    io.print("VIEW STUDENT");
                    break;
                case 4:
                    io.print("REMOVE STUDENT");
                    break;
                case 5:
                    keepGoing = false;
                    break;
                default:
                    io.print("UNKNOWN COMMAND");
            }

        }

        io.print("GOODBYE");
    }
    
    private int getMenuSelection(){
        return classRosterView.printMenuAndGetSelection();
    }
}
