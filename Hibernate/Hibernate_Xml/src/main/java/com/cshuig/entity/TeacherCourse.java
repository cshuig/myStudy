package com.cshuig.entity;

/**
 * 教师和课程的中间类，它们之间是学习的关系，对于有如：分数的字段
 * 此对象时 将Teacher 和 Course 之间的多对多关系    >>>  拆分为 两个One2Many(一对多) 此类是Many端
 * @author Administrator
 *
 */
public class TeacherCourse {

	private int id;
	private Teacher teacher;
	private Course course;
	private int score;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
}
