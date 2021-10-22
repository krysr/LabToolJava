package com.example.labtooljava.Person;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import javax.persistence.*;

@Entity
//@Table(name = "Person")
public class Person implements Serializable {

    @Id
    @Column(name = "ds_username", nullable = false, updatable = false)
    private String dsUsername;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "role_type")
    private String role;

    protected Person() {}

    public Person(String userId, String firstName, String lastName, String email, String role) {
        this.dsUsername = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
    }

    public void setDsUsername() {
        dsUsername = dsUsername;
    }

    public String getDsUsername() {
        return dsUsername;
    }

    public void setFirstName() {
        firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName() {
        lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setEmail() {
        email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setRole() {
        role = role;
    }

    public String getRole() {
        return role;
    }

}
