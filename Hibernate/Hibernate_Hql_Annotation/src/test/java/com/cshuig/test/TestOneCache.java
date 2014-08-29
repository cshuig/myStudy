package com.cshuig.test;

import java.util.Iterator;
import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import org.hibernate.Session;
import org.junit.Test;

import com.cshuig.entity.Student;
import com.cshuig.util.HibernateUtil;
/**
 * 一级缓存生命周期：session已关闭，立马失效
 * @author Administrator
 *
 */


public class TestOneCache {

	@Test
	public void test01(){
		Session session = null;
		try {
			/**
			 * 此时发出多条sql，第二条由于配置了 @ManyToOne(fetch=FetchType.EAGER) ，会立马把学生对于的班级抓取出来
			 */
			session = HibernateUtil.openSession();
			List<Student> list = session.createQuery(" from Student").setFirstResult(0).setMaxResults(10).list();
			Iterator<Student> stus = list.iterator();
			while(stus.hasNext()){
				Student stu = stus.next();
				System.out.println(stu.getSname() +" --- " + stu.getSex());
			}
		} catch (Exception e) {
			if(session!=null) session.getTransaction().rollback();
		}finally{
			HibernateUtil.closeSession(session);
		}
	}
	
	@Test
	public void test02(){
		Session session = null;
		try {
			
			/**
			 * 此时使用iterate()，首先之后发出一条sql，获取到所有学生的ID，
			 * 之后每次遍历使用一个学生对象，就会发出一条sql去取学生信息
			 * 典型的  n+1 情况
			 * 
			 * 说明：存在iterator的原因是：有可能会在一个session中查询两次数据，
			 * 如果使用list每次都会把所有的对象查询出来，而使用iterator仅仅只会查询id
			 * 此时所有的对象已经存储在一级缓存(session的缓存)中，可以直接取出来
			 */
			session = HibernateUtil.openSession();
			Iterator<Student> stus = session.createQuery(" from Student").setFirstResult(0).setMaxResults(10).iterate();
			while(stus.hasNext()){
				Student stu = stus.next();
				System.out.println(stu.getSname() +" --- " + stu.getSex());
			}
		} catch (Exception e) {
			if(session!=null) session.getTransaction().rollback();
		}finally{
			HibernateUtil.closeSession(session);
		}
	}
	
	@Test
	public void test03(){
		Session session = null;
		try {
			
			/**
			 * 此时使用list()会发出多条sql
			 */
			session = HibernateUtil.openSession();
			List<Student> list = session.createQuery(" from Student").setFirstResult(0).setMaxResults(10).list();
			
			//先通过list()查询一次
			Iterator<Student> stus = list.iterator();
			while(stus.hasNext()){
				Student stu = stus.next();
				System.out.println(stu.getSname() +" --- " + stu.getSex());
			}
			
			//再通过iterate()查询一次
			stus = session.createQuery(" from Student").setFirstResult(0).setMaxResults(10).iterate();
			while(stus.hasNext()){
				Student stu = stus.next();
				System.out.println(stu.getSname() +" --- " + stu.getSex());
			}
		} catch (Exception e) {
			if(session!=null) session.getTransaction().rollback();
		}finally{
			HibernateUtil.closeSession(session);
		}
	}
	
	@Test
	public void test04(){
		Session session = null;
		try {
			
			/**
			 * 一级缓存：使用list()的情况下，获取到的对象被放进一级缓存(session的缓存)，当再次load()时候，就直接从一级缓存中获取
			 */
			session = HibernateUtil.openSession();
			List<Student> list = session.createQuery(" from Student").setFirstResult(0).setMaxResults(10).list();
			Iterator<Student> stus = list.iterator();
			while(stus.hasNext()){
				Student stu = stus.next();
				System.out.println(stu.getSname() +" --- " + stu.getSex());
			}
			
			Student student = (Student)session.load(Student.class, 1);
			System.out.println("---");
			System.out.println(student.getSname());
		} catch (Exception e) {
			if(session!=null) session.getTransaction().rollback();
		}finally{
			HibernateUtil.closeSession(session);
		}
	}
	
	@Test
	public void test05(){
		Session session = null;
		try {
			
			/**
			 * 一级缓存：使用iterate()时，由于是先获取到对象的ID，此时一级缓存中不存在相应的对象，所以再次load()对象时，会发出一条sql取对象
			 */
			session = HibernateUtil.openSession();
			Iterator<Student> stus = session.createQuery(" from Student").setFirstResult(0).setMaxResults(10).iterate();
			while(stus.hasNext()){
				Student stu = stus.next();
				System.out.println(stu.getSname() +" --- " + stu.getSex());
			}
			/**
			 * 此时直接从缓存中获取对象    session此时还未关闭
			 * 一级缓存生命周期：session一关闭，就立马失效
			 */
			Student student = (Student)session.load(Student.class, 1);
			System.out.println("---");
			System.out.println(student.getSname());
		} catch (Exception e) {
			if(session!=null) session.getTransaction().rollback();
		}finally{
			HibernateUtil.closeSession(session);
		}
	}
}
