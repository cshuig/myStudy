package com.cshuig.entity;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="t_student")
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY) //开启二级缓存
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * 1、不要在多的一方使用级联操作：(cascade=CascadeType.ALL)，会发现save(学生)，会自动update班级，妹的
	 * 2、EAGER：默认值，
	 * 			load时只会发出一条带有join的hql语句，LAZY:延迟，每次都会发送一条hql
	 * 			当取到多个对象时，不使用到对象中的关联对象，则也会发出多条sql
	 * @return
	 */
	@ManyToOne(fetch=FetchType.LAZY) //
	@JoinColumn(name="c_id")
	public Classroom getClassroom() {
		return classroom;
	}

	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}
}
