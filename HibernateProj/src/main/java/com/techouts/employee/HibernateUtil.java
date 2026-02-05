package com.techouts.employee;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {
    private static SessionFactory sessionFactory = null;
    public static SessionFactory getSessionFactory() throws HibernateException {
    	if(sessionFactory==null) {
    		sessionFactory = new Configuration().configure().buildSessionFactory();
    	}
        return sessionFactory;
    }
}

