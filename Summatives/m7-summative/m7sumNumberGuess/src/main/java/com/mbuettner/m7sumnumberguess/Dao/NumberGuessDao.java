/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.m7sumnumberguess.Dao;

import com.mbuettner.m7sumnumberguess.DTO.Game;

/**
 *
 * @author mbuet
 */
public interface NumberGuessDao {
    
    public Game createGame();
    
    public String roundResult();
}
