package com.example.labtooljava.LabClass;

import com.example.labtooljava.Lab.Lab;
import com.example.labtooljava.PersonLab.PersonLab;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "labclass")
public class LabClass implements Serializable {

    @Id
    @Column(name = "class_id", nullable = false, updatable = false)
    private String classId;
    @Column(name = "class_name")
    private String className;

//    @OneToMany(fetch = FetchType.EAGER,mappedBy="labclass",cascade = CascadeType.ALL)
//    private Set<Lab> labs;

    protected LabClass() {}

    public LabClass(String classId, String className) {
        this.classId = classId;
        this.className = className;
       // this.labs = labs;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

//    public Set<Lab> getLabs() {
//        return labs;
//    }
//
//    public void setLabs(Set<Lab> labs) {
//        this.labs = labs;
//    }
}
