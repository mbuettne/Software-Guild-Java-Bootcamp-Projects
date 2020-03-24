/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.m3.summative.service;

/**
 *
 * @author mbuet
 */
public class noItemInventoryException extends Exception {
    
    public noItemInventoryException(String message){
        super(message);
    }
    
    public noItemInventoryException(String message, Throwable cause){
        super(message, cause);
    }
}
