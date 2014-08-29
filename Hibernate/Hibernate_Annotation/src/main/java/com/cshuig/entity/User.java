package com.cshuig.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 一个用户一个角色，
 * 一个角色对应多个用户
 * 这里主要是要测试：One2Many 双向
 * @author Administrator
 */
@Entity
@Table(name="t_user")
public class User {

	private int id;
	private String name;
	private int age;
	private Role role;
	
	@Id
	@GeneratedValue  //表示他是主键，且自动递增
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="age",columnDefinition="int")
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	/**
	 * fetch : 可以配置为：EAGER(一条sql回去所有记录)、LAZY(延迟加载)
	 */
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="r_id")
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
}
