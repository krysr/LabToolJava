package com.example.labtooljava.LabClass;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "labclass")
public class LabClass implements Serializable {

    @Id
    @Column(name = "class_id", nullable = false, updatable = false)
    private String classId;
    @Column(name = "class_name")
    private String className;

    protected LabClass() {}

    public LabClass(String classId, String className) {
        this.classId = classId;
        this.className = className;
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

}
