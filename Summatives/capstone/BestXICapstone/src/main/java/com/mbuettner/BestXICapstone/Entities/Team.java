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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *
 * @author mbuet
 */
@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int teamid;
    @NotBlank(message = "Team name must not be blank.")
    @Column(nullable = false)
    @Size(max = 50, message = "Team name must be less than 50 characters.")
    private String teamname;
    @NotBlank(message = "Coach name must not be blank.")
    @Column(nullable = false)
    @Size(max = 50, message = "Coach name must be less than 50 characters.")
    private String coachname;
    @Column(nullable = true)
    private String logourl;

    public Team() {
    }

    public int getTeamid() {
        return teamid;
    }

    public void setTeamid(int teamid) {
        this.teamid = teamid;
    }

    public String getTeamname() {
        return teamname;
    }

    public void setTeamname(String teamname) {
        this.teamname = teamname;
    }

    public String getCoachname() {
        return coachname;
    }

    public void setCoachname(String coachname) {
        this.coachname = coachname;
    }

    public String getLogourl() {
        return logourl;
    }

    public void setLogourl(String logourl) {
        this.logourl = logourl;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.teamid;
        hash = 17 * hash + Objects.hashCode(this.teamname);
        hash = 17 * hash + Objects.hashCode(this.coachname);
        hash = 17 * hash + Objects.hashCode(this.logourl);
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
        if (this.teamid != other.teamid) {
            return false;
        }
        if (!Objects.equals(this.teamname, other.teamname)) {
            return false;
        }
        if (!Objects.equals(this.coachname, other.coachname)) {
            return false;
        }
        if (!Objects.equals(this.logourl, other.logourl)) {
            return false;
        }
        return true;
    }

    

}
