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
    QUARTER("25"), DIME("10"), NICKEL("5"), PENNY("1");
    
    public  String label;
    
    private Coins(String label){
        this.label = label;
    }
}
