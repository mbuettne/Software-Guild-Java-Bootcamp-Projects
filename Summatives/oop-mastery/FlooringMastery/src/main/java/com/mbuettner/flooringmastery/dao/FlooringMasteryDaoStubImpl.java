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

/**
 *
 * @author mbuet
 */
public class FlooringMasteryDaoStubImpl implements FlooringMasteryDao{
    Order order;
    HashMap<String, Order> orderMap = new HashMap<>();
    
    public FlooringMasteryDaoStubImpl(){
        order = new Order("Test", "OH", "WOOD", new BigDecimal("10.00"));
        order.setOrderNumber(100);
        order.setDate();
        order.setProductCost(new BigDecimal ("5.15"));
        order.setCostSqFt();
        order.setLaborCost(new BigDecimal("4.75"));
        order.setLaborSqFt();
        order.setTaxRate(new BigDecimal("6.25"));
        order.setTaxCost();
        order.setTotalCost();
        
        orderMap.put(Integer.toString(order.getOrderNumber()), order);
    }

    @Override
    public Order createOrder(String name, String state, String product, BigDecimal area) throws FlooringMasteryDaoException {
        Order newOrder = new Order(name, state, product, area);
        newOrder.setOrderNumber(101);
        orderMap.put(Integer.toString(newOrder.getOrderNumber()), newOrder);
        return newOrder;
    }

    @Override
    public Order removeOrder(LocalDate date, int orderNumber) throws FlooringMasteryDaoException {
        Order newOrder = orderMap.get(Integer.toString(orderNumber));
        orderMap.remove(Integer.toString(101));
        return newOrder;
    }

    @Override
    public Order editOrder(LocalDate date, int orderNumber, Order edited) throws FlooringMasteryDaoException {
        Order order = orderMap.get(Integer.toString(orderNumber));
        order.setName("AfterEdit");
        orderMap.replace(Integer.toString(orderNumber), order);
        return order;
    }

    @Override
    public HashMap<String, Order> loadOrders(LocalDate date) throws FlooringMasteryDaoException {
        return orderMap;
    }

    @Override
    public void loadDates() throws FlooringMasteryDaoException {
//Do Nothing
    }

    @Override
    public void loadOrderNumbers() throws FlooringMasteryDaoException {
//Do Nothing
    }

    @Override
    public void loadTaxes() throws FlooringMasteryDaoException {
//Do Nothing
    }

    @Override
    public void loadProducts() throws FlooringMasteryDaoException {
//Do Nothing
    }

    @Override
    public void writeEdits(LocalDate date) throws FlooringMasteryDaoException {
//Do Nothing
    }

    @Override
    public void writeOrders(LocalDate date) throws FlooringMasteryDaoException {
//Do Nothing
    }

    @Override
    public void writeOrders(LocalDate date, Order newOrder) throws FlooringMasteryDaoException {
//Do Nothing
    }

    @Override
    public void writeOrderNumbers() throws FlooringMasteryDaoException {
//Do Nothing
    }

    @Override
    public HashMap<String, BigDecimal> getTaxList() throws FlooringMasteryDaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HashMap<String, ArrayList<BigDecimal>> getProductList() throws FlooringMasteryDaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getNextOrderNumber() throws FlooringMasteryDaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createNewFile(LocalDate date) throws FlooringMasteryDaoException, IOException {
//Do Nothing
    }

    @Override
    public void setMap(Order newOrder) throws FlooringMasteryDaoException {
//Do Nothing
    }

    @Override
    public void replaceMap(Order newOrder, LocalDate date) throws FlooringMasteryDaoException {
//Do Nothing
    }

    @Override
    public void checkDate(LocalDate date) throws FlooringMasteryDaoException, IOException {
//Do Nothing
    }

    @Override
    public boolean checkDateExists(LocalDate date) throws FlooringMasteryDaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean checkOrderExists(LocalDate date, int orderNumber) throws FlooringMasteryDaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
