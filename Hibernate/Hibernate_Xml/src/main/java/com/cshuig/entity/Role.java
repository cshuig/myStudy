package com.cshuig.entity;

import java.util.HashSet;
import java.util.Set;
/**
 * 角色类
 * 和用户之间 是  one2Many 的关系
 * @author Administrator
 */
public class Role {

	private int id;
	private String rname;
	
	private Set<User> users ;
	
	public Role(){
		users = new HashSet<User>();
	}
	
	public void addUsers(User user){
		users.add(user);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
}
