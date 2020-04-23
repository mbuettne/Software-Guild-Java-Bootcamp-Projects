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
public class Team {
    private int teamId;
    private String teamName;
    private String coachName;
    private String logoUrl;

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.teamId;
        hash = 79 * hash + Objects.hashCode(this.teamName);
        hash = 79 * hash + Objects.hashCode(this.coachName);
        hash = 79 * hash + Objects.hashCode(this.logoUrl);
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
        final Team other = (Team) obj;
        if (this.teamId != other.teamId) {
            return false;
        }
        if (!Objects.equals(this.teamName, other.teamName)) {
            return false;
        }
        if (!Objects.equals(this.coachName, other.coachName)) {
            return false;
        }
        if (!Objects.equals(this.logoUrl, other.logoUrl)) {
            return false;
        }
        return true;
    }


    
}
