package com.example.labtooljava.Lab;

import com.example.labtooljava.LabClass.LabClass;
import com.example.labtooljava.PersonLab.PersonLab;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "lab")
public class Lab implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "lab_id", nullable = false, updatable = false)
    private int labId;
    @Column(name = "lab_day")
    private String labDay;
//    @Column(name = "class_id_fk")
//    private String classId;
    @Column(name = "start_time")
    private int startTime;
    @Column(name = "end_time")
    private int endTime;

//    @OneToMany(fetch = FetchType.EAGER,mappedBy="lab",cascade = CascadeType.ALL)
//    private Set<PersonLab> personlabs;


    @ManyToOne
    @JoinColumn(name = "class_id_fk", referencedColumnName = "class_id")
    private LabClass labclass;

    protected Lab() {}

    public Lab(int labId, String labDay, int startTime, int endTime, LabClass labclass) {
        this.labId = labId;
        this.labDay = labDay;
        this.startTime = startTime;
        this.endTime = endTime;
        //this.personlabs = personlabs;
        this.labclass = labclass;
        //this.classId = classId;
    }

    public void setlabDay(String labDay) {
        this.labDay = labDay;
    }

    public String getlabDay() {
        return this.labDay;
    }

    public void setstartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getstartTime() {
        return this.startTime;
    }

    public void setendTime(int endTime) {
        this.endTime = endTime;
    }

    public int getendTime() {
        return this.endTime;
    }

    public int getLabId() {
        return labId;
    }

    public void setLabId(int labId) {
        this.labId = labId;
    }
//
//    public Set<PersonLab> getpersonlabs() {
//        return personlabs;
//    }
//
//    public void setpersonLabs(Set<PersonLab> personlabs) {
//        this.personlabs = personlabs;
//    }

    public LabClass getLabClass() {
        return labclass;
    }

    public void setLabClass(LabClass labclass) {
        this.labclass = labclass;
    }
}