/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.flooringmastery.service;

import com.mbuettner.flooringmastery.dao.FlooringMasteryDao;
import com.mbuettner.flooringmastery.dto.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author mbuet
 */
public class FlooringMasteryServiceLayerImpl implements FlooringMasteryServiceLayer {
    private FlooringMasteryDao dao;
    
    public FlooringMasteryServiceLayerImpl(FlooringMasteryDao dao){
        this.dao = dao;
    }
    
    private Map <LocalDate, Map<String, Order>> ordersByDate = new HashMap<>();
    private Map <String, Order> orders = new HashMap<>();

    @Override
    public Order createOrder(String name, String state, String product, BigDecimal area) {
        Order newOrder = new Order(name, state, product, area);
        newOrder.setOrderNumber(orders.size() + 1);
        newOrder.setTaxRate();
        newOrder.setProductCost();
        newOrder.setLaborCost();
        newOrder.setMaterialCost();
        newOrder.setLaborTotalCost();
        newOrder.setTaxCost();
        newOrder.setTotalCost();
        newOrder.setDate();
        
        orders.put(Integer.toString(newOrder.getOrderNumber()), newOrder);
        ordersByDate.put(newOrder.getDate(), orders);
        return newOrder;
    }

    @Override
    public Order editOrder(LocalDate date, int orderNumber, Order edited) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeOrder(LocalDate date, int orderNumber) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Order> listAllOrders(LocalDate date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveWork() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order findOrder(LocalDate date, int orderNumber) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
