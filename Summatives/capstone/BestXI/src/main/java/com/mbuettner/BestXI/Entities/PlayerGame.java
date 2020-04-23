/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.BestXI.Entities;

import java.util.Objects;

/**
 *
 * @author mbuet
 */
public class PlayerGame {
    private int playerGameId;
    private int shots;
    private int goals;
    private int assists;
    private int dribbles;
    private int passes;
    private int passPercentage;
    private int tackles;
    private int interceptions;
    private int shotsDefensed;
    private int shotsSaved;
    private int goalsAllowed;
    private Player player;

    public int getPlayerGameId() {
        return playerGameId;
    }

    public void setPlayerGameId(int playerGameId) {
        this.playerGameId = playerGameId;
    }

    public int getShots() {
        return shots;
    }

    public void setShots(int shots) {
        this.shots = shots;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getDribbles() {
        return dribbles;
    }

    public void setDribbles(int dribbles) {
        this.dribbles = dribbles;
    }

    public int getPasses() {
        return passes;
    }

    public void setPasses(int passes) {
        this.passes = passes;
    }

    public int getPassPercentage() {
        return passPercentage;
    }

    public void setPassPercentage(int passPercentage) {
        this.passPercentage = passPercentage;
    }

    public int getTackles() {
        return tackles;
    }

    public void setTackles(int tackles) {
        this.tackles = tackles;
    }

    public int getInterceptions() {
        return interceptions;
    }

    public void setInterceptions(int interceptions) {
        this.interceptions = interceptions;
    }

    public int getShotsDefensed() {
        return shotsDefensed;
    }

    public void setShotsDefensed(int shotsDefensed) {
        this.shotsDefensed = shotsDefensed;
    }

    public int getShotsSaved() {
        return shotsSaved;
    }

    public void setShotsSaved(int shotsSaved) {
        this.shotsSaved = shotsSaved;
    }

    public int getGoalsAllowed() {
        return goalsAllowed;
    }

    public void setGoalsAllowed(int goalsAllowed) {
        this.goalsAllowed = goalsAllowed;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.playerGameId;
        hash = 79 * hash + this.shots;
        hash = 79 * hash + this.goals;
        hash = 79 * hash + this.assists;
        hash = 79 * hash + this.dribbles;
        hash = 79 * hash + this.passes;
        hash = 79 * hash + this.passPercentage;
        hash = 79 * hash + this.tackles;
        hash = 79 * hash + this.interceptions;
        hash = 79 * hash + this.shotsDefensed;
        hash = 79 * hash + this.shotsSaved;
        hash = 79 * hash + this.goalsAllowed;
        hash = 79 * hash + Objects.hashCode(this.player);
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
        final PlayerGame other = (PlayerGame) obj;
        if (this.playerGameId != other.playerGameId) {
            return false;
        }
        if (this.shots != other.shots) {
            return false;
        }
        if (this.goals != other.goals) {
            return false;
        }
        if (this.assists != other.assists) {
            return false;
        }
        if (this.dribbles != other.dribbles) {
            return false;
        }
        if (this.passes != other.passes) {
            return false;
        }
        if (this.passPercentage != other.passPercentage) {
            return false;
        }
        if (this.tackles != other.tackles) {
            return false;
        }
        if (this.interceptions != other.interceptions) {
            return false;
        }
        if (this.shotsDefensed != other.shotsDefensed) {
            return false;
        }
        if (this.shotsSaved != other.shotsSaved) {
            return false;
        }
        if (this.goalsAllowed != other.goalsAllowed) {
            return false;
        }
        if (!Objects.equals(this.player, other.player)) {
            return false;
        }
        return true;
    }
    
    
}
