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
public class insufficientFundsException extends Exception{
    
    public insufficientFundsException(String message){
        super(message);
    }
    
        public insufficientFundsException(String message, Throwable cause){
        super(message, cause);
    }
}
