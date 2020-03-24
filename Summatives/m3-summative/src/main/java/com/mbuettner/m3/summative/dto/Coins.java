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
public enum Coins {
    QUARTER("25.00"), DIME("10.00"), NICKEL("5.00"), PENNY("1.00");
    
    public  String label;
    
    private Coins(String label){
        this.label = label;
    }
}
