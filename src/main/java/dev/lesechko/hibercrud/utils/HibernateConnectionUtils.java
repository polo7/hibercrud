package dev.lesechko.hibercrud.utils;

//import java.sql.*;

//TODO: тут делаем синглтон SessionFactory и обращаемся к нему в репозиториях

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConnectionUtils {
    private static SessionFactory sessionFactory;
    private HibernateConnectionUtils() {}

    //TODO: так ОК или лучше через метод openFactory?
    static {
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } else {
            throw new RuntimeException("Failed to init SessionFactory");
        }
    }

//    private static SessionFactory openFactory() {
//        if (sessionFactory == null) {
//            sessionFactory = new Configuration().configure().buildSessionFactory();
//        } else {
//            throw new RuntimeException("Failed to init SessionFactory");
//        }
//        return sessionFactory;
//    }


    public static Session getNewSession() {
        return sessionFactory.openSession();
    }
}
