package com.cshuig.entity;

import java.util.Set;

public class Classroom {

	private int id;
	private String cname;
	private int grade;
	
	private Set<Student> stus;
	private Special special;//班级和专业是：多对一的关系
	
	
	public Classroom(){
		
	}
	
	public Classroom(int id){
		this.id = id;
	}
	
	public Classroom(String cname, int grade){
		this.cname = cname;
		this.grade = grade;
	}
	public Classroom(String cname, int grade,Special special){
		this.cname = cname;
		this.grade = grade;
		this.special = special;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	public Set<Student> getStus() {
		return stus;
	}
	public void setStus(Set<Student> stus) {
		this.stus = stus;
	}
	
	public Special getSpecial() {
		return special;
	}
	public void setSpecial(Special special) {
		this.special = special;
	}
}
