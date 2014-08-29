package com.cshuig.entity;


public class Student {

	private int id;
	private String sname;
	private String sex;
	
	private Classroom classroom;

	public Student(){
		
	}
	public Student(String sname, String sex, Classroom classroom){
		this.sname = sname;
		this.sex = sex;
		this.classroom = classroom;
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Classroom getClassroom() {
		return classroom;
	}

	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}
}
