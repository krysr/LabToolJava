package com.example.labtooljava.Grade;

import com.example.labtooljava.Demo.Demo;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Grade implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "grade_id", nullable = false, updatable = false)
    private int gradeId;
    @Column(name = "grade_comment")
    private String gradeComment;
    @Column(name = "grade")
    private float grade;
    @Column(name = "assessment_name")
    private String assessmentName;

    @OneToOne
    @JoinColumn(name = "demo_id", referencedColumnName = "demo_id")
    private Demo demo;

    protected Grade() {}

    public Grade(int gradeId, String gradeComment, float grade, String assessmentName, Demo demo) {
        this.gradeId = gradeId;
        this.gradeComment = gradeComment;
        this.grade = grade;
        this.assessmentName = assessmentName;
        this.demo = demo;
    }

    public void setGrade_id(int gradeId) {
        this.gradeId = gradeId;
    }

    public int getGrade_id() {
        return gradeId;
    }

    public void setgradeComment(String gradeComment) {
        this.gradeComment = gradeComment;
    }

    public String getgradeComment() {
        return gradeComment;
    }

    public void setGrade(float grade) { this.grade = grade; }

    public float getGrade() {
        return grade;
    }

    public void setAssessmentName(String assessmentName) { this.assessmentName = assessmentName; }

    public String getAssessmentName() { return assessmentName; }

    public void setDemo(Demo demo) {
        this.demo = demo;
    }

    public Demo getDemo() {
        return demo;
    }
}
