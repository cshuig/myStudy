package com.cshuig.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 信用卡类 
 * 和persion的类 是 一对一的关系
 * 一个人 只能有 一个信用卡号
 * @author Administrator
 */
@Entity
@Table(name="t_icCard")
public class IdCard {

	private int id;
	private String no;
	private Persion persion;
	
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="no")
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	
	@OneToOne(mappedBy="idCard")//然persion中的idCard来维护关系
	public Persion getPersion() {
		return persion;
	}
	public void setPersion(Persion persion) {
		this.persion = persion;
	}
}
