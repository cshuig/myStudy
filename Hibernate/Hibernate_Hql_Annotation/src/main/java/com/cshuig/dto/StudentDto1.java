package com.cshuig.dto;

import com.cshuig.entity.Classroom;
import com.cshuig.entity.Special;
import com.cshuig.entity.Student;

public class StudentDto1 {

	private Student stu;
	private Classroom clas;
	private Special spe;
	
	public StudentDto1(){}
	
	public StudentDto1(Student stu, Classroom clas, Special spe) {
		super();
		this.stu = stu;
		this.clas = clas;
		this.spe = spe;
	}


	public Student getStu() {
		return stu;
	}
	public void setStu(Student stu) {
		this.stu = stu;
	}
	public Classroom getClas() {
		return clas;
	}
	public void setClas(Classroom clas) {
		this.clas = clas;
	}
	public Special getSpe() {
		return spe;
	}
	public void setSpe(Special spe) {
		this.spe = spe;
	}
}
