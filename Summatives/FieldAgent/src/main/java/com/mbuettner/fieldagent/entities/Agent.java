package com.mbuettner.fieldagent.entities;

import java.time.LocalDate;
import static java.time.temporal.WeekFields.ISO;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Data
public class Agent {

    @Id
    @NotNull(message="Agent identifier must be unique.")
    private String identifier;
    
    @Column(name="first_name", nullable=false, length=25)
    @NotBlank(message = "First name cannot be empty.")
    @Size(max=25, message="First name cannot be longer than 25 characters.")
    private String firstName;
    
    @Column(name="middle_name", nullable=true, length=25)
    @Size(max=25, message="Middle name cannot be longer than 25 characters.")
    private String middleName;
    
    @Column(name="last_name", nullable=false, length=25)
    @NotBlank(message = "Last name cannot be empty.")
    @Size(max=25, message="Last name cannot be longer than 25 characters.")
    private String lastName;
    
    @Column(name="picture_url", nullable=true, length=255)
    private String pictureUrl;
    
    @Column(name="birth_date", nullable=false)
    @NotNull(message = "Birth date cannot be empty and must be between 1900 and 10 years ago.")
  @Past(message="Birth date must be in the past")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    
    @Column(name="height", nullable=false)
    @Min(36)
    @Max(96)
    private int height;
    
    @Column(name="activation_date", nullable=false)
    @NotNull(message="Activation date cannot be empty.")
    @Past(message="Activation date must be in the past.")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate activationDate;
    
    @Column(name="is_active", nullable=false)
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "agency_id")
    private Agency agency;

    @ManyToOne
    @JoinColumn(name = "security_clearance_id")
    private SecurityClearance securityClearance;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public LocalDate getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(LocalDate activationDate) {
        this.activationDate = activationDate;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.identifier);
        hash = 53 * hash + Objects.hashCode(this.firstName);
        hash = 53 * hash + Objects.hashCode(this.middleName);
        hash = 53 * hash + Objects.hashCode(this.lastName);
        hash = 53 * hash + Objects.hashCode(this.pictureUrl);
        hash = 53 * hash + Objects.hashCode(this.birthDate);
        hash = 53 * hash + this.height;
        hash = 53 * hash + Objects.hashCode(this.activationDate);
        hash = 53 * hash + (this.isActive ? 1 : 0);
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
        final Agent other = (Agent) obj;
        if (this.height != other.height) {
            return false;
        }
        if (this.isActive != other.isActive) {
            return false;
        }
        if (!Objects.equals(this.identifier, other.identifier)) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.middleName, other.middleName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.pictureUrl, other.pictureUrl)) {
            return false;
        }
        if (!Objects.equals(this.birthDate, other.birthDate)) {
            return false;
        }
        if (!Objects.equals(this.activationDate, other.activationDate)) {
            return false;
        }
        return true;
    }

}
