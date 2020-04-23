/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.BestXI.Entities;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author mbuet
 */
public class TeamGame {
    private int teamGameId;
    private LocalDate gameDate;
    private String gameLocation;
    private String opponent;
    private int teamScore;
    private int opponentScore;
    private String result;
    private Team team;

    public int getTeamGameId() {
        return teamGameId;
    }

    public void setTeamGameId(int teamGameId) {
        this.teamGameId = teamGameId;
    }

    public LocalDate getGameDate() {
        return gameDate;
    }

    public void setGameDate(LocalDate gameDate) {
        this.gameDate = gameDate;
    }

    public String getGameLocation() {
        return gameLocation;
    }

    public void setGameLocation(String gameLocation) {
        this.gameLocation = gameLocation;
    }

    public String getOpponent() {
        return opponent;
    }

    public void setOpponent(String opponent) {
        this.opponent = opponent;
    }

    public int getTeamScore() {
        return teamScore;
    }

    public void setTeamScore(int teamScore) {
        this.teamScore = teamScore;
    }

    public int getOpponentScore() {
        return opponentScore;
    }

    public void setOpponentScore(int opponentScore) {
        this.opponentScore = opponentScore;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.teamGameId;
        hash = 53 * hash + Objects.hashCode(this.gameDate);
        hash = 53 * hash + Objects.hashCode(this.gameLocation);
        hash = 53 * hash + Objects.hashCode(this.opponent);
        hash = 53 * hash + this.teamScore;
        hash = 53 * hash + this.opponentScore;
        hash = 53 * hash + Objects.hashCode(this.result);
        hash = 53 * hash + Objects.hashCode(this.team);
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
        final TeamGame other = (TeamGame) obj;
        if (this.teamGameId != other.teamGameId) {
            return false;
        }
        if (this.teamScore != other.teamScore) {
            return false;
        }
        if (this.opponentScore != other.opponentScore) {
            return false;
        }
        if (!Objects.equals(this.gameLocation, other.gameLocation)) {
            return false;
        }
        if (!Objects.equals(this.opponent, other.opponent)) {
            return false;
        }
        if (!Objects.equals(this.result, other.result)) {
            return false;
        }
        if (!Objects.equals(this.gameDate, other.gameDate)) {
            return false;
        }
        if (!Objects.equals(this.team, other.team)) {
            return false;
        }
        return true;
    }
    
    
}
