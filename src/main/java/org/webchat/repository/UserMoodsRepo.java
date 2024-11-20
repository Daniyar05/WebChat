package org.webchat.repository;

import org.webchat.domain.User;
import org.webchat.utils.PasswordHasher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserMoodsRepo {
    public List<String> getUsersId(String mood) {
        String userQuery = "SELECT id_user FROM user_moods WHERE mood = ?";
        List<String> list = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement userStatement = connection.prepareStatement(userQuery)) {

            userStatement.setString(1, mood);
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

    public boolean addUser(String userId, String mood) {
        String userQuery = "INSERT INTO user_moods (id_user, mood) VALUES (?,?)";

        try (Connection connection = DatabaseConnection.getConnection();
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

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement userStatement = connection.prepareStatement(userQuery)) {

            userStatement.setString(1, userId);
            userStatement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean hasMoodsForUser(String userId){
        String userQuery = "SELECT * FROM user_moods WHERE id_user=?";

        try (Connection connection = DatabaseConnection.getConnection();
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
