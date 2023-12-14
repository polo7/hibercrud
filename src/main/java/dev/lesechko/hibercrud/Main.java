package dev.lesechko.hibercrud;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) {
        String DB_URL = "jdbc:postgresql://localhost:5432/hibercrud";
        String USER = "postgres";
        String PASSWORD = "";
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            if (connection != null) {
                System.out.println("Connected to POSTGRESQL");
            } else {
                System.out.println("Connection failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Hello world!");

    }
}