package com.example.labtooljava.PersonLab;

import com.example.labtooljava.Grade.Grade;
import com.example.labtooljava.Lab.Lab;
import com.example.labtooljava.Person.Person;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "personlab")
public class PersonLab implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "lab_user_id", nullable = false, updatable = false)
    private int labUserId;
//    @Column(name = "lab_id_fk")
//    private int labId;
//    @Column(name = "person_id_fk")
//    private String username;

    @Column(name = "demo")
    private boolean demo;

    @Column(name = "position")
    private int position;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "lab_id_fk", referencedColumnName = "lab_id")
    private Lab lab;
//
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "person_id_fk", referencedColumnName = "ds_username")
    private Person person;

//    @Transient
//    @OneToOne(mappedBy = "personlab", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Grade grade;

    protected PersonLab() {}

    public PersonLab(int labUserId, Lab lab, Person person, boolean demo, int position) {
        this.labUserId = labUserId;
        this.lab = lab;
        this.person = person;
        this.demo = demo;
        this.position = position;
       // this.grade = grade;
//        this.labId = labId;
//        this.username = username;
    }


    public int getLabUserId() {
        return labUserId;
    }

    public void setLabUserId(int labUserId) {
        this.labUserId = labUserId;
    }

//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }


    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Lab getLab() {
        return lab;
    }

    public void setLab(Lab lab) {
        this.lab = lab;
    }

    public boolean isDemo() {
        return demo;
    }

    public void setDemo(boolean demo) {
        this.demo = demo;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    //    public Grade getGrade() {
//        return grade;
//    }
//
//    public void setGrade(Grade grade) {
//        this.grade = grade;
//    }
}
