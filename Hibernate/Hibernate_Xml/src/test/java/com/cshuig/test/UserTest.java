package com.cshuig.test;

import org.hibernate.Session;
import org.junit.Test;

import com.cshuig.entity.User;
import com.cshuig.util.HibernateUtil;

public class UserTest {

	@Test
	public void testAdd(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			User user = new User();
			user.setAge(100);
			user.setName("张三");
			session.save(user);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
