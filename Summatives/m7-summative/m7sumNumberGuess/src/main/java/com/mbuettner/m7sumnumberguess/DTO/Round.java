/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.m7sumnumberguess.DTO;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author mbuet
 */
public class Round {
    private int roundsId;
    private String guess;
    private String timestamp;
    private String result;
    private int gameId;

    public Round() {
    }

    public Round(String guess, int gameId) {
        this.guess = guess;
        this.gameId = gameId;
    }

    public int getRoundsId() {
        return roundsId;
    }

    public void setRoundsId(int roundsId) {
        this.roundsId = roundsId;
    }

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp.toString();
    }
    
        public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.roundsId;
        hash = 67 * hash + Objects.hashCode(this.guess);
        hash = 67 * hash + Objects.hashCode(this.timestamp);
        hash = 67 * hash + Objects.hashCode(this.result);
        hash = 67 * hash + this.gameId;
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
        final Round other = (Round) obj;
        if (this.roundsId != other.roundsId) {
            return false;
        }
        if (this.gameId != other.gameId) {
            return false;
        }
        if (!Objects.equals(this.guess, other.guess)) {
            return false;
        }
        if (!Objects.equals(this.timestamp, other.timestamp)) {
            return false;
        }
        if (!Objects.equals(this.result, other.result)) {
            return false;
        }
        return true;
    }
    
    
    
}
