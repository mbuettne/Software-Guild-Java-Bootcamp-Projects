/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.BestXICapstone.Entities;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 *
 * @author mbuet
 */
@Entity
public class Playergame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int playergameid;
    @Column(nullable = false)
    @NotNull(message = "Shots cannot be blank")
    @Min(0)
    private int shots;
    @Column(nullable = false)
    @NotNull(message = "Goals cannot be blank")
    @Min(0)
    private int goals;
    @Column(nullable = false)
    @NotNull(message = "Assists cannot be blank")
    @Min(0)
    private int assists;
    @Column(nullable = false)
    @NotNull(message = "Dribbles cannot be blank")
    @Min(0)
    private int dribbles;
    @Column(nullable = false)
    @NotNull(message = "Passes cannot be blank")
    @Min(0)
    private int passes;
    @Column(nullable = false)
    @NotNull(message = "Pass Completion Percentage cannot be blank")
    @Min(0)
    private int passpercentage;
    @Column(nullable = false)
    @NotNull(message = "Tackles cannot be blank")
    @Min(0)
    private int tackles;
    @Column(nullable = false)
    @NotNull(message = "Interception cannot be blank")
    @Min(0)
    private int interceptions;
    @Column(nullable = false)
    @NotNull(message = "Shots defensed cannot be blank")
    @Min(0)
    private int shotsdefensed;
    @Column(nullable = false)
    @NotNull(message = "Shots saved cannot be blank")
    @Min(0)
    private int shotssaved;
    @Column(nullable = false)
    @NotNull(message = "Goals allowed cannot be blank")
    @Min(0)
    private int goalsallowed;
    @JoinColumn(name = "playerId", nullable = false)
    @NotNull(message = "Must choose a player.")
    @Min(value = 1, message = "Must choose a player.")
    private int playerid;

    public Playergame() {
    }

    public int getPlayergameid() {
        return playergameid;
    }

    public void setPlayergameid(int playergameid) {
        this.playergameid = playergameid;
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

    public int getPasspercentage() {
        return passpercentage;
    }

    public void setPasspercentage(int passpercentage) {
        this.passpercentage = passpercentage;
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

    public int getShotsdefensed() {
        return shotsdefensed;
    }

    public void setShotsdefensed(int shotsdefensed) {
        this.shotsdefensed = shotsdefensed;
    }

    public int getShotssaved() {
        return shotssaved;
    }

    public void setShotssaved(int shotssaved) {
        this.shotssaved = shotssaved;
    }

    public int getGoalsallowed() {
        return goalsallowed;
    }

    public void setGoalsallowed(int goalsallowed) {
        this.goalsallowed = goalsallowed;
    }

    public int getPlayerid() {
        return playerid;
    }

    public void setPlayerid(int playerid) {
        this.playerid = playerid;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + this.playergameid;
        hash = 61 * hash + this.shots;
        hash = 61 * hash + this.goals;
        hash = 61 * hash + this.assists;
        hash = 61 * hash + this.dribbles;
        hash = 61 * hash + this.passes;
        hash = 61 * hash + this.passpercentage;
        hash = 61 * hash + this.tackles;
        hash = 61 * hash + this.interceptions;
        hash = 61 * hash + this.shotsdefensed;
        hash = 61 * hash + this.shotssaved;
        hash = 61 * hash + this.goalsallowed;
        hash = 61 * hash + this.playerid;
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
        final Playergame other = (Playergame) obj;
        if (this.playergameid != other.playergameid) {
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
        if (this.passpercentage != other.passpercentage) {
            return false;
        }
        if (this.tackles != other.tackles) {
            return false;
        }
        if (this.interceptions != other.interceptions) {
            return false;
        }
        if (this.shotsdefensed != other.shotsdefensed) {
            return false;
        }
        if (this.shotssaved != other.shotssaved) {
            return false;
        }
        if (this.goalsallowed != other.goalsallowed) {
            return false;
        }
        if (this.playerid != other.playerid) {
            return false;
        }
        return true;
    }

   

}
