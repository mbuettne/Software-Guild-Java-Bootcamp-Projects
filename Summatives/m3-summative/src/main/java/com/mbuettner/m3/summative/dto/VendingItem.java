/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.m3.summative.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author mbuet
 */
public class VendingItem {
    private String name;
    private int quantity;
    private BigDecimal price;

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price.setScale(2, RoundingMode.HALF_UP);
    }
    
    public void setPrice(BigDecimal price){
        this.price = price;
    }

    public VendingItem(String name, BigDecimal price, int quantity) {
        this.name = name;
        this.price = price.setScale(2, RoundingMode.HALF_UP);
        this.quantity = quantity;
    }
    
}
