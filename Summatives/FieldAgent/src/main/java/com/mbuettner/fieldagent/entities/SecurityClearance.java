package com.mbuettner.fieldagent.entities;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class SecurityClearance {

    @Id
    private int securityClearanceId;
    @Column(name="name", nullable=false, length=50)
    private String name;

    public int getSecurityClearanceId() {
        return securityClearanceId;
    }

    public void setSecurityClearanceId(int securityClearanceId) {
        this.securityClearanceId = securityClearanceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.securityClearanceId;
        hash = 59 * hash + Objects.hashCode(this.name);
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
        final SecurityClearance other = (SecurityClearance) obj;
        if (this.securityClearanceId != other.securityClearanceId) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

}
