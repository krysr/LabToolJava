package com.example.labtooljava.Lab;

import com.example.labtooljava.LabClass.LabClass;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "lab")
public class Lab implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "lab_id", nullable = false, updatable = false)
    private int labId;
    @Column(name = "lab_day")
    private String labDay;
    @Column(name = "start_time")
    private int startTime;
    @Column(name = "end_time")
    private int endTime;
    @Column(name = "room")
    private String room;

    @ManyToOne
    @JoinColumn(name = "class_id_fk", referencedColumnName = "class_id")
    private LabClass labClass;

    protected Lab() {}

    public Lab(int labId, String labDay, int startTime, int endTime, LabClass labClass, String room) {
        this.labId = labId;
        this.labDay = labDay;
        this.startTime = startTime;
        this.endTime = endTime;
        this.labClass = labClass;
        this.room = room;
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

    public LabClass getLabClass() {
        return labClass;
    }

    public void setLabClass(LabClass labClass) {
        this.labClass = labClass;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getRoom() {
        return room;
    }
}
