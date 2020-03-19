/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.flooringmastery.service;

import com.mbuettner.flooringmastery.dao.FlooringMasteryDao;
import com.mbuettner.flooringmastery.dao.FlooringMasteryDaoException;
import com.mbuettner.flooringmastery.dto.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author mbuet
 */
public class FlooringMasteryServiceLayerImpl implements FlooringMasteryServiceLayer {

    private FlooringMasteryDao dao;

    public FlooringMasteryServiceLayerImpl(FlooringMasteryDao dao) {
        this.dao = dao;
    }

    //  private Map<LocalDate, Map<String, Order>> ordersByDate = new HashMap<>();
    private Map<String, Order> orders = new HashMap<>();

    @Override
    public Order createOrder(String name, String state, String product, BigDecimal area) throws FlooringMasteryDaoException {
        dao.createNewFile(LocalDate.now());

        dao.loadOrderNumbers();
        dao.writeOrderNumbers();
        int nextOrderNum = dao.getNextOrderNumber();
        orders = listAllOrders(LocalDate.now());
        Order newOrder = new Order(name, state, product, area);
        newOrder.setOrderNumber(nextOrderNum);
        newOrder.setTaxRate();
        newOrder.setProductCost();
        newOrder.setLaborCost();
        newOrder.setCostSqFt();
        newOrder.setLaborSqFt();
        newOrder.setTaxCost();
        newOrder.setTotalCost();
        newOrder.setDate(LocalDate.now());

        orders.put(Integer.toString(newOrder.getOrderNumber()), newOrder);
        dao.setMap(newOrder);
        //ordersByDate.put(newOrder.getDate(), orders);

        dao.writeOrders(LocalDate.now());
        return newOrder;
    }

    @Override
    public Order editOrder(LocalDate date, int orderNumber, Order edited) throws FlooringMasteryDaoException {
     //   orders = dao.loadOrders(date);
        Order orderToBeEdited = dao.editOrder(date, orderNumber, edited);

        return orderToBeEdited;
    }

    @Override
    public void removeOrder(LocalDate date, int orderNumber) throws FlooringMasteryDaoException {
        dao.removeOrder(date, orderNumber);
    }

    @Override
    public HashMap<String, Order> listAllOrders(LocalDate date) throws FlooringMasteryDaoException {
        return dao.loadOrders(date);
    }

//    @Override
//    public void saveWork() throws FlooringMasteryDaoException {
//        dao.writeOrders();
//    }
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

}
