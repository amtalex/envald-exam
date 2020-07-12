package controllers;

import configurators.DataBaseConfig;
import models.User;
import repositories.CrudRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDataBaseController implements CrudRepository<User> {

    private static final String tableName = "users.db";
    private static final String dbURL = (DataBaseConfig.getInstance().getDbUrl()).concat(tableName);

    private UserDataBaseController() {
    }

    public static UserDataBaseController getInstance() {
        return new UserDataBaseController();
    }

    public void add(User user) {
        try (Connection connection = DriverManager.getConnection(dbURL)) {
            try (PreparedStatement prepStmt = connection.prepareStatement(
                    "INSERT INTO users (id,lastName,firstName,email,role,team) " +
                            "VALUES (?,?,?,?,?,?);")) {
                prepStmt.setString(1, user.getId());
                prepStmt.setString(2, user.getLastName());
                prepStmt.setString(3, user.getFirstName());
                prepStmt.setString(4, user.getEmail());
                prepStmt.setString(5, user.getRole());
                prepStmt.setString(6, user.getTeam());
                prepStmt.execute();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<User> selectAll() {
        try (Connection connection = DriverManager.getConnection(dbURL)) {
            try (Statement prepStmt = connection.createStatement()) {

                List<User> users = new ArrayList<>();
                ResultSet rs = prepStmt.executeQuery("SELECT * FROM users;");
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
                return users;

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<User> findBy(String param, String value) {
        try (Connection connection = DriverManager.getConnection(dbURL)) {
            try (PreparedStatement prepStmt = connection.prepareStatement("SELECT * FROM users WHERE ?=?;")) {
                prepStmt.setString(1, param);
                prepStmt.setString(2, value);

                ResultSet rs = prepStmt.getResultSet();
                if (rs.next()) {
                    return Optional.of(new User(
                            rs.getString("id"),
                            rs.getString("lastName"),
                            rs.getString("firstName"),
                            rs.getString("email"),
                            rs.getString("role"),
                            rs.getString("team")));
                } else {
                    return Optional.empty();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(User user) {
        try (Connection connection = DriverManager.getConnection(dbURL)) {
            try (PreparedStatement prepStmt = connection.prepareStatement(
                    "UPDATE users SET " +
                            "lastName = ?, " +
                            "firstName = ?, " +
                            "email = ?, " +
                            "role = ?, " +
                            "team = ? WHERE id = ?;")) {
                prepStmt.setString(1, user.getLastName());
                prepStmt.setString(2, user.getFirstName());
                prepStmt.setString(3, user.getEmail());
                prepStmt.setString(4, user.getRole());
                prepStmt.setString(5, user.getTeam());
                prepStmt.setObject(6, user.getId());
                prepStmt.execute();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(User user) {
        try (Connection connection = DriverManager.getConnection(dbURL)) {
            try (PreparedStatement prepStmt = connection.prepareStatement("DELETE FROM users WHERE id=?;")) {
                prepStmt.setString(1, user.getId());
                prepStmt.execute();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
