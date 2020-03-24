/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.m2.dao;

/**
 *
 * @author mbuet
 */
public class m2DaoException extends Exception {

    public m2DaoException(String message) {
        super(message);
    }

    public m2DaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
