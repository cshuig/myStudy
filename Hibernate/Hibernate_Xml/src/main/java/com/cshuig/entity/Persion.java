package com.cshuig.entity;

/**
 * 人 和 信用卡类 之间： 一对一的关系
 * 一个人 只能有 一个信用卡号
 * @author Administrator
 */
public class Persion {
	
	private int id;
	private String pname;
	private IdCard idCard;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public IdCard getIdCard() {
		return idCard;
	}
	public void setIdCard(IdCard idCard) {
		this.idCard = idCard;
	}
}
