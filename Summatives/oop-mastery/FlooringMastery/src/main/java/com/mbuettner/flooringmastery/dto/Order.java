/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.flooringmastery.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;

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

    public void setLaborCost(BigDecimal laborCost) {
        this.laborCost = laborCost;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.name);
        hash = 79 * hash + Objects.hashCode(this.state);
        hash = 79 * hash + Objects.hashCode(this.product);
        hash = 79 * hash + Objects.hashCode(this.area);
        hash = 79 * hash + Objects.hashCode(this.date);
        hash = 79 * hash + Objects.hashCode(this.productCost);
        hash = 79 * hash + Objects.hashCode(this.laborCost);
        hash = 79 * hash + Objects.hashCode(this.costSqFt);
        hash = 79 * hash + Objects.hashCode(this.laborSqFt);
        hash = 79 * hash + Objects.hashCode(this.taxRate);
        hash = 79 * hash + Objects.hashCode(this.taxCost);
        hash = 79 * hash + Objects.hashCode(this.totalCost);
        hash = 79 * hash + this.orderNumber;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Order other = (Order) obj;
        if (this.orderNumber != other.orderNumber) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        if (!Objects.equals(this.product, other.product)) {
            return false;
        }
        if (!Objects.equals(this.area, other.area)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.productCost, other.productCost)) {
            return false;
        }
        if (!Objects.equals(this.laborCost, other.laborCost)) {
            return false;
        }
        if (!Objects.equals(this.costSqFt, other.costSqFt)) {
            return false;
        }
        if (!Objects.equals(this.laborSqFt, other.laborSqFt)) {
            return false;
        }
        if (!Objects.equals(this.taxRate, other.taxRate)) {
            return false;
        }
        if (!Objects.equals(this.taxCost, other.taxCost)) {
            return false;
        }
        if (!Objects.equals(this.totalCost, other.totalCost)) {
            return false;
        }
        return true;
    }
    
    
}
