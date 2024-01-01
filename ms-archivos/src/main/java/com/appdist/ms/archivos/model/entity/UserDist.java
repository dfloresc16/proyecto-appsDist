package com.appdist.ms.archivos.model.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "userDist")
public class UserDist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String isActive;

    private String password;

    private String email;

    private String suscription;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSuscription() {
        return suscription;
    }

    public void setSuscription(String suscription) {
        this.suscription = suscription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDist userDist)) return false;
        return Objects.equals(id, userDist.id) && Objects.equals(firstName, userDist.firstName) && Objects.equals(lastName, userDist.lastName) && Objects.equals(isActive, userDist.isActive) && Objects.equals(password, userDist.password) && Objects.equals(email, userDist.email) && Objects.equals(suscription, userDist.suscription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, isActive, password, email, suscription);
    }
}
