package com.example.labtooljava.Demo;

import com.example.labtooljava.Lab.Lab;
import com.example.labtooljava.Person.Person;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "demo")
public class Demo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "demo_id", nullable = false, updatable = false)
    private int demoId;

    @Column(name = "demo")
    private String demo;

    @Column(name = "position")
    private int position;

    @Column(name = "instructor")
    private boolean instructor;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "lab_id_fk", referencedColumnName = "lab_id")
    private Lab lab;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "person_id_fk", referencedColumnName = "ds_username")
    private Person person;

    protected Demo() {}

    public Demo(Lab lab, Person person, String demo, int position, boolean instructor) {
        this.lab = lab;
        this.person = person;
        this.demo = demo;
        this.position = position;
        this.instructor = instructor;
    }


    public int getdemoId() {
        return demoId;
    }

    public void setdemoId(int demoId) {
        this.demoId = demoId;
    }

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

    public String getDemo() {
        return demo;
    }

    public void setDemo(String demo) {
        this.demo = demo;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isInstructor() {
        return instructor;
    }

    public void setInstructor(boolean instructor) {
        this.instructor = instructor;
    }

}
