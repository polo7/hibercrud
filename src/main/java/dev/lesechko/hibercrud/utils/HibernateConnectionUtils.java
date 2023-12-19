package dev.lesechko.hibercrud.utils;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateConnectionUtils {
    private static SessionFactory sessionFactory;

    private HibernateConnectionUtils() {}

    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (HibernateException e) {
            System.out.println("Failed to create SessionFactory");
            e.printStackTrace();
        }
    }

//    private static SessionFactory openFactory() {
//        if (sessionFactory == null) {
//            sessionFactory = new Configuration().configure().buildSessionFactory();
//        }
//        return sessionFactory;
//    }


    public static Session getNewSession() {
        return sessionFactory.openSession();
    }
}
