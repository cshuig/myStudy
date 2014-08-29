package com.cshuig.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * 书类
 * 和 作者类 之间 是  Many2Many 的关系
 * @author Administrator
 */
public class Book {
	private int id;
	private String bname;
	private Set<Author> authors;
	
	
	public Book(){
		authors = new HashSet<Author>();
	}
	public void addBook(Author author){
		authors.add(author);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public Set<Author> getAuthors() {
		return authors;
	}
	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}
}
