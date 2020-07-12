package controllers;

import models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserController {

    public static final String dbURL = "jdbc:sqlite:users.db";

    List<User> selectAll(){
        try (Connection connection = DriverManager.getConnection(dbURL)) {
            try (PreparedStatement prepStmt = connection.prepareStatement("SELECT * FROM users;")) {

                List<User> users = new ArrayList<>();

                ResultSet rs = prepStmt.executeQuery();
                if (rs.next()) {
                    users.add((new User(
                            rs.getString("id"),
                            rs.getString("lastName"),
                            rs.getString("firstName"),
                            rs.getString("email"),
                            rs.getString("role"),
                            rs.getString("team")
                    )));
                }
                prepStmt.execute();
                return users;

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
