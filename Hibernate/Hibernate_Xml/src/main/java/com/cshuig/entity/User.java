package com.cshuig.entity;

/**
 * 一个用户一个角色，
 * 一个角色对应多个用户
 * 这里主要是要测试：One2Many 双向
 * @author Administrator
 *
 */
public class User {

	private int id;
	private String name;
	private int age;
	
	private Role role;
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
}
