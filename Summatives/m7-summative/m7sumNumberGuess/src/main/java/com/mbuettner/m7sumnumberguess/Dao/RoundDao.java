/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.m7sumnumberguess.Dao;

import com.mbuettner.m7sumnumberguess.DTO.Round;
import java.util.List;

/**
 *
 * @author mbuet
 */
public interface RoundDao {
    public Round addRound(Round round);
    
    public List<Round> getAllRoundsByGame(int gameId);
    
    public Round getLatestRound(int gameId);
}
