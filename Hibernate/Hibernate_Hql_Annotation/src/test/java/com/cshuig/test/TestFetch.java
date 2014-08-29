package com.cshuig.test;

import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.hibernate.Session;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.junit.Test;

import com.cshuig.entity.Classroom;
import com.cshuig.entity.Student;
import com.cshuig.util.HibernateUtil;

/**
 * 测试：抓取策略
 * @author Administrator
 */
@SuppressWarnings("unchecked")
public class TestFetch {

	@Test
	public void test01(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			/**
			 * 1、测试：fetch=FetchType.EAGER 默认值  	--立马取出所有关联的对象
			 * 测试结果：注解的方式下，默认是非延迟加载，而且只会发出一条sql取出所有的对象
			 * 
			 * 2、1、测试：fetch=FetchType.LAZY		--延迟取出所有关联的对象
			 * 测试结果：注解的方式下，会再发出多条sql来获取关联对象
			 */
			Student stu = (Student) session.load(Student.class, 1);
			System.out.println(stu.getSname() +" --- "+stu.getClassroom().getCname());
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
			/**
			 * 1、测试：fetch=FetchType.EAGER   --立马取出所有关联的对象
			 * 测试结果：会立马发出多条独立的sql，将关联的对象全部取出
			 * 
			 * 2、测试：fetch=FetchType.LAZY	 --延迟取出所有关联的对象
			 * 测试结果：不使用关联对象时，是不会立马发出sql，但是在使用关联对象时，就会发出多条sql
			 */
			List<Student> list = session.createQuery(" from Student").list();
			for(Student stu : list){
				System.out.println(stu.getSname());
			}
		} catch (Exception e) {
			if(session!=null) session.getTransaction().rollback();
		}finally{
			HibernateUtil.closeSession(session);
		}
	}
	
	@Test
	public void test03(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			/**
			 * 测试：在hql中使用 fetch 进行关联对象的抓取
			 * 测试结果：之后发出一条sql了，而且数据全部取出来
			 * 
			 * 特别注意：在hql中使用 fetch 后，，不能使用 count(*)了
			 * 在分页的时候由需要使用count(*)时，可以将 fetch替换为空即可
			 */
			List<Student> list = session.createQuery(" select stu from Student stu join fetch stu.classroom cla join fetch cla.special").list();
			for(Student stu : list){
				System.out.println(stu.getSname() + stu.getClassroom().getCname() + stu.getClassroom().getSpecial().getSname());
			}
		} catch (Exception e) {
			if(session!=null) session.getTransaction().rollback();
		}finally{
			HibernateUtil.closeSession(session);
		}
	}
	
	
/** 针对one端的抓取---------------------------------------------  */
	
	@Test
	public void test04(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			/**
			 * one端:@Fetch(FetchMode.SUBSELECT)
			 * 此时会发现：只会发出一条sql
			 */
			Classroom classroom = (Classroom)session.load(Classroom.class, 1);
			System.out.println(classroom.getCname());
			for(Student stu : classroom.getStus()){
				System.out.println(stu.getSname() +"-- " +stu.getSex());
			}
		} catch (Exception e) {
			if(session!=null) session.getTransaction().rollback();
		}finally{
			HibernateUtil.closeSession(session);
		}
	}
	
	@Test
	public void test05(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			/**
			 * 此时会发现，对于通过hql来获取班级对象并且获取相应的学生列表时，会发出大量的sql，fetch="join"会失效
			 * 解决办法：使用@Fetch(FetchMode.SUBSELECT)
			 * 		此时会只有一条 获取所有班级的所有学生  的 sql
			 * select xxx 
			 * 	where stus0_.c_id in (select classroom0_.id from t_classroom classroom0_)
			 */
			List<Classroom> list = session.createQuery("from Classroom cla").list();
			for(Classroom cla : list){
				System.out.println(cla.getCname());
				for(Student stu : cla.getStus()){
					System.out.println(stu.getSname() +" -- " +stu.getSex());
				}
			}
		} catch (Exception e) {
			if(session!=null) session.getTransaction().rollback();
		}finally{
			HibernateUtil.closeSession(session);
		}
	}
}
