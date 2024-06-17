package com.restapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int deptId;
    private String deptName;
    private String subject;

    //@OneToOne(mappedBy = "dept"):-isse kya hoga ki department class me teacher wala column create nhi hoga but mapping ab yaha dono
    // Taraf se ho rha hai Teacher to Department and Department to Teacher
    @OneToOne(mappedBy = "dept")
    @JsonBackReference   //so isse wo back hokar department class par nhi jaayega.
    private Teacher teacher;

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
