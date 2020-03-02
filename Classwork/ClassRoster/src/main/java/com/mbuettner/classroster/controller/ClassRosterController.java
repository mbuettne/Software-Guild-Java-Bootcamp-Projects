/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.classroster.controller;

import com.mbuettner.classroster.dao.ClassRosterPersistenceException;
import com.mbuettner.classroster.dto.Student;
import com.mbuettner.classroster.service.ClassRosterDataValidationException;
import com.mbuettner.classroster.service.ClassRosterDuplicateIdException;
import com.mbuettner.classroster.service.ClassRosterServiceLayer;
import com.mbuettner.classroster.ui.ClassRosterView;
import java.util.List;

/**
 *
 * @author mbuet
 */
public class ClassRosterController {

    ClassRosterView view;
    ClassRosterServiceLayer slay;

    public void run() throws ClassRosterDuplicateIdException, ClassRosterDataValidationException, ClassRosterPersistenceException {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {

                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        listStudents();
                        break;
                    case 2:
                        createStudent();
                        break;
                    case 3:
                        viewStudent();
                        break;
                    case 4:
                        removeStudent();
                        break;
                    case 5:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }

            }

            exitMessage();
        } catch (ClassRosterPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    public ClassRosterController(ClassRosterServiceLayer slay, ClassRosterView view) {
        this.slay = slay;
        this.view = view;
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void createStudent() throws ClassRosterPersistenceException{
        view.displayCreateStudentBanner();
        boolean hasErrors = false;
        do{
            Student currentStudent = view.getNewStudentInfo();
            try{
                slay.createStudent(currentStudent);
                view.displayCreateSuccessBanner();
                hasErrors = false;
            } catch(ClassRosterDuplicateIdException | ClassRosterDataValidationException e){
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());
            }
        } while (hasErrors);
    }

    private void listStudents() throws ClassRosterPersistenceException {
        view.displayDisplayAllBanner();
        List<Student> studentList = slay.getAllStudents();
        view.displayStudentList(studentList);

    }

    private void viewStudent() throws ClassRosterPersistenceException {
        view.displayDisplayStudentBanner();
        String studentId = view.getStudentIdChoice();
        Student student = slay.getStudent(studentId);
        view.displayStudent(student);
    }

    private void removeStudent() throws ClassRosterPersistenceException {
        view.displayRemoveStudentBanner();
        String studentId = view.getStudentIdChoice();
        slay.removeStudent(studentId);
        view.displayRemoveSuccessBanner();
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }
}
