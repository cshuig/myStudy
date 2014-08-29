package com.cshuig.test;

import org.hibernate.Session;
import org.junit.Test;

import com.cshuig.entity.Classroom;
import com.cshuig.entity.Special;
import com.cshuig.entity.Student;
import com.cshuig.util.HibernateUtil;

public class TestAdd {

	
	@Test
	public void testAddSpecial(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			
			session.save(new Special("计算机网络"));	//id=1
			session.save(new Special("计算机应用"));	//id=2
			session.save(new Special("计算机信息管理"));	//id=3
			session.save(new Special("园艺园林"));		//id=4
			session.save(new Special("数据教育"));		//id=5
			session.save(new Special("物理教育"));		//id=6
			session.save(new Special("化学教育"));		//id=7
			session.save(new Special("会计学"));		//id=8
			session.save(new Special("英语教育"));		//id=9
			
			session.getTransaction().commit();
		} catch (Exception e) {
			if(session!=null) session.getTransaction().rollback();
		}finally{
			HibernateUtil.closeSession(session);
		}
	}
	
	@Test
	public void testAddClassroom(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			
			session.save(new Classroom("网络1",2009,new Special(1)));		//id=1
			session.save(new Classroom("网络2",2009,new Special(1)));		//id=2
			session.save(new Classroom("网络3",2009,new Special(1)));		//id=3
			session.save(new Classroom("网络4",2009,new Special(1)));		//id=4
			
			
			session.save(new Classroom("应用1",2009,new Special(2)));		//id=5
			session.save(new Classroom("应用2",2009,new Special(2)));		//id=6
			
			session.save(new Classroom("信息管理1",2009,new Special(3)));	//id=7
			session.save(new Classroom("信息管理2",2009,new Special(3)));	//id=8
			session.save(new Classroom("信息管理3",2009,new Special(3)));	//id=9
			session.save(new Classroom("信息管理4",2009,new Special(3)));	//id=10
			session.save(new Classroom("信息管理5",2009,new Special(3)));	//id=11
			session.save(new Classroom("信息管理6",2009,new Special(3)));	//id=12
			
			session.save(new Classroom("园林1",2009,new Special(4)));		//id=13
			session.save(new Classroom("园林2",2009,new Special(4)));		//id=14
			session.save(new Classroom("园林3",2009,new Special(4)));		//id=15
			
			session.save(new Classroom("数据1",2009,new Special(5)));		//id=16
			session.save(new Classroom("数据2",2009,new Special(5)));		//id=17
			session.save(new Classroom("数据3",2009,new Special(5)));		//id=18
			
			session.save(new Classroom("物理1",2009,new Special(6)));		//id=19
			session.save(new Classroom("物理2",2009,new Special(6)));		//id=20
			session.save(new Classroom("物理3",2009,new Special(6)));		//id=21
			
			session.save(new Classroom("化学1",2009,new Special(7)));		//id=22
			session.save(new Classroom("化学2",2009,new Special(7)));		//id=23
			session.save(new Classroom("化学3",2009,new Special(7)));		//id=24
			
			session.save(new Classroom("会计1",2009,new Special(8)));		//id=25
			session.save(new Classroom("会计2",2009,new Special(8)));		//id=26
			session.save(new Classroom("会计3",2009,new Special(8)));		//id=27
			
			session.save(new Classroom("英语1",2009,new Special(9)));		//id=28
			session.save(new Classroom("英语2",2009,new Special(9)));		//id=29
			session.save(new Classroom("英语3",2009,new Special(9)));		//id=30
			
			
			session.getTransaction().commit();
		} catch (Exception e) {
			if(session!=null) session.getTransaction().rollback();
		}finally{
			HibernateUtil.closeSession(session);
		}
	}
	
	@Test
	public void testAddStudent(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			
			for(int i=1;i<=10;i++){
				session.save(new Student("张飞"+i,"男",new Classroom(1)));
			}
			
			for(int i=1;i<=10;i++){
				session.save(new Student("关羽"+i,"男",new Classroom(2)));
			}
			
			for(int i=1;i<=10;i++){
				session.save(new Student("赵云"+i,"男",new Classroom(3)));
			}
			
			for(int i=1;i<=10;i++){
				session.save(new Student("马超"+i,"男",new Classroom(4)));
			}
			
			for(int i=1;i<=10;i++){
				session.save(new Student("诸葛亮"+i,"男",new Classroom(5)));
			}
			
			for(int i=1;i<=10;i++){
				session.save(new Student("刘备"+i,"男",new Classroom(6)));
			}
			
			for(int i=1;i<=10;i++){
				session.save(new Student("黄忠"+i,"男",new Classroom(7)));
			}
			
			for(int i=1;i<=10;i++){
				session.save(new Student("貂蝉"+i,"女",new Classroom(8)));
			}
			
			for(int i=1;i<=10;i++){
				session.save(new Student("张辽"+i,"男",new Classroom(9)));
			}
			
			for(int i=1;i<=10;i++){
				session.save(new Student("司马懿"+i,"男",new Classroom(10)));
			}
			
			for(int i=1;i<=10;i++){
				session.save(new Student("曹操"+i,"男",new Classroom(11)));
			}
			
			for(int i=1;i<=10;i++){
				session.save(new Student("吕布"+i,"男",new Classroom(12)));
			}
			
			for(int i=1;i<=10;i++){
				session.save(new Student("夏侯渊"+i,"男",new Classroom(13)));
			}
			
			for(int i=1;i<=10;i++){
				session.save(new Student("夏侯淳"+i,"男",new Classroom(14)));
			}
			
			for(int i=1;i<=10;i++){
				session.save(new Student("徐晃"+i,"男",new Classroom(15)));
			}
			
			for(int i=1;i<=10;i++){
				session.save(new Student("许诸"+i,"男",new Classroom(16)));
			}
			
			for(int i=1;i<=10;i++){
				session.save(new Student("鲁肃"+i,"男",new Classroom(17)));
			}
			
			for(int i=1;i<=10;i++){
				session.save(new Student("左慈"+i,"男",new Classroom(18)));
			}
			
			for(int i=1;i<=10;i++){
				session.save(new Student("庞统"+i,"男",new Classroom(19)));
			}
			
			for(int i=1;i<=10;i++){
				session.save(new Student("魏延"+i,"男",new Classroom(20)));
			}
			
			for(int i=1;i<=10;i++){
				session.save(new Student("关兴"+i,"男",new Classroom(21)));
			}
			
			for(int i=1;i<=10;i++){
				session.save(new Student("孙权"+i,"男",new Classroom(22)));
			}
			
			for(int i=1;i<=10;i++){
				session.save(new Student("文丑"+i,"男",new Classroom(23)));
			}
			
			for(int i=1;i<=10;i++){
				session.save(new Student("甘宁"+i,"男",new Classroom(24)));
			}
			
			for(int i=1;i<=10;i++){
				session.save(new Student("董允"+i,"男",new Classroom(25)));
			}
			
			for(int i=1;i<=10;i++){
				session.save(new Student("法正"+i,"男",new Classroom(26)));
			}
			
			for(int i=1;i<=10;i++){
				session.save(new Student("姜维"+i,"男",new Classroom(27)));
			}
			
			for(int i=1;i<=10;i++){
				session.save(new Student("马良"+i,"男",new Classroom(28)));
			}
			
			for(int i=1;i<=10;i++){
				session.save(new Student("孙乾"+i,"男",new Classroom(29)));
			}
			
			for(int i=1;i<=10;i++){
				session.save(new Student("糜竺"+i,"男",new Classroom(30)));
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			if(session!=null) session.getTransaction().rollback();
		}finally{
			HibernateUtil.closeSession(session);
		}
	}
}
