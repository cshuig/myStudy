package com.cshuig.test;

import org.hibernate.Session;
import org.junit.Test;

import com.cshuig.entity.ClassRoom;
import com.cshuig.entity.Student;
import com.cshuig.util.HibernateUtil;

public class ManyToOneTest {

	@Test
	public void testAdd01(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			//先添加ManyToOne 中的   One的一方：
			ClassRoom classRoom = new ClassRoom();
			classRoom.setName("计算机信息管理");
			classRoom.setGrade(2009);
			session.save(classRoom);
			
			Student student = new Student();
			student.setName("张三");
			student.setSno(01);
			student.setClassRoom(classRoom);
			session.save(student);
			
			Student student2 = new Student();
			student2.setName("李四");
			student2.setSno(02);
			student2.setClassRoom(classRoom);
			session.save(student2);
			
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
		try{
			session = HibernateUtil.openSession();
			session.beginTransaction();
			Student student1 = new Student();
			student1.setName("武松");
			student1.setSno(01);
			session.save(student1);
			
			Student student2 = new Student();
			student2.setName("鲁智深");
			student2.setSno(02);
			session.save(student2);
			
			ClassRoom classRoom = new ClassRoom();
			classRoom.setName("水浒英雄班");
			classRoom.setGrade(1567);
			session.save(classRoom);
			
			student1.setClassRoom(classRoom);
			student2.setClassRoom(classRoom);
			session.getTransaction().commit();
			
			
		}catch(Exception e){
			e.printStackTrace();
			if(session!=null) session.getTransaction().rollback();
		}finally{
			HibernateUtil.closeSession(session);
		}
	}
	
	@Test
	public void testAdd03(){
		Session session = null;
		try{
			session = HibernateUtil.openSession();
			session.beginTransaction();
			Student student1 = new Student();
			student1.setName("武松");
			student1.setSno(01);
			session.save(student1);
			
			Student student2 = new Student();
			student2.setName("鲁智深");
			student2.setSno(02);
			session.save(student2);
			
			ClassRoom classRoom = new ClassRoom();
			classRoom.setName("水浒英雄班");
			classRoom.setGrade(1567);
			//session.save(classRoom);
			
			/**
			 * 此时由于classRoom对象是一个 瞬时状态，关联后会报错
			 * 解决办法是添加：cascade属性，表明会自动关联对象，如果没有关联对象，会自动创建一个对象
			 */
			student1.setClassRoom(classRoom);
			student2.setClassRoom(classRoom);
			session.getTransaction().commit();
			
			
		}catch(Exception e){
			e.printStackTrace();
			if(session!=null) session.getTransaction().rollback();
		}finally{
			HibernateUtil.closeSession(session);
		}
	}
	
	@Test
	public void testLoad01(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			Student stu = (Student)session.load(Student.class, 1);
			System.out.println(stu.getName());
			//此时会延迟加载, 再次发生一条sql
			System.out.println(stu.getClassRoom().getName());
		} catch (Exception e) {
			e.printStackTrace();
			if(session!=null) session.getTransaction().rollback();
		}finally{
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * 测试cascade = all的情况是否符合预期
	 * 只要删除多的一方，或者one的一方，都会自动的去删除对方的所有对象，如果发现一的id，在多的一方还有依赖，则会报错
	 */
	@Test
	public void testDelete01(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			Student stu1 = (Student)session.load(Student.class, 3);
			session.delete(stu1);
			
			/*Student stu2 = (Student)session.load(Student.class, 2);
			session.delete(stu2);
			
			ClassRoom classRoom = (ClassRoom)session.load(ClassRoom.class, 1);
			session.delete(classRoom);*/
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			if(session!=null) session.getTransaction().rollback();
		}finally{
			HibernateUtil.closeSession(session);
		}
	}
}
