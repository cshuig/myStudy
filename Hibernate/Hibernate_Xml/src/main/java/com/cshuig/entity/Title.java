package com.cshuig.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * 文章
 * 和 评论(Comments) 是 一对多 的关系
 * @author Administrator
 *
 */
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	
	public String getTcontent() {
		return tcontent;
	}
	public void setTcontent(String tcontent) {
		this.tcontent = tcontent;
	}
	public Set<Comments> getComments() {
		return comments;
	}
	public void setComments(Set<Comments> comments) {
		this.comments = comments;
	}
}
