package com.cshuig.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * 教师类，
 * 和课程类是  Many2Many(多对多的关系)
 * 此时不直接使用 Many2Many，而是将之拆分为 两个One2Many(一对多)
 * @author Administrator
 */
public class Teacher {
	
	private int id;
	private String tname;
	private Set<TeacherCourse> tcs;
	
	public void Teacher(){
		tcs = new HashSet<TeacherCourse>();
	}
	public void addTcs(TeacherCourse teacherCourse){
		tcs.add(teacherCourse);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public Set<TeacherCourse> getTcs() {
		return tcs;
	}
	public void setTcs(Set<TeacherCourse> tcs) {
		this.tcs = tcs;
	}
}
