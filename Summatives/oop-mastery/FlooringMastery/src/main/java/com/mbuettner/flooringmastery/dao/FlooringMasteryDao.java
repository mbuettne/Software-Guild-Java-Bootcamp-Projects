/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.flooringmastery.dao;

import com.mbuettner.flooringmastery.dto.Order;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author mbuet
 */
public interface FlooringMasteryDao {
    public List<Order> getOrdersByDate(LocalDate date);
    public Order saveEdits(Order order);
    public Order getOrder(LocalDate date, int orderNumber);
}
