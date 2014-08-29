package com.cshuig.test;

import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.junit.Test;

import com.cshuig.entity.Student;
import com.cshuig.util.HibernateUtil;

/**
 * 测试数据库的并发问题
 * @author Administrator
 */
public class TestLock {

	/**
	 * 测试步骤： 
	 * 同步debug断点 test01()  test02()，之后两个方法都load()到对象后，
	 * 先提交test01()，数据库查看，名字修改成功：凤飞飞
	 * 接着提交test02()，数据库查看，发现名字变成了原来的。
	 */
	@Test
	public void test01(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			/**
			 * 只要使用这种方式来load，就会为其增加悲观锁
			 */
			Student student = (Student)session.get(Student.class, 1, LockOptions.UPGRADE);
			student.setSname("凤飞飞");
			session.getTransaction().commit();
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
			session = HibernateUtil.openSession();
			session.beginTransaction();
			Student student = (Student)session.get(Student.class, 1, LockOptions.UPGRADE);
			student.setSex("女");
			session.getTransaction().commit();
		} catch (Exception e) {
			if(session!=null) session.getTransaction().rollback();
		}finally{
			HibernateUtil.closeSession(session);
		}
	}
	
	
	
}
