package com.cshuig.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
/**
 * 角色类
 * 和用户之间 是  one2Many 的关系
 * @author Administrator
 */
@Entity
@Table(name="t_role")
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

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="rname")
	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}
	
	/**
	 * mappedBy : 自己不做关系维护，而由User.role属性来维护关系,即由多的一方来维护关系
	 */
	@OneToMany(mappedBy="role")
	//@JoinColumn(name="r_id")//我在对方的外键名称  即  在t_user表中的外键
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
}
