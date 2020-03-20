/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.flooringmastery.service;

import com.mbuettner.flooringmastery.dao.FlooringMasteryDaoException;
import com.mbuettner.flooringmastery.dto.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;

/**
 *
 * @author mbuet
 */
public interface FlooringMasteryServiceLayer {

    public Order createOrder(String name, String state, String product, BigDecimal area) throws FlooringMasteryDaoException;

    public Order editOrder(LocalDate date, int orderNumber, Order edited) throws FlooringMasteryDaoException ;

    public void removeOrder(LocalDate date, int orderNumber) throws FlooringMasteryDaoException ;

    public HashMap<String, Order> listAllOrders(LocalDate date) throws FlooringMasteryDaoException;

   // public void saveWork() throws FlooringMasteryDaoException;

    public Order findOrder(LocalDate date, int orderNumber) throws FlooringMasteryDaoException ;
}
