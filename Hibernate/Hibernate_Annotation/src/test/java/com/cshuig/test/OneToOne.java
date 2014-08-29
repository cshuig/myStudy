package com.cshuig.test;

import org.hibernate.Session;
import org.junit.Test;

import com.cshuig.entity.IdCard;
import com.cshuig.entity.Persion;
import com.cshuig.util.HibernateUtil;

public class OneToOne {

	//测试one2one :单向
	@Test
	public void testAdd01(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			IdCard idCard = new IdCard();
			idCard.setNo("1001");
			session.save(idCard);
			
			Persion persion = new Persion();
			persion.setIdCard(idCard);
			persion.setPname("事实上事实上");
			session.save(persion);
			
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			if(session!=null) session.getTransaction().rollback();
		}finally{
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * 测试one2one：双方不唯一是否正确
	 */
	@Test
	public void testAdd02(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			
			IdCard idCard = (IdCard)
			session.load(IdCard.class, 1);
			
			Persion persion = new Persion();
			persion.setIdCard(idCard);
			persion.setPname("色东方给偶加");
			session.save(persion);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			if(session!=null) session.getTransaction().rollback();
		}finally{
			HibernateUtil.closeSession(session);
		}
	}
	
	
	/**
	 * 测试使用IdCard来维护关系,IdCard属于非关系维护端
	 */
	@Test
	public void testAdd03(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			
			/**
			 * 此时，由于使用的是Persion来维护关系(外键在哪一端，就由那一端来维护)
			 */
			Persion persion = new Persion();
			persion.setPname("测试非关系维护端是否能维护关系");
			session.save(persion);
			
			IdCard idCard = new IdCard();
			idCard.setNo("1002");
			idCard.setPersion(persion);
			//由于Persion才是关系维护者，所以此时会发现，关系不会更新及t_persin表中的card_id自动为空
			session.save(idCard);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			if(session!=null) session.getTransaction().rollback();
		}finally{
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * 双向：此时使用persion来维护关系
	 */
	@Test
	public void testAdd04(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			
			Persion persion = new Persion();
			persion.setPname("杜兰特");
			session.save(persion);
			
			IdCard idCard = new IdCard();
			idCard.setNo("222222");
			idCard.setPersion(persion);
			session.save(idCard);
			session.getTransaction().commit();
		} catch (Exception e) {
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
			
			/**
			 * 特别注意：如果没有使用双向，此时会发出2条sql，一条取Persion，一条延迟加载取IdCard
			 */
			Persion persion = (Persion)session.load(Persion.class, 1);
			System.out.println(persion.getPname() + " , " + persion.getIdCard().getNo());
			
			//此时会发现，不会有sql了，
			IdCard idCard = (IdCard)session.load(IdCard.class, 1);
			System.out.println(idCard.getNo() + ",  " +idCard.getPersion().getPname());
		} catch (Exception e) {
			e.printStackTrace();
			if(session!=null) session.getTransaction().rollback();
		}finally{
			HibernateUtil.closeSession(session);
		}
	}
	
	@Test
	public void testLoad02(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			Persion persion = (Persion)session.load(Persion.class, 4);
			//此时会发现，有3条sql
			IdCard idCard = (IdCard)session.load(IdCard.class, 4);
			//只要取非关系维护端的记录，同时也会取出这个persion的idCard，这里不使用join来取出来，所以会发出2条sql
			System.out.println(idCard.getNo() + ",  " +idCard.getPersion().getPname());
			
			
		} catch (Exception e) {
			e.printStackTrace();
			if(session!=null) session.getTransaction().rollback();
		}finally{
			HibernateUtil.closeSession(session);
		}
	}
}
