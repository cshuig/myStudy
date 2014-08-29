package com.cshuig.entity;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 课程类，
 * 和教师类是Many2Many（多对多）的关系
 * 此时不直接使用 Many2Many，而是将之拆分为 两个One2Many(一对多)
 * @author Administrator
 */
@Entity
@Table(name="t_course")
public class Course {

	private int id;
	private String cname;
	private Set<TeacherCourse> tcs;
	
	public Course(){
		tcs = new HashSet<TeacherCourse>();
	}
	public void addTcs(TeacherCourse teacherCourse){
		tcs.add(teacherCourse);
	}
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="cname")
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	
	@OneToMany(mappedBy="course")
	public Set<TeacherCourse> getTcs() {
		return tcs;
	}
	public void setTcs(Set<TeacherCourse> tcs) {
		this.tcs = tcs;
	}
}
