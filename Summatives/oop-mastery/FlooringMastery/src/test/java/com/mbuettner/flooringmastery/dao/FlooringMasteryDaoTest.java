/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.flooringmastery.dao;

import com.mbuettner.flooringmastery.dto.Order;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author mbuet
 */
public class FlooringMasteryDaoTest {

    private FlooringMasteryDao dao;

    public FlooringMasteryDaoTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        dao = ctx.getBean("FlooringMasteryDao", FlooringMasteryDao.class);
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() throws FlooringMasteryDaoException {
        HashMap<String, Order> orderMap = dao.loadOrders(LocalDate.now());
        orderMap.forEach((k, v) -> {
            try {
                dao.removeOrder(LocalDate.now(), Integer.parseInt(k));
            } catch (FlooringMasteryDaoException ex) {
                Logger.getLogger(FlooringMasteryDaoTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of createOrder method, of class FlooringMasteryDao.
     */
    @Test
    public void testCreateOrder() throws Exception {
        Order order = new Order("Test", "OH", "WOOD", new BigDecimal("10.00"));
        order.setDate();
        order.setProductCost(new BigDecimal("5.15"));
        order.setLaborCost(new BigDecimal("4.75"));
        order.setTaxRate(new BigDecimal("6.25"));
        order.setCostSqFt();
        order.setLaborSqFt();
        order.setTaxCost();
        order.setTotalCost();
        order.setOrderNumber(100);

        Order newOrder = dao.createOrder("Test", "OH", "WOOD", new BigDecimal("10.00"));
        newOrder.setOrderNumber(100);
        assertEquals(order, newOrder);
    }

    /**
     * Test of editOrder method, of class FlooringMasteryDao.
     */
    @Test
    public void testEditOrder() throws Exception {
        Order newOrder = dao.createOrder("Test", "OH", "WOOD", new BigDecimal("10.00"));
        newOrder.setOrderNumber(100);
        dao.writeOrders(LocalDate.now(), newOrder);
        dao.editOrder(LocalDate.now(), 100, new Order("James", "OH", "TILE", new BigDecimal("123")));
        dao.writeEdits(LocalDate.now());
        dao.loadOrders(LocalDate.now());
        assertEquals("James", dao.loadOrders(LocalDate.now()).get(Integer.toString(100)).getName());
    }

    /**
     * Test of removeOrder method, of class FlooringMasteryDao.
     */
    @Test
    public void testRemoveOrder() throws Exception {
        Order newOrder = dao.createOrder("Test", "OH", "WOOD", new BigDecimal("10.00"));
        newOrder.setOrderNumber(100);
        dao.writeOrders(LocalDate.now(), newOrder);
        dao.removeOrder(LocalDate.now(), 100);
        dao.writeOrders(LocalDate.now());
        dao.loadOrders(LocalDate.now());
        assertFalse(dao.checkOrderExists(LocalDate.now(), 100));
    }

    /**
     * Test of getTaxList method, of class FlooringMasteryDao.
     */
    @Test
    public void testGetTaxList() throws Exception {
        dao.loadTaxes();
        HashMap<String, BigDecimal> taxMap = dao.getTaxList();

        assertEquals(taxMap.size(), 4);
    }

    /**
     * Test of getProductList method, of class FlooringMasteryDao.
     */
    @Test
    public void testGetProductList() throws Exception {
        dao.loadProducts();
        HashMap<String, ArrayList<BigDecimal>> productMap = dao.getProductList();

        assertEquals(productMap.size(), 4);
    }

    /**
     * Test of checkDateExists method, of class FlooringMasteryDao.
     */
    @Test
    public void testCheckDateExistsTrue() throws Exception {
        assertTrue(dao.checkDateExists(LocalDate.now()));
    }

    @Test
    public void testCheckDateExistsFalse() throws Exception {
        assertFalse(dao.checkDateExists(LocalDate.parse("2002-08-03")));
    }

    /**
     * Test of checkOrderExists method, of class FlooringMasteryDao.
     */
    @Test
    public void testCheckOrderExistsTrue() throws Exception {
        assertTrue(dao.checkOrderExists(LocalDate.parse("2020-03-20"), 49));
    }

    @Test
    public void testCheckOrderExistsFalse() throws Exception {
        assertFalse(dao.checkOrderExists(LocalDate.now(), 29));
    }

}
