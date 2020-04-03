/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.jdbctcomplexexample.dao;

import com.sg.jdbctcomplexexample.TestApplicationConfiguration;
import com.sg.jdbctcomplexexample.entity.Employee;
import com.sg.jdbctcomplexexample.entity.Meeting;
import com.sg.jdbctcomplexexample.entity.Room;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author mbuet
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class EmployeeDaoDBTest {

    @Autowired
    RoomDao roomDao;

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    MeetingDao meetingDao;

    public EmployeeDaoDBTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        List<Room> rooms = roomDao.getAllRooms();
        for (Room room : rooms) {
            roomDao.deleteRoomById(room.getId());
        }

        List<Employee> employees = employeeDao.getAllEmployees();
        for (Employee employee : employees) {
            employeeDao.deleteEmployeeById(employee.getId());
        }

        List<Meeting> meetings = meetingDao.getAllMeetings();
        for (Meeting meeting : meetings) {
            meetingDao.deleteMeetingById(meeting.getId());
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getAllEmployees method, of class EmployeeDaoDB.
     */
    @Test
    public void testGetAllEmployees() {
        Employee employee = new Employee();
        employee.setFirstName("Test First");
        employee.setLastName("Test Last");
        employee = employeeDao.addEmployee(employee);

        Employee employee2 = new Employee();
        employee2.setFirstName("Test First 2");
        employee2.setLastName("Test Last 2");
        employee2 = employeeDao.addEmployee(employee2);

        List<Employee> employees = employeeDao.getAllEmployees();

        assertEquals(2, employees.size());
        assertTrue(employees.contains(employee));
        assertTrue(employees.contains(employee2));
    }

    /**
     * Test of getEmployeeById and addEmployee methods, of class EmployeeDaoDB.
     */
    @Test
    public void testAddGetEmployee() {
        Employee employee = new Employee();
        employee.setFirstName("Test First");
        employee.setLastName("Test Last");
        employee = employeeDao.addEmployee(employee);

        Employee fromDao = employeeDao.getEmployeeById(employee.getId());

        assertEquals(employee, fromDao);
    }

    /**
     * Test of updateEmployee method, of class EmployeeDaoDB.
     */
    @Test
    public void testUpdateEmployee() {
        Employee employee = new Employee();
        employee.setFirstName("Test First");
        employee.setLastName("Test Last");
        employee = employeeDao.addEmployee(employee);

        Employee fromDao = employeeDao.getEmployeeById(employee.getId());

        assertEquals(employee, fromDao);

        employee.setFirstName("Another Test First");

        employeeDao.updateEmployee(employee);

        assertNotEquals(employee, fromDao);

        fromDao = employeeDao.getEmployeeById(employee.getId());

        assertEquals(employee, fromDao);
    }

    /**
     * Test of deleteEmployeeById method, of class EmployeeDaoDB.
     */
    @Test
    public void testDeleteEmployeeById() {
        Employee employee = new Employee();
        employee.setFirstName("Test First");
        employee.setLastName("Test Last");
        employee = employeeDao.addEmployee(employee);

        Employee fromDao = employeeDao.getEmployeeById(employee.getId());

        assertEquals(employee, fromDao);

        employee.setFirstName("Another Test First");

        employeeDao.updateEmployee(employee);

        assertNotEquals(employee, fromDao);

        fromDao = employeeDao.getEmployeeById(employee.getId());

        assertEquals(employee, fromDao);
    }

}
