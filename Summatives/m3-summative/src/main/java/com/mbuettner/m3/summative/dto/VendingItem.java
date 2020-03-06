/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.m3.summative.dto;

/**
 *
 * @author mbuet
 */
public class VendingItem {
    private String name;
    private int quantity;
    private double price;

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public VendingItem(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    
}
