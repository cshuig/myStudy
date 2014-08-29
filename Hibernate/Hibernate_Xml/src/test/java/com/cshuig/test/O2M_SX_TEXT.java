package com.cshuig.test;

import org.hibernate.Session;
import org.junit.Test;

import com.cshuig.entity.Role;
import com.cshuig.entity.User;
import com.cshuig.util.HibernateUtil;

/**
 * 一对多双向
 * @author Administrator
 *
 */
public class O2M_SX_TEXT {

	/**
	 * 先添加 Many 一端
	 * 在添加 One  一端
	 */
	@Test
	public void testAdd01(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			
			User user1 = new User();
			user1.setName("八戒");
			user1.setAge(100);
			session.save(user1);
			
			User user2 = new User();
			user2.setName("九戒");
			user2.setAge(100);
			session.save(user2);
			
			Role role = new Role();
			role.setRname("管理员");
			role.addUsers(user1);
			role.addUsers(user2);
			session.save(role);
			//此时也会发出 5条sql，所以最近实践：不要在one的一方维护关系,
			//解决办法：可以用 inver=true，来指定，不在自己这端维护关系，这个时候会发现只有insert，没有update了，因为不自己不维护关系了
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			if(session!=null) session.getTransaction().rollback();
		}finally{
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * 先添加 One 一端
	 * 在添加  Many 一端
	 */
	@Test
	public void testAdd02(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			Role role = new Role();
			role.setRname("管理员");
			session.save(role);
			
			User user1 = new User();
			user1.setName("1八戒");
			user1.setAge(100);
			user1.setRole(role);
			session.save(user1);
			
			User user2 = new User();
			user2.setName("1九戒");
			user2.setAge(100);
			user2.setRole(role);
			session.save(user2);
			
			role.addUsers(user1);
			role.addUsers(user2);
			
			//此时也会发出 5条sql，所以最近实践：不要在one的一方维护关系,
			//解决办法：可以用 inver=true，来指定，不在自己这端维护关系，这个时候会发现只有insert，没有update了，因为不自己不维护关系了
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			if(session!=null) session.getTransaction().rollback();
		}finally{
			HibernateUtil.closeSession(session);
		}
	}
}
