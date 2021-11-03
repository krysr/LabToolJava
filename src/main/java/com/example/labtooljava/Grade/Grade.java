package com.example.labtooljava.Grade;

import com.example.labtooljava.PersonLab.PersonLab;

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
    @JoinColumn(name = "lab_user_id_fk", referencedColumnName = "lab_user_id")
    private PersonLab personlab;

    protected Grade() {}

    public Grade(int gradeId, String gradeComment, float grade, String assessmentName, PersonLab personlab) {
        this.gradeId = gradeId;
        this.gradeComment = gradeComment;
        this.grade = grade;
        this.assessmentName = assessmentName;
        this.personlab = personlab;
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

    public void setPersonLab(PersonLab personLab) {
        this.personlab = personLab;
    }

    public PersonLab getPersonLab() {
        return personlab;
    }
}
