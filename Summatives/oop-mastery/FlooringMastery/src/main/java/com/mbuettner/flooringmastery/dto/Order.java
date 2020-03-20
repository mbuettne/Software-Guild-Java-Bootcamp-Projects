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
    private BigDecimal costSqFt;
    private BigDecimal laborSqFt;
    private BigDecimal taxRate;
    private BigDecimal taxCost;
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
        return this.date;
    }

    public void setDate() {
        this.date = LocalDate.now();
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getCostSqFt() {
        return costSqFt;
    }

    public void setCostSqFt(BigDecimal costSqFt) {
        this.costSqFt = costSqFt;
    }
    
    public void setCostSqFt(){
        BigDecimal costSqFtUnround = this.area.multiply(this.productCost);
        this.costSqFt = costSqFtUnround.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getLaborSqFt() {
        return laborSqFt;
    }

    public void setLaborSqFt(BigDecimal laborSqFt) {
        this.laborSqFt = laborSqFt;
    }
    
    public void setLaborSqFt(){
        BigDecimal laborSqFtUnround = this.area.multiply(this.laborCost);
        this.laborSqFt = laborSqFtUnround.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getProductCost() {
        return productCost;
    }

    public BigDecimal getLaborCost() {
        return laborCost;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setProductCost(BigDecimal productCost) {
        this.productCost = productCost;
    }

//    public void setProductCost() {
//        BigDecimal newProductCost = new BigDecimal("0");
//        if (product.equalsIgnoreCase("Carpet")) {
//            newProductCost = new BigDecimal("2.25");
//        } else if (product.equalsIgnoreCase("Laminate")) {
//            newProductCost = new BigDecimal("1.75");
//        } else if (product.equalsIgnoreCase("Tile")) {
//            newProductCost = new BigDecimal("3.50");
//        } else if (product.equalsIgnoreCase("Wood")) {
//            newProductCost = new BigDecimal("5.15");
//        }
//        this.productCost = newProductCost;
//    }

    public void setLaborCost(BigDecimal laborCost) {
        this.laborCost = laborCost;
    }

//    public void setLaborCost() {
//        BigDecimal newLaborCost = new BigDecimal("0");
//        if (product.equalsIgnoreCase("Carpet")) {
//            newLaborCost = new BigDecimal("2.10");
//        } else if (product.equalsIgnoreCase("Laminate")) {
//            newLaborCost = new BigDecimal("2.10");
//        } else if (product.equalsIgnoreCase("Tile")) {
//            newLaborCost = new BigDecimal("4.15");
//        } else if (product.equalsIgnoreCase("Wood")) {
//            newLaborCost = new BigDecimal("4.75");
//        }
//        this.laborCost = newLaborCost;
//    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

//    public void setTaxRate() {
//        BigDecimal newTaxRate = new BigDecimal("0");
//        if (state.equalsIgnoreCase("OH")) {
//            newTaxRate = new BigDecimal("6.25");
//        } else if (state.equalsIgnoreCase("PA")) {
//            newTaxRate = new BigDecimal("6.75");
//        } else if (state.equalsIgnoreCase("MI")) {
//            newTaxRate = new BigDecimal("5.75");
//        } else if (state.equalsIgnoreCase("IN")) {
//            newTaxRate = new BigDecimal("6.00");
//        }
//        this.taxRate = newTaxRate;
//    }

    public BigDecimal getTaxCost() {
        return taxCost;
    }

    public void setTaxCost(BigDecimal taxCost) {
        this.taxCost = taxCost;
    }

    public void setTaxCost() {
        BigDecimal hundred = new BigDecimal("100");
        BigDecimal taxRatePercent = this.taxRate.divide(hundred);
        BigDecimal costsAdded = this.costSqFt.add(this.laborSqFt);
        BigDecimal taxCostUnround = costsAdded.multiply(taxRatePercent);
        this.taxCost = taxCostUnround.setScale(2, RoundingMode.HALF_UP);
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public void setTotalCost() {
        BigDecimal total = this.costSqFt.add(this.laborSqFt).add(this.taxCost);
        this.totalCost = total.setScale(2, RoundingMode.HALF_UP);
    }
}
