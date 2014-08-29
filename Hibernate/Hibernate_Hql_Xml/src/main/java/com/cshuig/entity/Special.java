package com.cshuig.entity;

import java.util.Set;

public class Special {

	private int id;
	private String sname;
	private Set<Classroom> clas;
	
	
	public Special(){
		
	}
	
	public Special(int id){
		this.id = id;
	}
	
	public Special(String sname){
		this.sname = sname;
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
	
	public Set<Classroom> getClas() {
		return clas;
	}
	public void setClas(Set<Classroom> clas) {
		this.clas = clas;
	}
}
