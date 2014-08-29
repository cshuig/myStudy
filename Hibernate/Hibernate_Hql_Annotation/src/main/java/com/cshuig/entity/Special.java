package com.cshuig.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="t_special")
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
	
	@Id
	@GeneratedValue
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
	
	@OneToMany(mappedBy="special")
	@LazyCollection(LazyCollectionOption.EXTRA)//可以自动切换 select *  和   select count(*) 
	public Set<Classroom> getClas() {
		return clas;
	}
	public void setClas(Set<Classroom> clas) {
		this.clas = clas;
	}
}
