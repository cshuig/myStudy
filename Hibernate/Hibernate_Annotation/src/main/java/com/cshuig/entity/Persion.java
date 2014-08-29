package com.cshuig.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 人 和 信用卡类 之间： 一对一的关系
 * 一个人 只能有 一个信用卡号
 * @author Administrator
 */
@Entity
@Table(name="t_persion")
public class Persion {
	
	private int id;
	private String pname;
	private IdCard idCard;
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="pname")
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="card_id",unique=true)
	public IdCard getIdCard() {
		return idCard;
	}
	public void setIdCard(IdCard idCard) {
		this.idCard = idCard;
	}
}
