/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.flooringmastery.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

/**
 *
 * @author mbuet
 */
public class Order {

    private String name;
    private String state;
    private String product;
    private BigDecimal area;
    private LocalDate date;
    private BigDecimal productCost;
    private BigDecimal laborCost;
    private BigDecimal tax;
    private BigDecimal totalCost;
    private int orderNumber;

    public Order(String name, String state, String product, BigDecimal area) {
        this.name = name;
        this.state = state;
        this.product = product;
        this.area = area;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public LocalDate getDate() {
        return date;
    }

    public BigDecimal getProductCost() {
        return productCost;
    }

    public BigDecimal getLaborCost() {
        return laborCost;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setProductCost() {
        BigDecimal newProductCost = new BigDecimal("");
        if (product.equalsIgnoreCase("Carpet")) {
            newProductCost = new BigDecimal("2.25");
        } else if (product.equalsIgnoreCase("Laminate")) {
            newProductCost = new BigDecimal("1.75");
        } else if (product.equalsIgnoreCase("Tile")) {
            newProductCost = new BigDecimal("3.50");
        } else if (product.equalsIgnoreCase("Wood")) {
            newProductCost = new BigDecimal("5.15");
        }
        this.productCost = newProductCost;
    }
    
        public void setLaborCost() {
        BigDecimal newLaborCost = new BigDecimal("");
        if (product.equalsIgnoreCase("Carpet")) {
            newLaborCost = new BigDecimal("2.10");
        } else if (product.equalsIgnoreCase("Laminate")) {
            newLaborCost = new BigDecimal("2.10");
        } else if (product.equalsIgnoreCase("Tile")) {
            newLaborCost = new BigDecimal("4.15");
        } else if (product.equalsIgnoreCase("Wood")) {
            newLaborCost = new BigDecimal("4.75");
        }
        this.laborCost = newLaborCost;
    }
        
        public void setTax(){
            BigDecimal newTax = new BigDecimal("");
            if(state.equalsIgnoreCase("OH")){
                newTax = new BigDecimal("6.25");
            } else if(state.equalsIgnoreCase("PA")){
                newTax = new BigDecimal("6.75");
            } else if(state.equalsIgnoreCase("MI")){
                newTax = new BigDecimal("5.75");
            } else if(state.equalsIgnoreCase("IN")){
                newTax = new BigDecimal("6.00");
            }
            this.tax = newTax;
        }
        
        public void setTotalCost(){
            BigDecimal total = this.productCost.add(this.laborCost).add(this.tax);
            this.totalCost = total.setScale(2, RoundingMode.HALF_UP);
        }
}
