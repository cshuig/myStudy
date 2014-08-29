package com.cshuig.test;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.junit.Test;

import com.cshuig.entity.Student;
import com.cshuig.util.HibernateUtil;
/**
 * 二级缓存测试
 * 二级缓存：在session关闭后，还是有效的
 * 二级缓存是：基于对象的缓存，一定要注意
 * @author Administrator
 *
 */
public class TestSecondCache {

	/**
	 * 第一个session：测试load()一个对象，是否会被缓存到二级缓存中
	 * 第二个session：测试二级缓存是否有第一个session中缓存的对象
	 */
	@Test
	public void test01(){
		Session session = null;
		try {
			
			/**
			 * 此时使用list()只会发出一条sql
			 */
			session = HibernateUtil.openSession();
			Student student = (Student) session.load(Student.class, 1);
			System.out.println(student.getSname() +" --- " + student.getSex());
		} catch (Exception e) {
			if(session!=null) session.getTransaction().rollback();
		}finally{
			HibernateUtil.closeSession(session);
		}
		
		try {
			
			/**
			 * 此时Student已经在二级缓存中，所以不会发出sql了 
			 */
			session = HibernateUtil.openSession();
			Student student = (Student) session.load(Student.class, 1);
			System.out.println(student.getSname() +" --- " + student.getSex());
		} catch (Exception e) {
			if(session!=null) session.getTransaction().rollback();
		}finally{
			HibernateUtil.closeSession(session);
		}
	}
	/**
	 * 第一个session：查询出一个对象列表，同时缓存进二级缓存
	 * 第二个session：
	 * 				1、load()是否有刚刚缓存的对象
	 * 				2、重新发出一个同样的hql对象查询列表，会发现：不会使用二级缓存，而是会再次发出sql
	 */
	@Test
	public void test02(){
		Session session = null;
		try {
			
			/**
			 * 此时Student已经在二级缓存中，所以不会发出sql了 
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
		
		try {
			/**
			 * 此时Student已经在二级缓存中，所以不会发出sql了 
			 */
			session = HibernateUtil.openSession();
			
			/**
			 * 由于二级缓存 缓存的是对象，所以再次查询列表是无法从二级缓存中获取，只能是 load()才会查二级缓存
			 */
			/*List<Student> list = session.createQuery(" from Student").setFirstResult(0).setMaxResults(10).list();
			Iterator<Student> stus = list.iterator();
			while(stus.hasNext()){
				Student stu = stus.next();
				System.out.println(stu.getSname() +" --- " + stu.getSex());
			}*/
			Student student = (Student) session.load(Student.class, 1);
			System.out.println(student.getSname() +" --- " + student.getSex());
		} catch (Exception e) {
			if(session!=null) session.getTransaction().rollback();
		}finally{
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * 测试二级缓存是否能缓存非 对象 的数据
	 * 测试结果：二级缓存只会缓存对象，对于散列的数据，是不会缓存的
	 */
	@Test
	public void test03(){
		Session session = null;
		try {
			
			session = HibernateUtil.openSession();
			/**
			 * 由于二级缓存 是基于对象的缓存，所以，下面查询结果不会被缓存到二级缓存中，当再次load()时，还是会发出sql去数据库查询
			 */
			List<Object[]> list = session.createQuery(" select stu.id,stu.sname,stu.sex from Student stu").setFirstResult(0).setMaxResults(10).list();
			Iterator<Object[]> stus = list.iterator();
			while(stus.hasNext()){
				Object[] obj = stus.next();
				System.out.println(obj[0] +" --- " + obj[1]);
			}
		} catch (Exception e) {
			if(session!=null) session.getTransaction().rollback();
		}finally{
			HibernateUtil.closeSession(session);
		}
		
		try {
			/**
			 * 此时Student已经在二级缓存中，所以不会发出sql了 
			 */
			session = HibernateUtil.openSession();
			Student student = (Student) session.load(Student.class, 1);
			System.out.println(student.getSname() +" --- " + student.getSex());
		} catch (Exception e) {
			if(session!=null) session.getTransaction().rollback();
		}finally{
			HibernateUtil.closeSession(session);
		}
	}
	
	
	/**
	 * 第一个session中：使用list()将学生对象全部缓存起来
	 * 第二session中，使用iterate()只查询学生的ID，之后在根据id获取对象时，
	 * 		先是通过二级缓存中获取，如果有则不会发出sql，这样就解决了N+1的问题，
	 * 		如果发现二级缓存中无数据，则在发送sql，并缓存相应的对象到二级缓存中
	 * 
	 * 所以iterate()存在的意义是：配合二级缓存一起使用
	 */
	@Test
	public void test04(){
		Session session = null;
		try {
			
			/**
			 * 此时Student已经在二级缓存中，所以不会发出sql了 
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
		
		try {
			/**
			 * 此时Student已经在二级缓存中，所以不会发出sql了 
			 */
			session = HibernateUtil.openSession();
			
			/**
			 * 由于二级缓存 缓存的是对象，所以再次查询列表是无法从二级缓存中获取，只能是 load()才会查二级缓存
			 */
			Iterator<Student> stus = session.createQuery(" from Student").setFirstResult(0).setMaxResults(11).iterate();
			while(stus.hasNext()){
				Student stu = stus.next();
				System.out.println(stu.getSname() +" --- " + stu.getSex());
			}
			Student student = (Student) session.load(Student.class, 1);
			System.out.println(student.getSname() +" --- " + student.getSex());
		} catch (Exception e) {
			if(session!=null) session.getTransaction().rollback();
		}finally{
			HibernateUtil.closeSession(session);
		}
	}
	
	
}
