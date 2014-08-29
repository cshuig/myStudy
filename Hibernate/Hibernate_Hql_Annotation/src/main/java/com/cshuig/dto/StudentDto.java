package com.cshuig.dto;

public class StudentDto {

	private int id;
	private String sname;
	private String sex;
	private String cname;
	private String speName;
	
	public StudentDto(){
	}
	
	public StudentDto(int id, String sname, String sex, String cname,
			String speName) {
		super();
		this.id = id;
		this.sname = sname;
		this.sex = sex;
		this.cname = cname;
		this.speName = speName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getSpeName() {
		return speName;
	}
	public void setSpeName(String speName) {
		this.speName = speName;
	}

	@Override
	public String toString() {
		return "StudentDto [id=" + id + ", sname=" + sname + ", sex=" + sex
				+ ", cname=" + cname + ", speName=" + speName + "]";
	}
}
