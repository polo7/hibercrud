package dev.lesechko.hibercrud;

import java.util.List;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import dev.lesechko.hibercrud.Developer;

public class DeveloperRunner {
    private static SessionFactory sessionFactory;

    public void addDeveloper(String firstName, String lastName, String specialty, int experience) {
        Session session = sessionFactory.openSession();
        Transaction transaction =  null;
        transaction = session.beginTransaction();
//        Developer developer = new Developer(firstName, lastName, specialty, experience);
        Developer developer = new Developer();
        developer.setFirstName(firstName);
        developer.setLastName(lastName);
        developer.setSpecialty(specialty);
        developer.setExperience(experience);
        session.persist(developer);
        transaction.commit();
        session.close();
    }

    public List listDevelopers() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List developers = session.createQuery("FROM Developer").list();
        transaction.commit();
        session.close();
        return developers;
    }

    public void updateDeveloper(int developerId, int experience) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Developer developer = (Developer) session.get(Developer.class, developerId);
        developer.setExperience(experience);
        session.merge(developer);
        transaction.commit();
        session.close();
    }

    public void removeDeveloper(int developerId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Developer developer = (Developer) session.get(Developer.class, developerId);
        session.remove(developer);
        transaction.commit();
        session.close();
    }

    public static void main(String[] args) {
//        PostgreSQL connection test
//        String DB_URL = "jdbc:postgresql://localhost:5432/hibercrud";
//        String USER = "postgres";
//        String PASSWORD = "password";
//        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
//            if (connection != null) {
//                System.out.println("Connected to POSTGRESQL");
//            } else {
//                System.out.println("Connection failed.");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("Hello world!");

//        Hibernate example
        sessionFactory = new Configuration().configure().buildSessionFactory();
        DeveloperRunner developerRunner = new DeveloperRunner();
        System.out.println("Adding developer's records to the DB");
        developerRunner.addDeveloper("John", "Doe", "Java Dev", 100);
        developerRunner.addDeveloper("John", "UnDoe", "Java Dev", 100);
        developerRunner.addDeveloper("John", "Romero", "C++ Dev", 100);

        System.out.println("List of developers:");
        List<Developer> developers = developerRunner.listDevelopers();
        for (Developer developer : developers) {
            System.out.println(developer);
        }




    }
}