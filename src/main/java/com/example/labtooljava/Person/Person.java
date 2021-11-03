package com.example.labtooljava.Person;

import com.example.labtooljava.PersonLab.PersonLab;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "person")
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
    @Column(name = "user_password")
    private String password;

//    @OneToMany(targetEntity = PersonLab.class, fetch = FetchType.EAGER,mappedBy="person",cascade = CascadeType.ALL)
//    private Set<PersonLab> personLabs;

    protected Person() {}

    public Person(String userId, String firstName, String lastName, String email, String role, String password) {
        this.dsUsername = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.password = password;
        //this.personLabs = personLabs;
    }

    public void setDsUsername() {
        dsUsername = dsUsername;
    }

    public String getDsUsername() {
        return dsUsername;
    }

    public void setFirstName() {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setLastName() {
        this.lastName = lastName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setEmail() {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setRole() {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }

    public void setPassword() { password = password; }

    public String getPassword() { return password; }
//
//    public Set<PersonLab> getPersonLab() {
//        return personLabs;
//    }
//
//    public void setPersonLab(Set<PersonLab> personLabs) {
//        this.personLabs = personLabs;
//    }

    @Override
    public String toString() {
        return dsUsername;
    }

}
