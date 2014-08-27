package com.cshuig.model;

/**
 * Created by Administrator on 2014/8/26 0026.
 */
public class Student {
    private int id;
    private String sname;
    private Group group;

    public Student(int id, String sname) {
        this.id = id;
        this.sname = sname;
    }

    public Student() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
