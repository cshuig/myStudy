package com.cshuig.test;

import org.hibernate.Session;
import org.junit.Test;

import com.cshuig.entity.Role;
import com.cshuig.entity.User;
import com.cshuig.util.HibernateUtil;

public class UserRoleTest {

	@Test
	public void testAdd01(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			Role role = new Role();
			role.setRname("管理员");
			session.save(role);
			
			User user = new User();
			user.setAge(100);
			user.setName("张三");
			user.setRole(role);
			session.save(user);
			
			
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 测试级联操作cascade=CascadeType.ALL
	 * 看看One的一端在状态为：瞬时的情况下，会不会自动写数据库
	 */
	@Test
	public void testAdd02(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			Role role = new Role();
			role.setRname("管理员2");
			//session.save(role);
			
			User user = new User();
			user.setAge(100);
			user.setName("张三2");
			
			//此时由于role对象时瞬时的状态，在配置了级联操作后cascade=CascadeType.ALL，会自动创建这个对象并插入数据库
			user.setRole(role);
			session.save(user);
			
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 先添加 Many 一端
	 * 在添加 One  一端
	 */
	@Test
	public void testAdd03(){
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
	public void testAdd04(){
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
	
	/**
	 * 测试：从非关系维护端(One端)，获取数据的sql情况
	 */
	@Test
	public void testLoad01(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			
			//由于此时role不做关系维护端，所有再去取users的时候，会发出一条sql
			Role role = (Role)session.load(Role.class, 1);
			System.out.println(role.getRname());
			for(User user : role.getUsers()){
				System.out.println(user.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 测试：从关系维护端(Many端)，获取数据的sql情况
	 */
	@Test
	public void testLoad02(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			
			/* 此时会发现，延迟加载失效，只发出一条sql， 
			*  原因是在@ManyToOne注解的属性中，没有配置 fetch的值
			*  添加后：延迟加载就可以用了
			*/
			User user = (User)session.load(User.class, 1);
			System.out.println(user.getName());
			System.out.println(user.getRole().getRname());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
