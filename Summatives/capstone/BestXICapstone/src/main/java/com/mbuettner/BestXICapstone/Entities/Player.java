/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.BestXICapstone.Entities;

import com.mbuettner.BestXICapstone.Repositories.teamRepo;
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
import javax.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author mbuet
 */
@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int playerid;
    @NotBlank(message = "First name must not be blank.")
    @Size(max = 25, message = "First name must be less than 25 characters.")
    @Column(nullable = false)
    private String firstname;
    @NotBlank(message = "Last name must not be blank.")
    @Size(max = 50, message = "Last name must be less than 50 characters.")
    @Column(nullable = false)
    private String lastname;
    @Column(nullable = true)
    private int height;
    @Column(nullable = true)
    private int weight;
    @NotBlank(message = "Player position must not be blank.")
    @Column(nullable = false)
    private String playerposition;
    @NotBlank(message = "Dominant Foot must not be blank.")
    @Column(nullable = false)
    private String dominantfoot;
    @JoinColumn(name = "teamid", nullable = false)
    private int teamid;

    public Player() {
    }

    public int getPlayerid() {
        return playerid;
    }

    public void setPlayerid(int playerid) {
        this.playerid = playerid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getPlayerposition() {
        return playerposition;
    }

    public void setPlayerposition(String playerposition) {
        this.playerposition = playerposition;
    }

    public String getDominantfoot() {
        return dominantfoot;
    }

    public void setDominantfoot(String dominantfoot) {
        this.dominantfoot = dominantfoot;
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
        hash = 89 * hash + this.playerid;
        hash = 89 * hash + Objects.hashCode(this.firstname);
        hash = 89 * hash + Objects.hashCode(this.lastname);
        hash = 89 * hash + this.height;
        hash = 89 * hash + this.weight;
        hash = 89 * hash + Objects.hashCode(this.playerposition);
        hash = 89 * hash + Objects.hashCode(this.dominantfoot);
        hash = 89 * hash + this.teamid;
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
        final Player other = (Player) obj;
        if (this.playerid != other.playerid) {
            return false;
        }
        if (this.height != other.height) {
            return false;
        }
        if (this.weight != other.weight) {
            return false;
        }
        if (this.teamid != other.teamid) {
            return false;
        }
        if (!Objects.equals(this.firstname, other.firstname)) {
            return false;
        }
        if (!Objects.equals(this.lastname, other.lastname)) {
            return false;
        }
        if (!Objects.equals(this.playerposition, other.playerposition)) {
            return false;
        }
        if (!Objects.equals(this.dominantfoot, other.dominantfoot)) {
            return false;
        }
        return true;
    }
    
    
   
}
