package com.codflix.backend.features.user;

import com.codflix.backend.core.Database;
import com.codflix.backend.models.User;
import com.codflix.backend.utils.GlobalData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    public User getUserByCredentials(String email, String password) {
        User user = null;

        Connection connection = Database.get().getConnection();
        try {
            PreparedStatement st = connection.prepareStatement("SELECT * FROM user WHERE email=? AND password=?");

            st.setString(1, email);
            st.setString(2, password);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                user = mapToUser(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public User getUserById(int userId) {
        User user = null;

        Connection connection = Database.get().getConnection();
        try {
            PreparedStatement st = connection.prepareStatement("SELECT * FROM user WHERE id=?");

            st.setInt(1, userId);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                user = mapToUser(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public User getUserByEmail(String email) {
        User user = null;

        Connection connection = Database.get().getConnection();
        try {
            PreparedStatement st = connection.prepareStatement("SELECT * FROM user WHERE email=?");

            st.setString(1, email);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                user = mapToUser(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public void createNewUser(String email, String hashedPassword) {
        Connection connection = Database.get().getConnection();
        try {
            PreparedStatement st = connection.prepareStatement("INSERT INTO user VALUES (null, ?, ?);");
            st.setString(1, email);
            st.setString(2, hashedPassword);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUserEmail(String email) {
        Connection connection = Database.get().getConnection();
        try {
            PreparedStatement st = connection.prepareStatement("UPDATE user SET email = ? WHERE id = (SELECT id FROM user WHERE email = ?);");
            st.setString(1, email);
            st.setString(2, GlobalData.getInstance().getCurrentUser().getEmail());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUserPassword(String password) {
        Connection connection = Database.get().getConnection();
        try {
            PreparedStatement st = connection.prepareStatement("UPDATE user SET password = ? WHERE id = (SELECT id FROM user WHERE email = ?);");
            st.setString(1, password);
            st.setString(2, GlobalData.getInstance().getCurrentUser().getEmail());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(int id) {
        Connection connection = Database.get().getConnection();
        try {
            PreparedStatement st = connection.prepareStatement("DELETE FROM user WHERE id = ?;");
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private User mapToUser(ResultSet rs) throws SQLException {
        return new User(
                rs.getInt(1), // id
                rs.getString(2), // email
                rs.getString(3) // password
        );
    }
}
