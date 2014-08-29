package com.cshuig.test;

import java.util.List;

import org.hibernate.Session;
import org.junit.Test;

import com.cshuig.dto.StudentDto;
import com.cshuig.entity.Classroom;
import com.cshuig.entity.Student;
import com.cshuig.util.HibernateUtil;
@SuppressWarnings("unchecked")
public class TesHql {

	@Test
	public void test01(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			
			List<Classroom> list = session.createQuery("from Classroom ").list();
			for(Classroom clas : list){
				System.out.println(clas.getCname() +"、"+clas.getGrade() +"、"+clas.getSpecial().getSname());
			}
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
			 * HQL中，不可以使用  select *  ,需要使用别名
			 */
			List<Classroom> list = session.createQuery("select cl from Classroom cl").list();
			for(Classroom clas : list){
				System.out.println(clas.getCname() +"、"+clas.getGrade() +"、"+clas.getSpecial().getSname());
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
			 * 带   ’ ？ ‘ 格式的参数，需注意，在Hibernate中参数下标从0开始，JDBC中从1开始
			 */
			List<Classroom> list = session.createQuery("select cl from Classroom cl where cl.cname like ? and cl.grade = ?")
										   .setParameter(0, "%会计%")
										   .setParameter(1, 2009)
											.list();
			for(Classroom clas : list){
				System.out.println(clas.getCname() +"、"+clas.getGrade() +"、"+clas.getSpecial().getSname());
			}
		} catch (Exception e) {
			if(session!=null) session.getTransaction().rollback();
		}finally{
			HibernateUtil.closeSession(session);
		}	
	}
	
	@Test
	public void test04(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			
			/**
			 * 基于别名的格式的参数，需注意，在Hibernate中参数下标从0开始，JDBC中从1开始
			 */
			List<Classroom> list = session.createQuery("select cl from Classroom cl where cl.cname like :cname and cl.grade = :grade")
										   .setParameter("cname", "%会计%")
										   .setParameter("grade", 2009)
											.list();
			for(Classroom clas : list){
				System.out.println(clas.getCname() +"、"+clas.getGrade() +"、"+clas.getSpecial().getSname());
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
			 * 集合查询
			 */
//			List<Classroom> list = session.createQuery("select count(*) from Classroom cl where cl.cname like :cname and cl.grade = :grade")
//										   .setParameter("cname", "%会计%")
//										   .setParameter("grade", 2009)
//											.list();
//			
//			System.out.println(list.get(0));
			
			/**
			 * 如果返回的数据，只有一条，那么可以使用：uniqueResult
			 */
			Long couts = (Long) session.createQuery("select count(*) from Classroom cl where cl.cname like :cname and cl.grade = :grade")
										.setParameter("cname", "%会计%")
										.setParameter("grade", 2009)
										.uniqueResult();
			System.out.println(couts);
		} catch (Exception e) {
			if(session!=null) session.getTransaction().rollback();
		}finally{
			HibernateUtil.closeSession(session);
		}	
	}
	
