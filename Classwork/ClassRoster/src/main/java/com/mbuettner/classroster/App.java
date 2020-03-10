/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.classroster;

import com.mbuettner.classroster.controller.ClassRosterController;
import com.mbuettner.classroster.dao.ClassRosterPersistenceException;
import com.mbuettner.classroster.service.ClassRosterDataValidationException;
import com.mbuettner.classroster.service.ClassRosterDuplicateIdException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author mbuet
 */
public class App {

public static void main(String[] args) throws  ClassRosterDuplicateIdException,  ClassRosterDataValidationException, ClassRosterPersistenceException {
        // UserIO myIo = new UserIOConsoleImpl();
        // ClassRosterView myView = new ClassRosterView(myIo);
        // ClassRosterDao myDao = new ClassRosterDaoFileImpl();
        // ClassRosterAuditDao myAuditDao = 
        //       new ClassRosterAuditDaoFileImpl();
        // ClassRosterServiceLayer myService = 
        //       new ClassRosterServiceLayerImpl(myDao, myAuditDao);
        // ClassRosterController controller = 
        //       new ClassRosterController(myService, myView);
        // controller.run();
 
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        ClassRosterController controller = ctx.getBean("controller", ClassRosterController.class);
        controller.run();
    }
}
