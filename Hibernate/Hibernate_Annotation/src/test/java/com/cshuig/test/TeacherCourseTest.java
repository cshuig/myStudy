package com.cshuig.test;

import org.hibernate.Session;
import org.junit.Test;

import com.cshuig.entity.Course;
import com.cshuig.entity.Teacher;
import com.cshuig.entity.TeacherCourse;
import com.cshuig.util.HibernateUtil;

public class TeacherCourseTest {

	@Test
	public void testAdd01(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			//先插入One的一端
			Teacher teacher1 = new Teacher();
			teacher1.setTname("白岩松");
			session.save(teacher1);
			
			Teacher teacher2 = new Teacher();
			teacher2.setTname("易中天");
			session.save(teacher2);
			
			Course course2 = new Course();
			course2.setCname("刘备打刘邦");
			session.save(course2);
			
			Course course1 = new Course();
			course1.setCname("国家访谈录");
			session.save(course1);
			
			//插入中间表即多的一方
			TeacherCourse tcs1 = new TeacherCourse();
			tcs1.setTeacher(teacher1);
			tcs1.setCourse(course1);
			tcs1.setScore(100);
			session.save(tcs1);
			
			TeacherCourse tcs2 = new TeacherCourse();
			tcs2.setTeacher(teacher1);
			tcs2.setCourse(course2);
			tcs2.setScore(50);
			session.save(tcs2);
			
			
			TeacherCourse tcs3 = new TeacherCourse();
			tcs3.setTeacher(teacher2);
			tcs3.setCourse(course1);
			tcs3.setScore(50);
			session.save(tcs3);
			
			TeacherCourse tcs4 = new TeacherCourse();
			tcs4.setTeacher(teacher2);
			tcs4.setCourse(course2);
			tcs4.setScore(100);
			session.save(tcs4);
			
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			if(session!=null) session.getTransaction().rollback();
		}finally{
			HibernateUtil.closeSession(session);
		}
	}
	
	@Test
	public void testAdd02(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			if(session!=null) session.getTransaction().rollback();
		}finally{
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * 测试从One端 去获取 Many的数据
	 * 结果：发现出现大量的hql
	 */
	@Test
	public void testLoad01(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			//获取一个教师 对于的所有课程及分数
			Teacher teacher = (Teacher)session.load(Teacher.class, 1);
			System.out.println(teacher.getTname());
			//此时会发送一条sql，获取到 t_teacher_course对象
			for(TeacherCourse tcs: teacher.getTcs()){
				//此时每次都会根据c_id从t_course表获取记录对象，每次都会发送一条SQL
				System.out.println(tcs.getCourse().getCname());
			}
			//可以看见发送大量的hql，所以不建议这样使用，建议使用：sql
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			if(session!=null) session.getTransaction().rollback();
		}finally{
			HibernateUtil.closeSession(session);
		}
	}
	
	@Test
	public void testLoad02(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			TeacherCourse tc = (TeacherCourse)session.get(TeacherCourse.class, 1);
			System.out.println(tc.getTeacher().getTname());
			System.out.println(tc.getCourse().getCname());
			System.out.println(tc.getScore());
			
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			if(session!=null) session.getTransaction().rollback();
		}finally{
			HibernateUtil.closeSession(session);
		}
	}
	
	@Test
	public void testDelete01(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			if(session!=null) session.getTransaction().rollback();
		}finally{
			HibernateUtil.closeSession(session);
		}
	}
	
	@Test
	public void testDelete02(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			if(session!=null) session.getTransaction().rollback();
		}finally{
			HibernateUtil.closeSession(session);
		}
	}
}
