package com.cshuig.test;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.junit.Test;

import com.cshuig.dto.StudentDto;
import com.cshuig.dto.StudentDto1;
import com.cshuig.entity.Classroom;
import com.cshuig.entity.Special;
import com.cshuig.entity.Student;
import com.cshuig.util.HibernateUtil;
@SuppressWarnings("unchecked")
public class TestSQL {

	/**
	 *返回某个 entity的列表
	 */
	@Test
	public void test01(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			List<Student> list = session.createSQLQuery("select * from t_student").addEntity(Student.class).list();
			for(Student stu : list){
				System.out.println(stu.getSname() +"、" + stu.getSex());
			}
		} catch (Exception e) {
			if(session!=null) session.getTransaction().rollback();
		}finally{
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 *返回某个 一个 dto
	 *这样会出现一个问题：当出现同名的字段时，前面的会覆盖后面的
	 *	如student.sname 会覆盖 spe.sname，导致下面的输出结果为：
	 *			         张飞1 —— 男  —— 网络1 —— 张飞1
	 *	正确的数据应该是：张飞1 —— 男  —— 网络1 —— 计算机网络
	 *
	 *解决的办法：往每一项 添加 {} 如：select {stu.*} , {cla.*}, {spe.*}
	 */
	@Test
	public void test02(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			List<Object[]> list = session.createSQLQuery("select {stu.*},{cla.*},{spe.*} "
														+ "from t_student stu "
														+ "left join t_classroom cla on(stu.c_id=cla.id) "
														+ "left join t_special spe on(cla.s_id=spe.id)")
														.addEntity("stu", Student.class)
														.addEntity("cla", Classroom.class)
														.addEntity("spe", Special.class).list();
			Student stu = null;
			Classroom cla = null;
			Special spe = null;
			for(Object[] obj : list){
				stu = (Student) obj[0];
				cla = (Classroom) obj[1];
				spe = (Special) obj[2];
				
				System.out.println(stu.getSname() + " —— " +stu.getSex()+"  —— "+ cla.getCname() +" —— "+spe.getSname());
			}
		} catch (Exception e) {
			if(session!=null) session.getTransaction().rollback();
		}finally{
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 *在实际业务中，可以将返回一个dto的列表
	 *
	 */
	@Test
	public void test03(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			List<Object[]> list = session.createSQLQuery("select {stu.*},{cla.*},{spe.*} "
														+ "from t_student stu "
														+ "left join t_classroom cla on(stu.c_id=cla.id) "
														+ "left join t_special spe on(cla.s_id=spe.id)")
														.addEntity("stu", Student.class)
														.addEntity("cla", Classroom.class)
														.addEntity("spe", Special.class).list();
			Student stu = null;
			Classroom cla = null;
			Special spe = null;
			List<StudentDto1> listDto = new ArrayList<StudentDto1>();
			for(Object[] obj : list){
				stu = (Student) obj[0];
				cla = (Classroom) obj[1];
				spe = (Special) obj[2];
				listDto.add(new StudentDto1(stu, cla, spe));
				System.out.println(stu.getSname() + " —— " +stu.getSex()+"  —— "+ cla.getCname() +" —— "+spe.getSname());
			}
			System.out.println(list.size());
		} catch (Exception e) {
			if(session!=null) session.getTransaction().rollback();
		}finally{
			HibernateUtil.closeSession(session);
		}
	}
	
	
	/**
	 * 查询结果是指定的 一些列：如stduentDto.java
	 * 在实际业务中，可以将返回一个dto的列表
	 *
	 */
	@Test
	public void test04(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			List<StudentDto> list = session.createSQLQuery(" select stu.id as id,stu.sname as sname, stu.sex as sex, cla.cname as cname, spe.sname as speName "
														+ " from t_student stu "
														+ " left join t_classroom cla on(stu.c_id=cla.id) "
														+ " left join t_special spe on(cla.s_id=spe.id)")
														.setResultTransformer(Transformers.aliasToBean(StudentDto.class))
														.list();
			for(StudentDto dto : list){
				//System.out.println(dto.getId() + " —— "+dto.getSname()+ " —— " + dto.getSex()+ " —— "+dto.getCname()+ " —— "+dto.getSpeName());
				System.out.println(dto);
			}
		} catch (Exception e) {
			if(session!=null) session.getTransaction().rollback();
		}finally{
			HibernateUtil.closeSession(session);
		}
	}
}
