package com.cshuig.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * 文章
 * 和 评论(Comments) 是 一对多 的关系
 * @author Administrator
 *
 */
@Entity
@Table(name="t_title")
public class Title {

	private int id;
	private String tname;
	private String tcontent;
	private Set<Comments> comments ;
	
	public Title(){
		comments = new HashSet<Comments>();
	}
	public void addComments(Comments comment){
		comments.add(comment);
	}
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="tname")
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	
	@Column(name="tcontent")
	public String getTcontent() {
		return tcontent;
	}
	public void setTcontent(String tcontent) {
		this.tcontent = tcontent;
	}
	
	@OneToMany(mappedBy="title")
	@LazyCollection(LazyCollectionOption.EXTRA)
	public Set<Comments> getComments() {
		return comments;
	}
	public void setComments(Set<Comments> comments) {
		this.comments = comments;
	}
}
