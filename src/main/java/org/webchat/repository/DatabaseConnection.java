package org.webchat.repository;

import java.sql.*;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/anonymous_chat_db";
    private static final String USER = "daniyar";
    private static final String PASSWORD = "";


    private static final String URL_p = "jdbc:postgresql://127.0.0.1:5432/anonymous_chat_db";
    private static final String USER_p = "postgres";
    private static final String PASSWORD_p = "12345678";



    public static Connection getConnectionMaria() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }


    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return DriverManager.getConnection(URL_p, USER_p, PASSWORD_p);
    }

    public static boolean createAllTable(Connection connection){ // MariaDB
//        String createTableChats = "CREATE TABLE IF NOT EXISTS chats(id_chat VARCHAR(255) PRIMARY KEY, name VARCHAR(255) NOT NULL)";
//        String createTableMessages = "CREATE TABLE IF NOT EXISTS messages(id_message BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY, id_chat VARCHAR(255) NOT NULL REFERENCES chats(id_chat), id_from VARCHAR(255) NOT NULL, content TEXT, date TIMESTAMP)";
//        String createTableUserChats = "CREATE TABLE IF NOT EXISTS user_chats(id_user VARCHAR(255) NOT NULL REFERENCES users(id_user), id_chat VARCHAR(255) NOT NULL REFERENCES chats(id_chat))";
//        String createTableUsers = "CREATE TABLE IF NOT EXISTS users(id_user VARCHAR(255) PRIMARY KEY, username VARCHAR(255) NOT NULL UNIQUE, password_hash VARCHAR(255) NOT NULL)";
        String createTableChats = "CREATE TABLE IF NOT EXISTS chats (id_chat VARCHAR(255) PRIMARY KEY, name VARCHAR(255) NOT NULL)";
        String createTableMessages = "CREATE TABLE IF NOT EXISTS messages (id_message SERIAL PRIMARY KEY, id_chat VARCHAR(255) NOT NULL REFERENCES chats(id_chat), id_from VARCHAR(255) NOT NULL, content TEXT, date TIMESTAMP)";
        String createTableUserChats = "CREATE TABLE IF NOT EXISTS user_chats (id_user VARCHAR(255) NOT NULL REFERENCES users(id_user), id_chat VARCHAR(255) NOT NULL REFERENCES chats(id_chat), PRIMARY KEY (id_user, id_chat))";
        String createTableUsers = "CREATE TABLE IF NOT EXISTS users (id_user VARCHAR(255) PRIMARY KEY, username VARCHAR(255) NOT NULL UNIQUE, password_hash VARCHAR(255) NOT NULL)";

        try (PreparedStatement chatsStatement = connection.prepareStatement(createTableChats);
             PreparedStatement messagesStatement = connection.prepareStatement(createTableMessages);
             PreparedStatement userChatsStatement = connection.prepareStatement(createTableUserChats);
             PreparedStatement userStatement = connection.prepareStatement(createTableUsers)) {

            ResultSet userResultSet1 = chatsStatement.executeQuery();
            ResultSet userResultSet2 = messagesStatement.executeQuery();
            ResultSet userResultSet3 = userChatsStatement.executeQuery();
            ResultSet userResultSet4 = userStatement.executeQuery();
            return (userResultSet1.next() && userResultSet2.next() && userResultSet3.next() && userResultSet4.next());
        } catch (SQLException e) {
            return false;
        }
    }
}