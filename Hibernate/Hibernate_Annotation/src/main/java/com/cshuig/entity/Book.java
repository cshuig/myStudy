package com.cshuig.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * 书类
 * 和 作者类 之间 是  Many2Many 的关系
 * @author Administrator
 */
@Entity
@Table(name="t_book")
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
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="bname")
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	
	@ManyToMany
	@JoinTable(name="t_author_book",
			   // 我的主键在关联表中的 名称：字段名字
			   joinColumns={@JoinColumn(name="b_id")},
			   //对方主键在关联表中的名称:字段名字
			   inverseJoinColumns={@JoinColumn(name="a_id")}
			)
	public Set<Author> getAuthors() {
		return authors;
	}
	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}
}
