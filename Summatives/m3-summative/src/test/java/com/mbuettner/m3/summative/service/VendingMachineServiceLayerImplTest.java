/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.m3.summative.service;

import com.mbuettner.m3.summative.dao.VendingMachineAuditDao;
import com.mbuettner.m3.summative.dao.VendingMachineDao;
import com.mbuettner.m3.summative.dto.User;
import com.mbuettner.m3.summative.dto.VendingItem;
import java.math.BigDecimal;
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
public class VendingMachineServiceLayerImplTest {

    private VendingMachineDao dao;
    private VendingMachineAuditDao auditDao;
    VendingMachineServiceLayerImpl service = new VendingMachineServiceLayerImpl(dao, auditDao);

    public VendingMachineServiceLayerImplTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        VendingMachineServiceLayer service = ctx.getBean("serviceLayer", VendingMachineServiceLayer.class);
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
     * Test of hasMoney method, of class VendingMachineServiceLayerImpl.
     */
    @Test
    public void testHasMoney() throws Exception {
        VendingItem item = new VendingItem("Test", new BigDecimal("1.50"), 4);
        User user = new User(new BigDecimal("2.00"));
        assertTrue(service.hasMoney(item, user.getMoney()));
    }

    @Test
    public void testHasNoMoney() throws Exception {
        VendingItem item = new VendingItem("Test", new BigDecimal("2.50"), 4);
        User user = new User(new BigDecimal("2.00"));
        assertFalse(service.hasMoney(item, user.getMoney()));
    }

    /**
     * Test of hasStock method, of class VendingMachineServiceLayerImpl.
     */
    @Test
    public void testHasStock() throws Exception {
        VendingItem item = new VendingItem("test", new BigDecimal("1.70"), 2);
        assertTrue(service.hasStock(item));
    }

    @Test
    public void testHasNoStock() throws Exception {
        VendingItem item = new VendingItem("test3", new BigDecimal("1.70"), 0);
        assertFalse(service.hasStock(item));
    }

}
