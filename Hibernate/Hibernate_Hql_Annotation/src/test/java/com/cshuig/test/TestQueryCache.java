package com.cshuig.test;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.junit.Test;

import com.cshuig.entity.Student;
import com.cshuig.util.HibernateUtil;
/**
 * 查询缓存测试
 * @author Administrator
 *
 */
public class TestQueryCache {

	/**
	 * 第一个session：查询出一个对象列表，同时缓存进二级缓存
	 * 第二个session：
	 * 				1、load()是否有刚刚缓存的对象
	 * 				2、重新发出一个同样的hql对象查询列表，会发现：不会使用二级缓存，而是会再次发出sql
	 */
	@Test
	public void test01(){
		Session session = null;
		try {
			
			/**
			 * 使用：.setCacheable(true) 就可以把当前的 hql 缓存起来
			 */
			session = HibernateUtil.openSession();
			List<Student> list = session.createQuery(" from Student")
										.setCacheable(true)
										.setFirstResult(0)
										.setMaxResults(10)
										.list();
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
			session = HibernateUtil.openSession();
			
			/**
			 * 此时 由于 hql 已经被缓存起来，所以检索到相同的hql后，就不会往数据库发送，而是使用旧的
			 * 
			 */
			List<Student> list = session.createQuery(" from Student")
										.setCacheable(true) //开启查询缓存，查询缓存属于：SessionFactory级别
										.setFirstResult(0)
										.setMaxResults(10)
										.list();
			Iterator<Student> stus = list.iterator();
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
	
	
	/**
	 * 查询缓存存在的第一个问题：
	 * 	只有HQL的语句不一样，就不会开启查询缓存，查询缓存村的是HQL语句，只有两个HQL完全一致(并且参数都要一样),才能使用查询缓存
	 */
	@Test
	public void test02(){
		Session session = null;
		try {
			/**
			 * 使用：.setCacheable(true) 就可以把当前的 hql 缓存起来
			 */
			session = HibernateUtil.openSession();
			List<Student> list = session.createQuery(" from Student where sname like ?")
										.setParameter(0, "%张飞%")
										.setCacheable(true)
										.setFirstResult(0)
										.setMaxResults(300)
										.list();
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
			session = HibernateUtil.openSession();
			
			/**
			 * 此时 由于 hql 已经被缓存起来，所以检索到相同的hql后，就不会往数据库发送，而是使用旧的
			 * 
			 */
			List<Student> list = session.createQuery(" from Student where sname like ?")
										.setParameter(0, "%貂蝉%")
										.setCacheable(true)
										.setFirstResult(0)
										.setMaxResults(300)
										.list();
			Iterator<Student> stus = list.iterator();
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
	
	
	/**
	 * 查询缓存存在的第二个问题：
	 * 首先要关闭对象的二级缓存
	 * 	此时会发现，还是会发出大量的sql， 
	 * 总结：查询缓存必须和二级缓存一起使用
	 */
	@Test
	public void test03(){
		Session session = null;
		try {
			/**
			 * 此时
			 */
			session = HibernateUtil.openSession();
			List<Student> list = session.createQuery(" from Student where sname like ?")
										.setParameter(0, "%张飞%")
										.setCacheable(true)
										.setFirstResult(0)
										.setMaxResults(300)
										.list();
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
			session = HibernateUtil.openSession();
			
			/**
			 * 此时 由于 hql 已经被缓存起来，所以检索到相同的hql后，就不会往数据库发送，而是使用旧的
			 * 
			 */
			List<Student> list = session.createQuery(" from Student where sname like ?")
										.setParameter(0, "%貂蝉%")
										.setCacheable(true)
										.setFirstResult(0)
										.setMaxResults(300)
										.list();
			Iterator<Student> stus = list.iterator();
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
