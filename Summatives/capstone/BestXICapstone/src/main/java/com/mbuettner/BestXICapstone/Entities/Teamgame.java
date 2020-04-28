/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.BestXICapstone.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
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
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author mbuet
 */
@Entity
public class Teamgame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int teamgameid;
    @Column(nullable = false)
    @NotNull(message = "Game date must not be blank.")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate gamedate;
    @Column(nullable = false)
    @NotBlank(message = "Game location must not be blank.")
    private String gamelocation;
    @Column(nullable = false)
    @NotBlank(message = "Game opponent must not be blank.")
    private String opponent;
    @Column(nullable = false)
    @NotNull(message = "Team score must not be blank.")
    @Min(0)
    private int teamscore;
    @Column(nullable = false)
    @NotNull(message = "Opponent score must not be blank.")
    @Min(0)
    private int opponentscore;
    @Column(nullable = false)
    @NotBlank(message = "Result must not be blank.")
    private String result;
    @JoinColumn(name = "teamid", nullable = false)
    @Min(value = 1, message = "Must choose team.")
    private int teamid;

    public Teamgame() {

    }

    public int getTeamgameid() {
        return teamgameid;
    }

    public void setTeamgameid(int teamgameid) {
        this.teamgameid = teamgameid;
    }

    public LocalDate getGamedate() {
        return gamedate;
    }

    public void setGamedate(LocalDate gamedate) {
        this.gamedate = gamedate;
    }

    public void setGamedate(String gamedate) {
        if(gamedate.equals("")){
            this.gamedate = null;
        } else {
               this.gamedate = LocalDate.parse(gamedate);
        }
     
    }

    public String getGamelocation() {
        return gamelocation;
    }

    public void setGamelocation(String gamelocation) {
        this.gamelocation = gamelocation;
    }

    public String getOpponent() {
        return opponent;
    }

    public void setOpponent(String opponent) {
        this.opponent = opponent;
    }

    public int getTeamscore() {
        return teamscore;
    }

    public void setTeamscore(int teamscore) {
        this.teamscore = teamscore;
    }

    public int getOpponentscore() {
        return opponentscore;
    }

    public void setOpponentscore(int opponentscore) {
        this.opponentscore = opponentscore;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getTeamid() {
        return teamid;
    }

    public void setTeamid(int teamid) {
        this.teamid = teamid;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + this.teamgameid;
        hash = 31 * hash + Objects.hashCode(this.gamedate);
        hash = 31 * hash + Objects.hashCode(this.gamelocation);
        hash = 31 * hash + Objects.hashCode(this.opponent);
        hash = 31 * hash + this.teamscore;
        hash = 31 * hash + this.opponentscore;
        hash = 31 * hash + Objects.hashCode(this.result);
        hash = 31 * hash + this.teamid;
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
        final Teamgame other = (Teamgame) obj;
        if (this.teamgameid != other.teamgameid) {
            return false;
        }
        if (this.teamscore != other.teamscore) {
            return false;
        }
        if (this.opponentscore != other.opponentscore) {
            return false;
        }
        if (this.teamid != other.teamid) {
            return false;
        }
        if (!Objects.equals(this.gamelocation, other.gamelocation)) {
            return false;
        }
        if (!Objects.equals(this.opponent, other.opponent)) {
            return false;
        }
        if (!Objects.equals(this.result, other.result)) {
            return false;
        }
        if (!Objects.equals(this.gamedate, other.gamedate)) {
            return false;
        }
        return true;
    }

}
