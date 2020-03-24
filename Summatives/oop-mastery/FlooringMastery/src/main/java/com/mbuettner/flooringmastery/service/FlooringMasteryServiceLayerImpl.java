/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.flooringmastery.service;

import com.mbuettner.flooringmastery.dao.FlooringMasteryDao;
import com.mbuettner.flooringmastery.dao.FlooringMasteryDaoException;
import com.mbuettner.flooringmastery.dto.Order;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author mbuet
 */
public class FlooringMasteryServiceLayerImpl implements FlooringMasteryServiceLayer {

    private FlooringMasteryDao dao;

    public FlooringMasteryServiceLayerImpl(FlooringMasteryDao dao) {
        this.dao = dao;
    }

    private Map<String, Order> orders = new HashMap<>();
    private Map<String, BigDecimal> taxes = new HashMap<>();
    private Map<String, ArrayList<BigDecimal>> products = new HashMap<>();

    FlooringMasteryServiceLayerImpl() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order createOrder(String name, String state, String product, BigDecimal area) throws FlooringMasteryDaoException, IOException {
        Order newOrder = dao.createOrder(name, state, product, area);

        return newOrder;
    }

    @Override
    public void createNewFile(LocalDate date) throws FlooringMasteryDaoException, IOException {
        dao.createNewFile(date);
    }

    @Override
    public Order editOrder(LocalDate date, int orderNumber, Order edited) throws FlooringMasteryDaoException {
        Order orderToBeEdited = dao.editOrder(date, orderNumber, edited);
        dao.replaceMap(orderToBeEdited, date);

        return orderToBeEdited;
    }

    @Override
    public boolean checkDateExists(LocalDate date) throws FlooringMasteryDaoException {
        return dao.checkDateExists(date);
    }
    
    @Override
    public boolean checkOrderExists(LocalDate date, int orderNumber) throws FlooringMasteryDaoException{
        return dao.checkOrderExists(date, orderNumber);
    }

    @Override
    public void removeOrder(LocalDate date, int orderNumber) throws FlooringMasteryDaoException {
        dao.removeOrder(date, orderNumber);
    }

    @Override
    public HashMap<String, Order> listAllOrders(LocalDate date) throws FlooringMasteryDaoException {
        return dao.loadOrders(date);
    }

    @Override
    public void saveWork(LocalDate date) throws FlooringMasteryDaoException {
        dao.writeOrders(date);
    }

    @Override
    public void saveWork(LocalDate date, Order newOrder) throws FlooringMasteryDaoException {
        dao.writeOrders(date, newOrder);
    }

    @Override
    public void saveEdits(LocalDate date) throws FlooringMasteryDaoException {
        dao.writeEdits(date);
    }

    @Override
    public Order findOrder(LocalDate date, int orderNumber) throws FlooringMasteryDaoException {
        HashMap<String, Order> orderMap = new HashMap<>();
        Order orderToFind;
        orderMap = listAllOrders(date);
        if (!orderMap.containsKey(Integer.toString(orderNumber))) {
            System.out.println("Order Not Found.");
            orderToFind = new Order("", "", "", new BigDecimal("0"));
        } else {
            orderToFind = orderMap.get(Integer.toString(orderNumber));
        }
        return orderToFind;
    }
    
    @Override
    public HashMap<String, Order> loadOrders(LocalDate date) throws FlooringMasteryDaoException{
        return dao.loadOrders(date);
    }

}
