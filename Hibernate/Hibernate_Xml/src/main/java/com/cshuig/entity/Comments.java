package com.cshuig.entity;

/**
 * 评论类
 * @author Administrator
 *
 */
public class Comments {
	private int id;
	private String content;
	private Title title;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Title getTitle() {
		return title;
	}
	public void setTitle(Title title) {
		this.title = title;
	}
}
