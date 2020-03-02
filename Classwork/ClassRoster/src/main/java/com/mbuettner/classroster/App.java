/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.classroster;

import com.mbuettner.classroster.controller.ClassRosterController;
import com.mbuettner.classroster.dao.ClassRosterAuditDao;
import com.mbuettner.classroster.dao.ClassRosterAuditDaoFileImpl;
import com.mbuettner.classroster.dao.ClassRosterDao;
import com.mbuettner.classroster.dao.ClassRosterDaoFileImpl;
import com.mbuettner.classroster.dao.ClassRosterPersistenceException;
import com.mbuettner.classroster.service.ClassRosterDataValidationException;
import com.mbuettner.classroster.service.ClassRosterDuplicateIdException;
import com.mbuettner.classroster.ui.ClassRosterView;
import com.mbuettner.classroster.ui.UserIO;
import com.mbuettner.classroster.ui.UserIOConsoleImpl;
import com.mbuettner.classroster.service.ClassRosterServiceLayer;
import com.mbuettner.classroster.service.ClassRosterServiceLayerImpl;

/**
 *
 * @author mbuet
 */
public class App {
    public static void main(String[] args) throws ClassRosterDuplicateIdException, ClassRosterDataValidationException, ClassRosterPersistenceException {
        UserIO myIo = new UserIOConsoleImpl();
        ClassRosterView myView = new ClassRosterView(myIo);
        ClassRosterDao myDao = new ClassRosterDaoFileImpl();
        ClassRosterAuditDao myAuditDao = new ClassRosterAuditDaoFileImpl();
        ClassRosterServiceLayer myService = new ClassRosterServiceLayerImpl(myDao, myAuditDao);
        ClassRosterController controller = new ClassRosterController(myService, myView);
        
        controller.run();
    }
}
