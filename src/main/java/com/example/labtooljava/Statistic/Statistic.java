package com.example.labtooljava.Statistic;

import com.example.labtooljava.Demo.Demo;
import com.example.labtooljava.Lab.Lab;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "statistic")
public class Statistic implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stat_id", nullable = false, updatable = false)
    private int statId;

    @Column(name = "waiting_time")
    private int waitingTime;

    @Column(name = "demo_start_time", columnDefinition = "DATE")
    private Date demoStartTime;

    @Column(name = "demo_end_time", columnDefinition = "DATE")
    private Date demoEndTime;

    @Column(name = "date", columnDefinition = "DATE")
    private Date date;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "demo_id_fk", referencedColumnName = "demo_id")
    private Demo demo;

    protected Statistic() {
    }

    public Statistic(Demo demo, int waitingTime, Date demoStartTime, Date demoEndTime, Date date) {
        this.demo = demo;
        this.waitingTime = waitingTime;
        this.demoStartTime = demoStartTime;
        this.demoEndTime = demoEndTime;
        this.date = date;
    }

    public void setDemo(Demo demo) {
        this.demo = demo;
    }

    public Demo getDemo() {
        return demo;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setDemoStartTime(Date demoStartTime) {
        this.demoStartTime = demoStartTime;
    }

    public Date getDemoStartTime() {
        return demoStartTime;
    }

    public void setDemoEndTime(Date demoEndTime) {
        this.demoEndTime = demoEndTime;
    }

    public Date getDemoEndTime() {
        return demoEndTime;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
