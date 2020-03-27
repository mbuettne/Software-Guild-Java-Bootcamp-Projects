/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.classroster.service;

import com.mbuettner.classroster.dao.ClassRosterAuditDao;
import com.mbuettner.classroster.dao.ClassRosterAuditDaoStubImpl;
import com.mbuettner.classroster.dao.ClassRosterDao;
import com.mbuettner.classroster.dao.ClassRosterDaoStubImpl;
import com.mbuettner.classroster.dao.ClassRosterPersistenceException;
import com.mbuettner.classroster.dto.Student;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author mbuet
 */
public class ClassRosterServiceLayerTest {

    private ClassRosterServiceLayer service;

    public ClassRosterServiceLayerTest() {
//        ClassRosterDao dao = new ClassRosterDaoStubImpl();
//        ClassRosterAuditDao auditDao = new ClassRosterAuditDaoStubImpl();
//
//        service = new ClassRosterServiceLayerImpl(dao, auditDao);

        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        service = ctx.getBean("serviceLayer", ClassRosterServiceLayer.class);
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of createStudent method, of class ClassRosterServiceLayer.
     */
    @Test
    public void testCreateStudent() throws Exception {
        Student student = new Student("0002");
        student.setFirstName("Sally");
        student.setLastName("Smith");
        student.setCohort(".NET 0220");
        service.createStudent(student);
    }

    @Test
    public void testCreateStudentDuplicateId() throws Exception {
        Student student = new Student("0001");
        student.setFirstName("Sally");
        student.setLastName("Smith");
        student.setCohort(".NET 0220");

        try {
            service.createStudent(student);
            fail("Expected ClassRosterDuplicateException was not thrown");
        } catch (ClassRosterDuplicateIdException e) {
            return;
        }
    }

    @Test
    public void testCreateStudentInvalidData() throws Exception {
        Student student = new Student("0002");
        student.setFirstName("");
        student.setLastName("Smith");
        student.setCohort(".NET 0220");

        try {
            service.createStudent(student);
            fail("Expected ClassRosterDataValidationException was not thrown.");
        } catch (ClassRosterDataValidationException e) {

        }
    }

    /**
     * Test of getAllStudents method, of class ClassRosterServiceLayer.
     */
    @Test
    public void testGetAllStudents() throws Exception {
        assertEquals(1, service.getAllStudents().size());
    }

    /**
     * Test of getStudent method, of class ClassRosterServiceLayer.
     */
    @Test
    public void testGetStudent() throws Exception {
        Student student = service.getStudent("0001");
        assertNotNull(student);

        student = service.getStudent("99999");
        assertNull(student);
    }

    /**
     * Test of removeStudent method, of class ClassRosterServiceLayer.
     */
    @Test
    public void testRemoveStudent() throws Exception {
        Student student = service.removeStudent("0001");
        assertNotNull(student);

        student = service.removeStudent("99999");
        assertNull(student);
    }

}
