package com.cshuig.test;

import com.cshuig.entity.Classroom;
import com.cshuig.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.junit.Test;

import java.util.List;

public class TesCriteria {

	@Test
	public void test01(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
            Criteria criteria = session.createCriteria(Classroom.class);
            criteria.add(Restrictions.like("cname", "%会计%"));
			List<Classroom> list = criteria.list();
			for(Classroom clas : list){
				System.out.println(clas.getCname() +"、"+clas.getGrade() +"、"+clas.getSpecial().getSname()+"、"+clas.getStus().size());
            }
		} catch (Exception e) {
			if(session!=null) session.getTransaction().rollback();
		}finally{
			HibernateUtil.closeSession(session);
		}	
	}

    /**
     * 查询条件使用：or
     */
    @Test
    public void test02(){
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            Criteria criteria = session.createCriteria(Classroom.class);
            criteria.add(Restrictions.or(Restrictions.like("cname", "%会计%"), Restrictions.isNull("cname")));
            List<Classroom> list = criteria.list();
            for(Classroom clas : list){
                System.out.println(clas.getCname() +"、"+clas.getGrade() +"、"+clas.getSpecial().getSname()+"、"+clas.getStus().size());
            }
        } catch (Exception e) {
            if(session!=null) session.getTransaction().rollback();
        }finally{
            HibernateUtil.closeSession(session);
        }
    }

    /**
     * 查询条件使用：or
     */
    @Test
    public void test03(){
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            Criteria criteria = session.createCriteria(Classroom.class);
            criteria.setFetchMode("special", FetchMode.SELECT);
            criteria.add(Restrictions.or(Restrictions.ilike("cname","%会计%", MatchMode.ANYWHERE),Restrictions.isNull("cname")));
            List<Classroom> list = criteria.list();
            for(Classroom clas : list){
                System.out.println(clas.getCname() +"、"+clas.getGrade() +"、"+clas.getSpecial().getSname()+"、"+clas.getStus().size());
            }
        } catch (Exception e) {
            if(session!=null) session.getTransaction().rollback();
        }finally{
            HibernateUtil.closeSession(session);
        }
    }

    /**
     * 查询条件使用：or
     */
    @Test
    public void test04(){
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            Criteria criteria = session.createCriteria(Classroom.class);
            //criteria.setFetchMode("special", FetchMode.SELECT);
            criteria.add(Restrictions.or(Restrictions.ilike("cname","%会计%", MatchMode.ANYWHERE),Restrictions.isNull("cname")));

            //criteria.createCriteria("special", JoinType.LEFT_OUTER_JOIN);
            //criteria.add(Restrictions.eq("sname","%ss%"));//会报错，发现当前的criteria还是Classroom，不知道为什么
            criteria.createAlias("special","special",JoinType.LEFT_OUTER_JOIN);
            //criteria.add(Restrictions.like("special.sname", "%计算%"));
            List<Classroom> list = criteria.list();
            for(Classroom clas : list){
                System.out.println(clas.getCname() +"、"+clas.getGrade() +"、"+clas.getSpecial().getSname()+"、"+clas.getStus().size());
            }
        } catch (Exception e) {
            e.printStackTrace();
            if(session!=null) session.getTransaction().rollback();
        }finally{
            HibernateUtil.closeSession(session);
        }
    }

    /**
     * 查询条件使用：or
     */
    @Test
    public void test05(){
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            Criteria criteria = session.createCriteria(Classroom.class);

            //not in 的写法
            criteria.add(Restrictions.not(Restrictions.in("id", new Integer[]{1,2})));
            List<Classroom> list = criteria.list();
            for(Classroom clas : list){
                System.out.println(clas.getId() +" --- "+ clas.getCname());
            }
        } catch (Exception e) {
            e.printStackTrace();
            if(session!=null) session.getTransaction().rollback();
        }finally{
            HibernateUtil.closeSession(session);
        }
    }
}
