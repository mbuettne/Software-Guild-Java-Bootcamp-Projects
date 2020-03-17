/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.flooringmastery.service;

import com.mbuettner.flooringmastery.dto.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author mbuet
 */
public interface FlooringMasteryServiceLayer {
    public Order createOrder(String name, String state, String product, BigDecimal area);
    
    public Order editOrder(LocalDate date, int orderNumber, Order edited);
    
    public void removeOrder(LocalDate date, int orderNumber);
    
    public List <Order> listAllOrders(LocalDate date);
    
    public void saveWork();
    
    public Order findOrder(LocalDate date, int orderNumber);
}
