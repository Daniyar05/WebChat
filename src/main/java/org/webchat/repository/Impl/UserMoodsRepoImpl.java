package org.webchat.repository.Impl;

import org.webchat.db.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserMoodsRepoImpl {
    DatabaseConnection databaseConnection;
    public UserMoodsRepoImpl(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public List<String> getUsersId(String mood, String userById) {
        String userQuery = "SELECT id_user FROM user_moods WHERE mood = ? AND id_user!=?";
        List<String> list = new ArrayList<>();
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement userStatement = connection.prepareStatement(userQuery)) {

            userStatement.setString(1, mood);
            userStatement.setString(2, userById);
            ResultSet resultSet = userStatement.executeQuery();
            while (resultSet.next()) {
                list.add(resultSet.getString("id_user"));
            }
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addUserMood(String userId, String mood) {
        String userQuery = "INSERT INTO user_moods (id_user, mood) VALUES (?,?)";
        removeUser(userId);
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement userStatement = connection.prepareStatement(userQuery)) {

            userStatement.setString(1, userId);
            userStatement.setString(2, mood);
            userStatement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean removeUser(String userId){
        String userQuery = "DELETE FROM user_moods WHERE id_user=?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement userStatement = connection.prepareStatement(userQuery)) {

            userStatement.setString(1, userId);
            userStatement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    private boolean removeById(String id){
        String userQuery = "DELETE FROM user_moods WHERE id=?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement userStatement = connection.prepareStatement(userQuery)) {

            userStatement.setString(1, id);
            userStatement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean hasMoodsForUser(String userId){
        String userQuery = "SELECT * FROM user_moods WHERE id_user=?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement userStatement = connection.prepareStatement(userQuery)) {

            userStatement.setString(1, userId);
            userStatement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }
}
