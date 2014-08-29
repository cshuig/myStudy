package com.cshuig.test;

import org.hibernate.Session;
import org.junit.Test;

import com.cshuig.entity.Classroom;
import com.cshuig.entity.Student;
import com.cshuig.util.HibernateUtil;

public class TesHql {

	@Test
	public void test01(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			/*Classroom clas = new Classroom();
			clas.setCname("园林技术0906");
			clas.setGrade(2009);
			
			Student stus = new Student();
			stus.setSname("貂蝉");
			stus.setSex("女");
			stus.setClassroom(clas);
			session.save(stus);*/
			
			session.getTransaction().commit();;
		} catch (Exception e) {
			if(session!=null) session.getTransaction().rollback();
		}finally{
			HibernateUtil.closeSession(session);
		}
	}
}
