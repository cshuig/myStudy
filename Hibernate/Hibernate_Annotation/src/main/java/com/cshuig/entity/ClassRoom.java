package com.cshuig.entity;

/**
 * 班级类：
 * 和学生类类是 One2Many 的关系
 * @author Administrator
 *
 */
public class ClassRoom {

	private int id;
	private String name;
	private int grade;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
}
