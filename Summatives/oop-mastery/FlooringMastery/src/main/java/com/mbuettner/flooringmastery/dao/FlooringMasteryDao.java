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
public interface FlooringMasteryDao {

    public Order getOrder(LocalDate date, int orderNumber)  throws FlooringMasteryDaoException;

    public Order removeOrder(LocalDate date, int orderNumber) throws FlooringMasteryDaoException;
    
    public Order editOrder(LocalDate date, int orderNumber, Order edited) throws FlooringMasteryDaoException;
    
    public void writeEdits(LocalDate date) throws FlooringMasteryDaoException;

    public void writeOrders(LocalDate date) throws FlooringMasteryDaoException;
    
    public void writeOrders(LocalDate date, Order newOrder) throws FlooringMasteryDaoException;

    public void writeOrderNumbers() throws FlooringMasteryDaoException;

    public HashMap<String, Order> loadOrders(LocalDate date) throws FlooringMasteryDaoException;

    public void loadDates() throws FlooringMasteryDaoException;

    public void loadOrderNumbers() throws FlooringMasteryDaoException;
    
    public void loadTaxes() throws FlooringMasteryDaoException;
    
    public HashMap<String, BigDecimal> getTaxList() throws FlooringMasteryDaoException;
    
    public HashMap<String, ArrayList<BigDecimal>> getProductList() throws FlooringMasteryDaoException;
    
    public void loadProducts() throws FlooringMasteryDaoException;

    public void createNewFile(LocalDate date) throws FlooringMasteryDaoException, IOException;

    public void setMap(Order newOrder)  throws FlooringMasteryDaoException;

    public void replaceMap(Order newOrder, LocalDate date) throws FlooringMasteryDaoException;

    public int getNextOrderNumber() throws FlooringMasteryDaoException;
    
    public void checkDate(LocalDate date) throws FlooringMasteryDaoException, IOException;
}
