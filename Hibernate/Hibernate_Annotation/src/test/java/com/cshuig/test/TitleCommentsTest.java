package com.cshuig.test;

import org.hibernate.Session;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.junit.Test;

import com.cshuig.entity.Comments;
import com.cshuig.entity.Title;
import com.cshuig.util.HibernateUtil;

public class TitleCommentsTest {

	/**
	 * 测试One的一方，来维护关系
	 * 预期结果：多的一方的外键值为空
	 */
	@Test
	public void testAdd01(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			
			Comments comments1 = new Comments();
			Comments comments2 = new Comments();
			Comments comments3 = new Comments();
			comments1.setContent("好评");
			comments2.setContent("差评");
			comments3.setContent("不评");
			session.save(comments1);
			session.save(comments2);
			session.save(comments3);
			
			Title title	= new Title();
			title.setTname("关于xxx的通知");
			title.setTcontent("是非法十分丰富");
			title.addComments(comments1);
			title.addComments(comments2);
			title.addComments(comments3);
			
			//此时会发现，One端不维护关系  ----达到理想效果
			session.save(title);
			
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			if(session!=null) session.getTransaction().rollback();
		}finally{
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * 测试Many的一方，来维护关系
	 * 预期结果：多的一方的外键值不能为空
	 */
	@Test
	public void testAdd2(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			
			Title title	= new Title();
			title.setTname("关于xxx的通知");
			title.setTcontent("是非法十分丰富");
			session.save(title);
			
			Comments comments1 = new Comments();
			Comments comments2 = new Comments();
			Comments comments3 = new Comments();
			comments1.setContent("好评");
			comments1.setTitle(title);
			comments2.setContent("差评");
			comments2.setTitle(title);
			comments3.setContent("不评");
			comments3.setTitle(title);
			session.save(comments1);
			session.save(comments2);
			session.save(comments3);
			//此时发现，多的一方外键肯定有值，达到了理想的效果
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			if(session!=null) session.getTransaction().rollback();
		}finally{
			HibernateUtil.closeSession(session);
		}
	}
	
	@Test
	public void testLoad(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			Title title = (Title)session.load(Title.class, 2);
			System.out.println(title.getTname() +"，" +title.getTcontent());
			System.out.println(title.getComments().size());
			for(Comments c : title.getComments()){
				System.out.println("       "+c.getContent());
			}
			//此时会在发出一条sql,会发现是把全部数据查询出来，如果只想要记录数呢。，可以配置注解@LazyCollection(LazyCollectionOption.EXTRA)
			//System.out.println(title.getComments().size());
		} catch (Exception e) {
			e.printStackTrace();
			if(session!=null) session.getTransaction().rollback();
		}finally{
			HibernateUtil.closeSession(session);
		}
	}
	
	
	@Test
	public void testDelete(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			Title title = (Title)session.load(Title.class, 3);
			//一的这端维护外键，它首先是将Many的一方的外键值置为空：update t_comments set t_id=null where t_id=?
			//然后在删除One的记录
			session.delete(title);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			if(session!=null) session.getTransaction().rollback();
		}finally{
			HibernateUtil.closeSession(session);
		}
	}
}
