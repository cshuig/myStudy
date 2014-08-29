package com.cshuig.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="t_classroom")
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
	
	@Id
	@GeneratedValue
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
	
	@OneToMany(mappedBy="classroom")
	@LazyCollection(LazyCollectionOption.EXTRA)//在获取.size()时,可以自动切换 select *  和   select count(*) 
	@Fetch(FetchMode.SUBSELECT)
	public Set<Student> getStus() {
		return stus;
	}
	public void setStus(Set<Student> stus) {
		this.stus = stus;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)//EAGER：默认值，只会发出一条带有join的hql语句，LAZY:延迟，每次都会发送一条hql
	@JoinColumn(name="s_id")
	public Special getSpecial() {
		return special;
	}
	public void setSpecial(Special special) {
		this.special = special;
	}
}