	@Test
	public void test06(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			
			/**
			 * group by
			 * 查询，某个专业的某个班级 男女个数
			 * 基于投影的查询，通过在列表中存储一个对象数组
			 */
			
			String hql = "select stu.sex, count(stu.id) "
						+"from Classroom cla "
						+"inner join Student stu with cla.id=stu.c_id"
						+ "inner join Special spe with cla.s_id = spe.id"
						+ "where cla.cname ? "
						+ "and spe.sname ?"
						+ "group by stu.sex";
			
			List<Object[]> list = session.createQuery(hql).setParameter(0, "%会计%").setParameter(1, "%会计%").list();
			for(Object[] obj : list){
				System.out.println(obj[0] +"、" +obj[1]);
			}
			System.out.println(list.get(0));
		} catch (Exception e) {
			if(session!=null) session.getTransaction().rollback();
		}finally{
			HibernateUtil.closeSession(session);
		}	
	}
	
	@Test
	public void test07(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			
			/**
			 * 查询某个班级的学生
			 */
			
			String hql = "select stu from Student stu where stu.classroom.id = ?";
			List<Student> list = session.createQuery(hql).setParameter(0, 1).list();
			for(Student s : list){
				System.out.println(s.getSname() +"、" + s.getSex());
			}
		} catch (Exception e) {
			if(session!=null) session.getTransaction().rollback();
		}finally{
			HibernateUtil.closeSession(session);
		}	
	}
	
	@Test
	public void test08(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			
			/**
			 * 查询班级信息
			 */
			String hql = "select cla from Classroom cla where cla.id = ?";
			List<Classroom> list = session.createQuery(hql).setParameter(0, 1).list();
			for(Classroom s : list){
				/**
				 * 如果发现：s.getStus().size()，时候sql语句不是 select count(*)
				 * 那么需要在@OneToMany端添加一个注解：@LazyCollection(LazyCollectionOption.EXTRA)
				 */
				System.out.println(s.getCname() + s.getStus().size() + s.getSpecial().getSname());
			}
		} catch (Exception e) {
			if(session!=null) session.getTransaction().rollback();
		}finally{
			HibernateUtil.closeSession(session);
		}	
	}
	
	@Test
	public void test09(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			
			/**
			 * 查询班级信息
			 */
			String hql = "select cla from Classroom cla where cla.id = ? and cla.special.id = ?";
			List<Classroom> list = session.createQuery(hql).setParameter(0, 1).setParameter(1, 1).list();
			for(Classroom s : list){
				System.out.println(s.getCname() + s.getSpecial().getSname());
			}
		} catch (Exception e) {
			if(session!=null) session.getTransaction().rollback();
		}finally{
			HibernateUtil.closeSession(session);
		}	
	}
	
	@Test
	public void test10(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			
			/**
			 * 使用in查询
			 * 需要特别注意以下细节：
			 * 		in(必须使用别名) 如下 in(:c_id)
			 * 		如果有 ？ 参数形式，那么此条件必须放在in之前  如下：cla.special.id 在  cla.id in(:c_id) 之前
			 */
			String hql = "select cla from Classroom cla where cla.special.id = ? and cla.id in (:c_id)";
			List<Classroom> list = session.createQuery(hql)
									.setParameter(0, 1)
									.setParameterList("c_id", new Integer[]{1,2,3})
									.list();
			for(Classroom s : list){
				System.out.println(s.getCname() + s.getSpecial().getSname());
			}
		} catch (Exception e) {
			if(session!=null) session.getTransaction().rollback();
		}finally{
			HibernateUtil.closeSession(session);
		}	
	}
	
	@Test
	public void test11(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			
			/**
			 * 分页查询
			 * setFirstResult(下标索引值,从0开始)
			 * setMaxResults(返回记录格式，单纯的记录个数)
			 */
			String hql = "select stu from Student stu";
			List<Student> list = session.createQuery(hql)
									.setFirstResult(10)
									.setMaxResults(10)
									.list();
			for(Student s : list){
				System.out.println(s.getSname());
			}
		} catch (Exception e) {
			if(session!=null) session.getTransaction().rollback();
		}finally{
			HibernateUtil.closeSession(session);
		}	
	}
	
	/**--------------- HQL的连接查询         start  -------------**/
	@Test
	public void testJoin01(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			//获取班级1 的所有学生
			String hql = "select stu from Student stu join stu.classroom cla where cla='1'";
			List<Student> list = session.createQuery(hql).list();
			for(Student stu : list){
				System.out.println(stu.getSname() +" 、" +stu.getSex());
			}
		} catch (Exception e) {
			if(session!=null) session.getTransaction().rollback();
		}finally{
			HibernateUtil.closeSession(session);
		}	
	}
	
	/**--------------- HQL的连接查询         start  -------------**/
	@Test
	public void testJoin02(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			//所以班级的男女生个数
			String hql = "select cla.cname,stu.sex,count(stu.id) from Student stu right join stu.classroom cla group by cla.id,stu.sex";
			List<Object[]> list = session.createQuery(hql).list();
			for(Object[] obj : list){
				System.out.println(obj[0] +","+ obj[1] +","+ obj[2]);
			}
		} catch (Exception e) {
			if(session!=null) session.getTransaction().rollback();
		}finally{
			HibernateUtil.closeSession(session);
		}	
	}
	
	@Test
	public void testJoin03(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			//所以班级的男女生个数
			String hql = "select stu.id,stu.sname,stu.sex,cla.cname,spe.sname"
						 + " from Student stu "
						 + " left join stu.classroom cla"
						 + " left join cla.special spe";
			List<Object[]> list = session.createQuery(hql).list();
			for(Object[] obj : list){
				System.out.println(obj[0] +","+ obj[1] +","+ obj[2]+obj[3] +","+ obj[4]);
			}
		} catch (Exception e) {
			if(session!=null) session.getTransaction().rollback();
		}finally{
			HibernateUtil.closeSession(session);
		}	
	}
	
	@Test
	public void testJoin04(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			/*
			 *使用 DTO 对象,DTO仅仅是用来存储数据 
			 *注意 select 后面 必须要加上 完整的 包名 如下： select new com.cshuig.dto.StudentDto()
			 */
			String hql = "select new com.cshuig.dto.StudentDto "
						 + "(stu.id as id,stu.sname as sname ,stu.sex as sex,cla.cname as cname,spe.sname as speName)"
						 + " from Student stu "
						 + " left join stu.classroom cla"
						 + " left join cla.special spe";
			List<StudentDto> list = session.createQuery(hql).list();
			for(StudentDto sdto : list){
				System.out.println(sdto.getId() +","
									+ sdto.getSex() +","
									+ sdto.getSex()+","
									+ sdto.getCname() +","
									+ sdto.getSpeName());
			}
		} catch (Exception e) {
			if(session!=null) session.getTransaction().rollback();
		}finally{
			HibernateUtil.closeSession(session);
		}	
	}
	
	@Test
	public void testJoin05(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			/**
			 *  统计每个专业的学生个数
			 */
			String hql = "select spe.sname,stu.sex,count(stu.id) from Student stu right join stu.classroom.special spe group by spe.id,stu.sex";
			List<Object[]> list = session.createQuery(hql).list();
			for(Object[] obj : list){
				System.out.println(obj[0] +"  ---  " +obj[1] + "   -----  "  + obj[2]);
			}
		} catch (Exception e) {
			if(session!=null) session.getTransaction().rollback();
		}finally{
			HibernateUtil.closeSession(session);
		}	
	}
	
	
}
