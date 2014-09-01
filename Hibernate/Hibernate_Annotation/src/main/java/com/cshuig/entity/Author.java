package com.cshuig.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * 作者类
 * 和书类是 Many2Many的关系
 * @author Administrator
 */
@Entity
@Table(name="t_author")
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
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="aname")
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	
	@ManyToMany(mappedBy="authors")//由我在对方中的属性来维护关系
	public Set<Book> getBooks() {
		return books;
	}
	public void setBooks(Set<Book> books) {
		this.books = books;
	}
}
