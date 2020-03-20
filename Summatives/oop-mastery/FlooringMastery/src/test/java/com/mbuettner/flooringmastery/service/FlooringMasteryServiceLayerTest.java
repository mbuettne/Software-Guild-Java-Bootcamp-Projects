/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.flooringmastery.service;

import com.mbuettner.flooringmastery.dao.FlooringMasteryDaoException;
import com.mbuettner.flooringmastery.dao.FlooringMasteryDao;
import com.mbuettner.flooringmastery.dao.FlooringMasteryDaoFileImpl;
import com.mbuettner.flooringmastery.dto.Order;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author mbuet
 */
public class FlooringMasteryServiceLayerTest {

    private FlooringMasteryDao dao = new FlooringMasteryDaoFileImpl();
    private FlooringMasteryServiceLayer service = new FlooringMasteryServiceLayerImpl(dao);

    public FlooringMasteryServiceLayerTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() throws FlooringMasteryDaoException {
        HashMap<String, Order> orderMap = service.loadOrders(LocalDate.now());
        orderMap.forEach((k, v) -> {
            try {
                service.removeOrder(LocalDate.now(), Integer.parseInt(k));
            } catch (FlooringMasteryDaoException ex) {
                Logger.getLogger(FlooringMasteryServiceLayerTest.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of createOrder method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testCreateOrder() throws Exception {

        Order order = new Order("Test2", "PA", "TILE", new BigDecimal("10.00"));
        order.setDate();
        order.setProductCost(new BigDecimal("3.50"));
        order.setLaborCost(new BigDecimal("4.15"));
        order.setTaxRate(new BigDecimal("6.75"));
        order.setCostSqFt();
        order.setLaborSqFt();
        order.setTaxCost();
        order.setTotalCost();
        order.setOrderNumber(70);

        Order newOrder = service.createOrder("Test2", "PA", "TILE", new BigDecimal("10.00"));

        newOrder.setOrderNumber(70);

        assertEquals(order, newOrder);
    }

    /**
     * Test of editOrder method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testEditOrder() throws Exception {
        Order newOrder = service.createOrder("Test", "OH", "WOOD", new BigDecimal("10.00"));
        newOrder.setOrderNumber(100);
        service.saveWork(LocalDate.now(), newOrder);
        service.editOrder(LocalDate.now(), 100, new Order("James", "OH", "TILE", new BigDecimal("123")));
        service.saveEdits(LocalDate.now());
        service.loadOrders(LocalDate.now());
        assertEquals("James", service.loadOrders(LocalDate.now()).get(Integer.toString(100)).getName());
    }

    /**
     * Test of removeOrder method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testRemoveOrder() throws Exception {
        Order newOrder = service.createOrder("Test", "OH", "WOOD", new BigDecimal("10.00"));
        newOrder.setOrderNumber(100);
        service.saveWork(LocalDate.now(), newOrder);
        service.removeOrder(LocalDate.now(), 100);
        service.saveWork(LocalDate.now());
        service.loadOrders(LocalDate.now());
        assertFalse(service.checkOrderExists(LocalDate.now(), 100));
    }

    /**
     * Test of findOrder method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testFindOrder() throws Exception {
        HashMap<String, Order> orderMap = service.loadOrders(LocalDate.parse("2020-03-18"));
        Order foundOrder = service.findOrder(LocalDate.parse("2020-03-18"), 1);

        assertEquals(foundOrder, orderMap.get(Integer.toString(1)));
    }

    /**
     * Test of checkDateExists method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testCheckDateExistsTrue() throws Exception {
        assertTrue(service.checkDateExists(LocalDate.now()));
    }

    @Test
    public void testCheckDateExistsFalse() throws Exception {
        assertFalse(service.checkDateExists(LocalDate.parse("2000-05-09")));
    }

    /**
     * Test of checkOrderExists method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testCheckOrderExistsTrue() throws Exception {
        assertTrue(service.checkOrderExists(LocalDate.now(), 49));
    }

    @Test
    public void testCheckOrderExistsFalse() throws Exception {
        assertFalse(service.checkOrderExists(LocalDate.now(), 749));
    }

}
