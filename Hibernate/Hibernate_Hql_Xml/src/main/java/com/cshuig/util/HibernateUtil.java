package com.cshuig.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

	private final static SessionFactory FACTORY = buildSessionFactory();
	
	private static SessionFactory buildSessionFactory(){
		Configuration cfg = new Configuration().configure();
        //Hibernate4不在使用:ServiceRegistryBuilders
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
		SessionFactory factory = cfg.buildSessionFactory(serviceRegistry);
		return factory;
	}
	
	public static SessionFactory getSessionFactory(){
		return FACTORY;
	}
	
	public static Session openSession(){
		return FACTORY.openSession();
	}
	
	public static void closeSession(Session session){
		if(session != null) session.close();
	}
}
