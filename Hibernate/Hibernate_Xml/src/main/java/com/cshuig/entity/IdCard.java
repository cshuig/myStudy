package com.cshuig.entity;
/**
 * 信用卡类 
 * 和persion的类 是 一对一的关系
 * 一个人 只能有 一个信用卡号
 * @author Administrator
 */
public class IdCard {

	private int id;
	private String no;
	private Persion persion;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public Persion getPersion() {
		return persion;
	}
	public void setPersion(Persion persion) {
		this.persion = persion;
	}
}
