package com.cshuig.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * 作者类
 * 和书类是 Many2Many的关系
 * @author Administrator
 */
public class Author {

	private int id;
	private String aname;
	private Set<Book> books;
	
	
	public Author(){
		books = new HashSet<Book>();
	}
	public void addBook(Book book){
		books.add(book);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	public Set<Book> getBooks() {
		return books;
	}
	public void setBooks(Set<Book> books) {
		this.books = books;
	}
}
