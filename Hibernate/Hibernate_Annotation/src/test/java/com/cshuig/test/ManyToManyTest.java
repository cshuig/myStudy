package com.cshuig.test;

import org.hibernate.Session;
import org.junit.Test;

import com.cshuig.entity.Author;
import com.cshuig.entity.Book;
import com.cshuig.util.HibernateUtil;

/**
 * 测试 多对多的
 * @author Administrator
 *
 */
public class ManyToManyTest {

	/*
	 * 测试：由 Author端(非维护端)维护关系
	 * 预期结果：中间表：为空
	 */
	@Test
	public void testAdd01(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			Book book1 = new Book();
			book1.setBname("spring in action");
			session.save(book1);
			Book book2 = new Book();
			book2.setBname("mongoDB in action");
			session.save(book2);
			
			Author author1 = new Author();
			author1.setAname("张飞");
			author1.addBook(book1);
			author1.addBook(book2);
			
			session.save(author1);
			
			Author author2 = new Author();
			author2.setAname("李逵");
			author2.addBook(book1);
			author2.addBook(book2);
			session.save(author2);
			
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			if(session!=null) session.getTransaction().rollback();
		}finally{
			HibernateUtil.closeSession(session);
		}
	}
	
	/*
	 * 测试：由 Book端维护关系
	 * 预期结果：中间变不为空
	 */
	@Test
	public void testAdd02(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			Author author1 = new Author();
			author1.setAname("唐僧");
			session.save(author1);
			
			Author author2 = new Author();
			author2.setAname("八戒");
			session.save(author2);
			
			Book book1 = new Book();
			book1.setBname("Object-C 基础教程");
			book1.addBook(author1);
			book1.addBook(author2);
			session.save(book1);
			
			Book book2 = new Book();
			book2.setBname("Object-C 深入教程");
			book2.addBook(author1);
			book2.addBook(author2);
			session.save(book2);
			
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			if(session!=null) session.getTransaction().rollback();
		}finally{
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * 查询测试
	 */
	@Test
	public void testLoad01(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			Author author = (Author)session.load(Author.class, 3);
			System.out.println(author.getAname());
			System.out.println(author.getBooks().size());
			
			//此时中间表会自动 inner join book表 来获取 book的数据， 》》》  只有1条sql
			for(Book book : author.getBooks()){
				System.out.println("       "+book.getBname());
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(session!=null) session.getTransaction().rollback();
		}finally{
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * 查询测试
	 */
	@Test
	public void testLoad02(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			Book book = (Book)session.load(Book.class, 3);
			System.out.println(book.getBname());
			System.out.println(book.getAuthors().size());
			
			//此时中间表会自动 inner join book表 来获取 book的数据， 》》》  只有1条sql
			for(Author author : book.getAuthors()){
				System.out.println("       "+author.getAname());
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(session!=null) session.getTransaction().rollback();
		}finally{
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * 查询测试
	 */
	@Test
	public void testDelete01(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			Author author = (Author)session.load(Author.class, 1);
			
			/**
			 * 步骤：先删除中间表的记录，在删除本身
			 */
			session.delete(author);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			if(session!=null) session.getTransaction().rollback();
		}finally{
			HibernateUtil.closeSession(session);
		}
	}
}
