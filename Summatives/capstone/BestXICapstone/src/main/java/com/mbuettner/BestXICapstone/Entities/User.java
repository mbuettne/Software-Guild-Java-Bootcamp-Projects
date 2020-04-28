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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *
 * @author mbuet
 */
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int userid;

    @NotBlank(message = "First name must not be blank.")
    @Size(max = 25, message = "First name must be less than 25 characters.")
    @Column(nullable = false)
    private String firstname;
    @NotBlank(message = "Last name must not be blank.")
    @Size(max = 50, message = "Last name must be less than 50 characters.")
    @Column(nullable = false)
    private String lastname;
    @NotBlank(message = "Username must not be blank.")
    @Size(max = 50, message = "Username must be less than 50 characters.")
    @Column(nullable = false)
    private String username;
    @NotBlank(message = "Password must not be blank.")
    @Size(max = 100, message = "Password must be less than 100 characters.")
    @Column(nullable = false)
    private String password;
    @Column
    private boolean enabled;
    @JoinColumn(name = "roleid", nullable = false)
    private int roleid;
    @JoinColumn(name = "teamid", nullable = false)
    private int teamid;
    
    public User(){
        
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
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
        hash = 67 * hash + this.userid;
        hash = 67 * hash + Objects.hashCode(this.firstname);
        hash = 67 * hash + Objects.hashCode(this.lastname);
        hash = 67 * hash + Objects.hashCode(this.username);
        hash = 67 * hash + Objects.hashCode(this.password);
        hash = 67 * hash + (this.enabled ? 1 : 0);
        hash = 67 * hash + this.roleid;
        hash = 67 * hash + this.teamid;
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
        final User other = (User) obj;
        if (this.userid != other.userid) {
            return false;
        }
        if (this.enabled != other.enabled) {
            return false;
        }
        if (this.roleid != other.roleid) {
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
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        return true;
    }

    

}
