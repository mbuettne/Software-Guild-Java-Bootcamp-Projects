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

    @Override
    public Order createOrder(String name, String state, String product, BigDecimal area) throws FlooringMasteryDaoException, IOException {
//load state taxes list and products prices lists
        dao.loadTaxes();
        taxes = dao.getTaxList();
        dao.loadProducts();
        products = dao.getProductList();

        //load orders list for date of entry, create new Order object with input data
        orders = listAllOrders(LocalDate.now());
        Order newOrder = new Order(name, state, product, area);

        //Set all order info
        newOrder.setTaxRate(taxes.get(state));
        newOrder.setProductCost(products.get(product).get(0));
        newOrder.setLaborCost(products.get(product).get(1));
        newOrder.setCostSqFt();
        newOrder.setLaborSqFt();
        newOrder.setTaxCost();
        newOrder.setTotalCost();
        newOrder.setDate(LocalDate.now());

        //Set order number after object is created and other info is set
        dao.loadOrderNumbers();
        dao.writeOrderNumbers();
        int nextOrderNum = dao.getNextOrderNumber();
        newOrder.setOrderNumber(nextOrderNum);
        
        //add order to list of orders for entry date, add order to map of all orders in dao
        orders.put(Integer.toString(newOrder.getOrderNumber()), newOrder);
        dao.setMap(newOrder);
        
        
        return newOrder;
    }

    @Override
    public void createNewFile(LocalDate date) throws FlooringMasteryDaoException, IOException {
        dao.createNewFile(date);
    }

    @Override
    public Order editOrder(LocalDate date, int orderNumber, Order edited) throws FlooringMasteryDaoException {
        //   orders = dao.loadOrders(date);
        Order orderToBeEdited = dao.editOrder(date, orderNumber, edited);
        dao.replaceMap(orderToBeEdited, date);
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

    @Override
    public void saveWork(LocalDate date) throws FlooringMasteryDaoException {
        dao.writeOrders(date);
    }
    
    @Override
    public void saveWork(LocalDate date, Order newOrder) throws FlooringMasteryDaoException{
        dao.writeOrders(date, newOrder);
    }
    
    @Override
    public void saveEdits(LocalDate date) throws FlooringMasteryDaoException{
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

}
