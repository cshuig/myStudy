package com.cshuig.test;

import java.util.List;

import com.cshuig.entity.Classroom;
import com.cshuig.entity.Student;
import org.hibernate.Session;
import org.junit.Test;

import com.cshuig.util.HibernateUtil;

/**
 * 测试：抓取策略
 * @author Administrator
 */
@SuppressWarnings("unchecked")
public class TestFetch {

	/** 针对Many端的抓取---------------------------------------------  */
	@Test
	public void test01(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			/**
			 * 测试： <many-to-one name="classroom" column="c_id" fetch="join"/>
			 * fetct="join"，是否会只发一条sql，且会取出所有的关联对象
			 */
			Student stu = (Student) session.load(Student.class, 1);
			System.out.println(stu.getSname());
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
			 * 测试：fetch="join"，对于下面的方式，是否有效
			 * 此时，如果只使用对象本身，则只会有一条获取自生的sql，如果有使用关联对象，则会再发送相应sql
			 * 测试结果：fetch="join"只对load有效
			 */
			List<Student> list = session.createQuery(" from Student").list();
			for(Student stu : list){
				System.out.println(stu.getSname() + stu.getClassroom().getCname());
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
			 * one端已经加了fetch join
			 *	<set name="stus" lazy="extra" inverse="true" fetch="subselect">
		     *  	<key column="c_id"/>
		     *  	<one-to-many class="Student"/>
		     *  </set>
			 * 此时会发现：只会发出一条sql
			 */
			Classroom classroom = (Classroom)session.load(Classroom.class, 1);
			System.out.println(classroom.getCname());
			for(Student stu : classroom.getStus()){
				System.out.println(stu.getSname() +"-- " +stu.getSex());
			}
			System.out.println(classroom.getSpecial().getSname());
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
			 * 解决办法：使用fetch="subselect"，
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
