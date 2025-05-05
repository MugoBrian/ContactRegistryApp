package com.contactregistry.ContactRegistryApp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.contactregistry.ContactRegistryApp.model.User;

public class UserDAO {

    private Connection conn;

    public UserDAO(Connection conn) {
        this.conn = conn;
    }

    public User validate(String email, String password) throws SQLException {
        String sql = "SELECT * FROM user WHERE email = ? AND password = MD5(?)";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, email);
            statement.setString(2, password);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("email_address"),
                        null,
                        rs.getString("first_name"),
                        null);
            }
        }
        return null;
    }
}
