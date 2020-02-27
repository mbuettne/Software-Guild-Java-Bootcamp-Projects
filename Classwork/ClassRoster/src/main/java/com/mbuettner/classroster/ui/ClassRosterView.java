/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.classroster.ui;

import com.mbuettner.classroster.dto.Student;
import java.util.List;

/**
 *
 * @author mbuet
 */
public class ClassRosterView {

    UserIO io = new UserIOConsoleImpl();

    public int printMenuAndGetSelection() {

        io.print("Main Menu");
        io.print("1. List Student IDs");
        io.print("2. Create New Student");
        io.print("3. View a Student");
        io.print("4. Remoe a Student");
        io.print("5. Exit");

        return io.readInt("Please Select from the above choices.\n", 1, 5);
        
    }

    public Student getNewStudentInfo() {
        io.readString("");
        String studentId = io.readString("Please enter Student ID");
        String firstName = io.readString("Please enter First Name");
        String lastName = io.readString("Please enter Last Name");
        String cohort = io.readString("Please enter Cohort");

        Student currentStudent = new Student(studentId);

        currentStudent.setFirstName(firstName);
        currentStudent.setLastName(lastName);
        currentStudent.setCohort(cohort);

        return currentStudent;
    }
    
    public void displayCreateStudentBanner(){
        io.print("=== Create Student ===");
        io.print("");
    }
    
    public void displayCreateSuccessBanner(){
        io.readString("Student successfully created. Please hit enter to continue.");
        io.print("");
    }
    
    public void displayStudentList(List<Student> studentList){
        io.readString("");
        for(Student currentStudent: studentList){
            io.print(currentStudent.getStudentId() + ": " + currentStudent.getFirstName() + " " + currentStudent.getLastName());
        }
        io.readString("Please hit enter to continue. ");
        io.print("");
    }
    
    public void displayDisplayAllBanner(){
        io.print("=== Display All Students ===");
        io.print("");
    }
    
    public void displayDisplayStudentBanner(){
        io.print("=== Display Student ===");
        io.print("");
    }
    
    public String getStudentIdChoice(){
        io.readString("");
        return io.readString("Please enter the Student ID.");
    }
    
    public void displayStudent(Student student){
        if(student != null){
            io.print(student.getStudentId());
            io.print(student.getFirstName() + " " + student.getLastName());
            io.print(student.getCohort());
            io.print("");
        } else {
            io.print("No such student.");
        }
        io.readString("Please hit enter to continue.");
    }
}
