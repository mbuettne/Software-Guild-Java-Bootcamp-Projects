/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.m3.summative.dto;

import java.math.BigDecimal;

/**
 *
 * @author mbuet
 */
public class User {
    private BigDecimal money;

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public User(){
        
    }
    
    public User(BigDecimal money) {
        this.money = money;
    }
    

 
    
    
}
